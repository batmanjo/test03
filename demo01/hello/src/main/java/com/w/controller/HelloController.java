package com.w.controller;

import com.w.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanmiao.wu
 * @create 2022-08-11 16:27
 */
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello(){
        String name = helloService.getName();
        return "hello," + name;
    }
}
