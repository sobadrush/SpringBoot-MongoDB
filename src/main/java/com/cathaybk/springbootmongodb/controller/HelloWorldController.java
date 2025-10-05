package com.cathaybk.springbootmongodb.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RogerLo
 * @date 2022/6/15
 */
@RequestMapping("/HelloWorldController")
@RestController
@Log4j2
public class HelloWorldController {

    private static final Logger mongoLogger = LogManager.getLogger("my_mongodb");

    @ResponseBody
    @GetMapping(value = "/sayHello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String sayHello() {
        log.info(" [log] ... 我是 sayHello() 方法 ...");
        mongoLogger.info(" [my_mongodb_log] ... 我是 sayHello() 方法 ...");
        return "Hello SpringBoot!";
    }

}
