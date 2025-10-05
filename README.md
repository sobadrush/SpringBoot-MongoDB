# SpringBoot-MongoDB

> 這是一個 SpringBoot 整合 MongoDB 的完整範例專案，包含 CRUD 操作、Log4j2 與 MongoDB 整合、Jasypt 加密配置等功能

## 🚀 專案特色

- **SpringBoot + MongoDB**：完整的 CRUD 操作實作
- **Log4j2 + MongoDB 整合**：將應用程式日誌直接寫入 MongoDB
- **Jasypt 加密配置**：敏感資訊加密保護
- **自訂 MongoDB Appender**：客製化的日誌輸出格式
- **環境變數配置**：彈性的部署配置

## 📋 系統需求

- Java 17+
- Maven 3.6+
- MongoDB 4.4+
- Spring Boot 2.7.0

## 🛠️ 啟動專案

### 1. 環境準備
```bash
# 確認本機已安裝 MongoDB，並且已啟動服務
mongod --dbpath /path/to/your/db

# 設定環境變數（從 launch.env 檔案）
set JASYPT_ENCRYPTOR_PASSWORD=mySecretKey
set MONGO_DB_USER=myDbUser
set MONGO_DB_PWD=myDbUser
set MONGO_DB_HOST=127.0.0.1
set MONGO_DB_PORT=27017
set MONGO_DB_INSTANCE_NAME=myshinynewdb
set MONGO_DB_AUTH_DB=admin
```

### 2. 編譯與執行
```bash
# 編譯專案
mvn clean compile

# 執行專案
mvn spring-boot:run
```

### 3. 測試 API
專案啟動後，可透過以下 URL 測試：
- `GET http://localhost:8080/RogerSpringBootMongo/HelloWorldController/sayHello` - Hello World API

詳細的 HTTP 測試案例請參考：`IntelliJ-HTTP-TestCases/測試.http`

## 📚 技術文檔

### [Log4j2 與 MongoDB 配置說明](docs/Log4j2_MongoDB_配置說明.md)
完整的 Log4j2 與 MongoDB 整合配置說明，包含：
- 系統架構概述與協作流程圖
- 詳細的配置檔案說明
- 自訂 MongoDB Appender 實作解析
- 使用方式與最佳實踐
- 故障排除指南

### [Log4j2 與 MongoDB 協作行為圖](docs/Log4j2_MongoDB_協作行為圖.md)
視覺化的系統協作流程圖，包含：
- 系統架構概覽圖
- 日誌路由策略圖
- MongoDB Appender 生命週期時序圖
- 日誌資料流向圖
- MongoDB 文檔結構圖

### [Jasypt 加密配置使用說明](docs/JASYPT_USAGE.md)
詳細的 Jasypt 加密配置指南，包含：
- 配置步驟與實作範例
- 多種環境下的密鑰設定方式
- 安全性建議與最佳實踐
- 常見問題與故障排除

# 參考資料
|  #  |                       說明                        |                                       URL                                        |
|:---:|:-----------------------------------------------:|:--------------------------------------------------------------------------------:|
|  1  |          SpringBoot 整合 MongoDB 進行事務操作           |               https://www.tpisoftware.com/tpu/articleDetails/2517                |
|  2  | SpringBoot 集成 Spring Data Mongodb 操作 MongoDB 详解 |                        https://learnku.com/articles/60403                        |
|  3  |        [Day 04] - 用Spring Boot連接Mongo DB        |                 https://ithelp.ithome.com.tw/m/articles/10267376                 |
|  4  |       Introduction to Spring Data MongoDB       |              https://www.baeldung.com/spring-data-mongodb-tutorial               |
|  5  |                     @Field                      |          https://blog.csdn.net/xia15000506007/article/details/91795776           |
|  6  |                     @Query                      |                 https://www.baeldung.com/spring-data-annotations                 |
|  7  |     @DbRef / @Query("{customer: 'Roger'}")      |                   https://segmentfault.com/a/1190000010520535                    |
|  8  |                  Spring實戰-第四版                   | https://potoyang.gitbook.io/spring-in-action-v4/untitled-6/untitled-3/untitled-3 |


