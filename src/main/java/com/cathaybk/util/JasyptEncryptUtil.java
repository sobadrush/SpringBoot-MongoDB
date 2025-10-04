package com.cathaybk.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;

/**
 * Jasypt 加密工具類
 * 用於生成加密的配置值
 */
public class JasyptEncryptUtil {
    
    private static final String ALGORITHM = "PBEWithMD5AndDES";
    private static final String PASSWORD = "mySecretKey"; // 實際使用時應該從環境變數取得
    
    public static void main(String[] args) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(PASSWORD);
        encryptor.setAlgorithm(ALGORITHM);
        encryptor.setIvGenerator(new RandomIvGenerator());
        
        // 加密資料庫帳號密碼
        String username = "myDbUser";
        String password = "myDbUser";
        String authDatabase = "myshinynewdb";
        
        String encryptedUsername = encryptor.encrypt(username);
        String encryptedPassword = encryptor.encrypt(password);
        String encryptedAuthDatabase = encryptor.encrypt(authDatabase);
        
        System.out.println("原始 username: " + username);
        System.out.println("加密後 username: " + encryptedUsername);
        System.out.println("配置格式: username: ENC(" + encryptedUsername + ")");
        System.out.println();
        
        System.out.println("原始 password: " + password);
        System.out.println("加密後 password: " + encryptedPassword);
        System.out.println("配置格式: password: ENC(" + encryptedPassword + ")");
        System.out.println();
        
        System.out.println("原始 auth-database: " + authDatabase);
        System.out.println("加密後 auth-database: " + encryptedAuthDatabase);
        System.out.println("配置格式: authentication-database: ENC(" + encryptedAuthDatabase + ")");
        System.out.println();
        
        // 驗證解密
        System.out.println("=== 驗證解密 ===");
        System.out.println("解密 username: " + encryptor.decrypt(encryptedUsername));
        System.out.println("解密 password: " + encryptor.decrypt(encryptedPassword));
        System.out.println("解密 auth-database: " + encryptor.decrypt(encryptedAuthDatabase));
    }
}