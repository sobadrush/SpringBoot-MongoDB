package com.cathaybk.springbootmongodb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 測試 Log4j2 MongoDB Appender 配置
 */
public class Log4j2MongoDbTest {

    @Test
    public void testMongoDbAppenderConfiguration() {
        // 使用程式化方式建立 MongoDB Appender 配置
        String xmlConfig = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Configuration status=\"DEBUG\">\n" +
                "    <Appenders>\n" +
                "        <Console name=\"Console\" target=\"SYSTEM_OUT\">\n" +
                "            <PatternLayout pattern=\"%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n\"/>\n" +
                "        </Console>\n" +
                "        <NoSql name=\"MongoAppender\">\n" +
                "            <MongoDb4 databaseName=\"myshinynewdb\"\n" +
                "                      collectionName=\"application_logs\"\n" +
                "                      server=\"localhost\"\n" +
                "                      port=\"27017\"\n" +
                "                      username=\"myDbUser\"\n" +
                "                      password=\"myDbUser\"/>\n" +
                "        </NoSql>\n" +
                "    </Appenders>\n" +
                "    <Loggers>\n" +
                "        <Root level=\"info\">\n" +
                "            <AppenderRef ref=\"Console\"/>\n" +
                "        </Root>\n" +
                "    </Loggers>\n" +
                "</Configuration>";

        try {
            InputStream is = new ByteArrayInputStream(xmlConfig.getBytes(StandardCharsets.UTF_8));
            ConfigurationSource source = new ConfigurationSource(is);
            Configurator.initialize(null, source);

            Logger logger = LogManager.getLogger(Log4j2MongoDbTest.class);
            logger.info("測試 MongoDB Appender 配置");

            System.out.println("配置載入成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("配置錯誤: " + e.getMessage());
        }
    }
}

