package com.cathaybk.springbootmongodb.controller;

import org.springframework.stereotype.Controller;
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
    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello SpringBoot!";
    }

}
