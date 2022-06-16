package com.cathaybk.springbootmongodb;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest(classes = { SpringBootMongoDbApplication.class })
class SpringBootMongoDbApplicationTests {

    @Autowired
    private Environment environment;

    @Test
    @Disabled
    void contextLoads() {
        System.err.println("environment = " + environment.getProperty("server.servlet.context-path"));
    }

}
