package com.cathaybk.springbootmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= { DataSourceAutoConfiguration.class})
// @EnableMongoRepositories(basePackages = "com.cathaybk.springbootmongodb.repository") // 預設會scan sub-package
public class SpringBootMongoDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongoDbApplication.class, args);
    }

}
