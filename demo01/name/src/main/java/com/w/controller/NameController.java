package com.w.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author yanmiao.wu
 * @create 2022-08-11 17:34
 */
@RestController
public class NameController {
    @GetMapping("/name")
    public String getName() {
        System.out.println("执行了一次" + new Date(System.currentTimeMillis()));
        return "richard";
    }
}
