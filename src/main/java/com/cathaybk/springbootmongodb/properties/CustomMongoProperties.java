package com.cathaybk.springbootmongodb.properties;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author RogerLo
 * @date 2025/10/5
 */
@Component
@ConfigurationProperties(prefix = "custom.mongodb")
@Setter
@Getter
@Accessors(chain = true) // 支援鏈式設定，但保留標準 setter 命名, 不能用 fluent = true
public class CustomMongoProperties {
    private String username;
    private String password;
    private String authenticationDatabase;
    private String database;
    private String host;
    private int port;
}
