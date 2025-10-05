# Jasypt 加密配置使用說明

## 配置步驟

### 1. 執行加密工具生成加密字串
執行 `JasyptEncryptUtil.java` 獲得加密後的字串，例如：
```
原始 username: myDbUser
加密後 username: 5Fx8vLbV2QYi4K7H3Ng==
配置格式: username: ENC(5Fx8vLbV2QYi4K7H3Ng==)

原始 password: myDbUser  
加密後 password: 7Gx9wMcW3RZj5L8I4Oh==
配置格式: password: ENC(7Gx9wMcW3RZj5L8I4Oh==)

原始 auth-database: myshinynewdb
加密後 auth-database: 9Ix1yOeY4TbL6M9K5Qj==
配置格式: authentication-database: ENC(9Ix1yOeY4TbL6M9K5Qj==)
```

### 2. 更新 application.yml 配置
將加密後的字串替換到配置檔案中：

```yaml
spring:
  data:
    mongodb:
      username: ENC(5Fx8vLbV2QYi4K7H3Ng==)
      password: ENC(7Gx9wMcW3RZj5L8I4Oh==)
      authentication-database: ENC(9Ix1yOeY4TbL6M9K5Qj==)
```

### 3. 設定解密密鑰

#### 方案 A：環境變數（推薦）
```bash
# Windows
set JASYPT_ENCRYPTOR_PASSWORD=mySecretKey

# Linux/Mac
export JASYPT_ENCRYPTOR_PASSWORD=mySecretKey

# Docker
docker run -e JASYPT_ENCRYPTOR_PASSWORD=mySecretKey your-app
```

#### 方案 B：啟動參數
```bash
java -jar app.jar --jasypt.encryptor.password=mySecretKey
```

#### 方案 C：IDE 設定
在 IntelliJ IDEA 的 Run Configuration 中設定：
- Environment variables: `JASYPT_ENCRYPTOR_PASSWORD=mySecretKey`
- VM options: `-Djasypt.encryptor.password=mySecretKey`

### 4. 驗證配置
啟動應用程式，確認以下：
- 應用程式正常啟動
- MongoDB 連線成功
- 無解密錯誤訊息

## 安全性建議

1. **生產環境密鑰管理**
   - 使用複雜的加密密鑰
   - 定期更換加密密鑰
   - 密鑰與程式碼分離存放

2. **版本控制**
   - 確保加密工具中的密鑰與生產環境不同
   - 不要將真實的加密密鑰提交到版本控制

3. **權限控制**
   - 限制對加密密鑰的存取權限
   - 使用配置管理系統（如 Vault）管理密鑰

## 常見問題

### Q: 啟動時出現解密錯誤？
A: 檢查 `jasypt.encryptor.password` 是否設定正確，且與加密時使用的密鑰一致。

### Q: 如何更換加密密鑰？
A: 
1. 使用新密鑰重新加密所有敏感資訊
2. 更新配置檔案中的加密字串  
3. 更新環境變數中的解密密鑰

### Q: 開發環境如何處理？
A: 可以建立 `application-dev.yml` 使用明碼配置，僅在生產環境使用加密配置。