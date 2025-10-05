package com.cathaybk.springbootmongodb;

import com.cathaybk.springbootmongodb.properties.CustomMongoProperties;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author RogerLo
 * @date 2025/10/5
 */
@Configuration
@RequiredArgsConstructor
@EnableEncryptableProperties // 如果有用 Jasypt Starter (Jasypt 會自動解密，不需手動 @Autowired StringEncryptor 解密)
public class MongoConfig {

    private final CustomMongoProperties customMongoProperties;

    @Bean
    public MongoClient mongoClient() {
        String decryptedUsername = customMongoProperties.getUsername();
        String decryptedPassword = customMongoProperties.getPassword();
        String decryptedAuthDb = customMongoProperties.getAuthenticationDatabase();
        String database = customMongoProperties.getDatabase();
        String host = customMongoProperties.getHost();
        int port = customMongoProperties.getPort();

        String uri = String.format(
            "mongodb://%s:%s@%s:%d/%s?authSource=%s",
            decryptedUsername,
            decryptedPassword,
            host,
            port,
            database,
            decryptedAuthDb
        );
        return MongoClients.create(uri);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, customMongoProperties.getDatabase());
    }

}