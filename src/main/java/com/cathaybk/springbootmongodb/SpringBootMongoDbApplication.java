package com.cathaybk.springbootmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= { DataSourceAutoConfiguration.class})
// @EnableMongoRepositories(basePackages = "com.cathaybk.springbootmongodb.repository") // 預設會scan sub-package
public class SpringBootMongoDbApplication {

    public static void main(String[] args) {
        // 強制指定 Log4j2 設定檔為 XML，避免載入舊的 YAML
        System.setProperty("log4j.configurationFile", "classpath:log4j2.xml");
        // 部分環境使用此別名，也一併設定
        System.setProperty("log4j2.configurationFile", "classpath:log4j2.xml");

        SpringApplication.run(SpringBootMongoDbApplication.class, args);
    }

}
