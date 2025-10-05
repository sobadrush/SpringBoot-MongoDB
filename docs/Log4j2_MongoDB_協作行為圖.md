# Log4j2 與 MongoDB 協作行為圖

## 系統架構概覽

```mermaid
graph TB
    subgraph "Spring Boot 應用層"
        A[Controller]
        B[Service Layer]
        C[Repository Layer]
    end
    
    subgraph "日誌框架層"
        D[SLF4J Facade]
        E[Log4j2 Core Engine]
        F[log4j2.yml 配置檔]
    end
    
    subgraph "日誌輸出目標"
        G[Console<br/>控制台輸出]
        H[RollingFile<br/>檔案系統]
        I[CustomMongoDB<br/>MongoDB 資料庫]
    end
    
    A --> B
    B --> C
    C --> D
    D --> E
    E --> F
    
    F --> G
    F --> H
    F --> I
    
    style A fill:#e3f2fd
    style E fill:#fff3e0
    style I fill:#f3e5f5
```

## 日誌路由策略

```mermaid
graph LR
    subgraph "Logger 階層"
        A[Root Logger<br/>WARN level]
        B[com.cathaybk<br/>INFO level]
        C[my_mongodb<br/>INFO level]
    end
    
    subgraph "Appender 處理器"
        D[Console Appender]
        E[RollingFile Appender]
        F[MongoDB Appender]
    end
    
    A --> D
    B --> D
    B --> E
    C --> F
    
    style B fill:#e8f5e8
    style C fill:#fff8e1
    style F fill:#fce4ec
```

## MongoDB Appender 生命週期

```mermaid
sequenceDiagram
    participant App as Spring Boot App
    participant Logger as Log4j2
    participant MongoDB_Appender as CustomMongoDbAppender
    participant MongoDB as MongoDB Database
    
    Note over App,MongoDB: 1. 應用啟動階段
    App->>Logger: 啟動 Log4j2
    Logger->>MongoDB_Appender: 建立 Appender 實例
    Note over MongoDB_Appender: 延遲連線設計<br/>不立即連接資料庫
    
    Note over App,MongoDB: 2. 首次日誌寫入
    App->>Logger: logger.info("訊息")
    Logger->>MongoDB_Appender: append(LogEvent)
    MongoDB_Appender->>MongoDB_Appender: tryInitIfNeeded()
    
    alt MongoDB 可用
        MongoDB_Appender->>MongoDB: 建立連線
        MongoDB-->>MongoDB_Appender: 連線成功
        MongoDB_Appender->>MongoDB: 寫入日誌文檔
    else MongoDB 不可用
        MongoDB_Appender->>MongoDB_Appender: 記錄錯誤，不中斷應用
    end
    
    Note over App,MongoDB: 3. 後續日誌寫入
    App->>Logger: logger.error("錯誤", ex)
    Logger->>MongoDB_Appender: append(LogEvent)
    MongoDB_Appender->>MongoDB: 直接寫入（已有連線）
    
    Note over App,MongoDB: 4. 應用關閉
    App->>Logger: 關閉 Log4j2
    Logger->>MongoDB_Appender: stop()
    MongoDB_Appender->>MongoDB: 關閉連線
```

## 日誌資料流向

```mermaid
flowchart TD
    A[業務程式碼<br/>logger.info()] --> B{Log4j2 Logger}
    
    B --> C[Console Appender]
    B --> D[RollingFile Appender] 
    B --> E[MongoDB Appender]
    
    C --> F[控制台輸出<br/>即時顯示]
    D --> G[本地檔案<br/>logs/*.log.gz]
    E --> H[MongoDB 集合<br/>application_logs]
    
    H --> I[結構化查詢<br/>日誌分析<br/>報表生成]
    
    style A fill:#e1f5fe
    style B fill:#fff3e0
    style E fill:#f3e5f5
    style H fill:#e8f5e8
    style I fill:#fce4ec
```

## MongoDB 文檔結構

```mermaid
classDiagram
    class LogDocument {
        +ObjectId _id
        +String timestamp
        +String level
        +String logger
        +String message  
        +String thread
        +ExceptionInfo exception
    }
    
    class ExceptionInfo {
        +String class
        +String message
    }
    
    LogDocument --> ExceptionInfo : 當有例外時
    
    note for LogDocument "儲存在 MongoDB 的<br/>application_logs 集合中"
```

這些圖表展示了：

1. **系統架構概覽**：Spring Boot 應用如何與 Log4j2 和 MongoDB 協作
2. **日誌路由策略**：不同 Logger 如何分發到不同的 Appender
3. **MongoDB Appender 生命週期**：從啟動到關閉的完整流程
4. **日誌資料流向**：日誌如何從程式碼流向各個輸出目標
5. **MongoDB 文檔結構**：在資料庫中儲存的日誌格式