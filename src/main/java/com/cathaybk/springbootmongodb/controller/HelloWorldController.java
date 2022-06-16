package com.cathaybk.springbootmongodb.controller;

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
public class HelloWorldController {

    @ResponseBody
    @GetMapping(value = "/sayHello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String sayHello() {
        return "Hello SpringBoot!";
    }

}
