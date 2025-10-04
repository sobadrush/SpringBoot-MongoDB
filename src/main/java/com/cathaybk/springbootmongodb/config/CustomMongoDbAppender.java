package com.cathaybk.springbootmongodb.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.bson.Document;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 自定義 MongoDB Appender（延遲連線版）
 * - 啟動時不連線 MongoDB，避免初始化失敗
 * - 第一次寫入時才嘗試連線，若失敗則只記錄錯誤、不阻斷應用
 */
@Plugin(name = "CustomMongoDb", category = "Core", elementType = "appender", printObject = true)
public class CustomMongoDbAppender extends AbstractAppender {

    // 連線參數（由 XML 注入）
    private final String connectionString;
    private final String databaseName;
    private final String collectionName;

    // 實際連線資源（延遲建立）
    private volatile MongoClient mongoClient;
    private volatile MongoCollection<Document> collection;
    private final AtomicBoolean initTried = new AtomicBoolean(false);

    protected CustomMongoDbAppender(String name, Filter filter,
                                    String connectionString, String databaseName, String collectionName) {
        super(name, filter, null, true, Property.EMPTY_ARRAY);
        this.connectionString = connectionString;
        this.databaseName = databaseName;
        this.collectionName = collectionName;
    }

    private void tryInitIfNeeded() {
        if (collection != null || mongoClient != null) return;
        if (!initTried.compareAndSet(false, true)) return; // 僅嘗試一次初始化
        try {
            if (connectionString == null || databaseName == null || collectionName == null) {
                LOGGER.error("CustomMongoDbAppender 缺少必要參數(connectionString/databaseName/collectionName)");
                return;
            }
            mongoClient = MongoClients.create(connectionString);
            MongoDatabase db = mongoClient.getDatabase(databaseName);
            collection = db.getCollection(collectionName);
            LOGGER.info("CustomMongoDbAppender 已連線 MongoDB，collection='{}'", collectionName);
        } catch (Exception e) {
            LOGGER.error("CustomMongoDbAppender 初始化連線失敗: {}", e.getMessage());
            // 不拋出，避免影響應用；下次 append 不再嘗試
        }
    }

    @Override
    public void append(LogEvent event) {
        tryInitIfNeeded();
        if (collection == null) {
            // 無法連線時，僅記錄一次性錯誤並略過寫入
            if (initTried.get()) {
                LOGGER.debug("CustomMongoDbAppender 略過寫入：MongoDB 尚未可用或初始化失敗");
            }
            return;
        }
        try {
            // 建立 MongoDB 文檔
            Document logDocument = new Document()
                    .append("timestamp", Instant.ofEpochMilli(event.getTimeMillis()).toString())
                    .append("level", event.getLevel().toString())
                    .append("logger", event.getLoggerName())
                    .append("message", event.getMessage().getFormattedMessage())
                    .append("thread", event.getThreadName());

            if (event.getThrown() != null) {
                Throwable t = event.getThrown();
                logDocument.append("exception", new Document()
                        .append("class", t.getClass().getName())
                        .append("message", t.getMessage()));
            }

            collection.insertOne(logDocument);
        } catch (Exception e) {
            // 寫入失敗不拋出，避免影響應用
            LOGGER.error("CustomMongoDbAppender 寫入失敗: {}", e.getMessage());
        }
    }

    @Override
    public void stop() {
        super.stop();
        try {
            if (mongoClient != null) mongoClient.close();
        } catch (Exception ignored) {
        }
    }

    @PluginFactory
    public static CustomMongoDbAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginAttribute("connectionString") String connectionString,
            @PluginAttribute("databaseName") String databaseName,
            @PluginAttribute("collectionName") String collectionName,
            @PluginElement("Filter") Filter filter) {

        if (name == null) name = "CustomMongoDbAppender";
        // 不在此處建立連線；永遠回傳一個可用的 appender 實例
        return new CustomMongoDbAppender(name, filter, connectionString, databaseName, collectionName);
    }
}
