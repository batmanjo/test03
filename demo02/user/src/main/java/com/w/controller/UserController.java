package com.w.controller;

import com.w.pojo.Order;
import com.w.pojo.User;
import com.w.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yanmiao.wu
 * @create 2022-08-15 10:36
 */
@RefreshScope
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

//    @Autowired
//    private PatternProperties patternProperties;
//
//    @Value("${pattern.dateformat}")
//    private String dateformat;

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") long id, @RequestHeader(value = "sign", required = false) String sign) {
        return userService.queryUser(id);
    }

    @GetMapping("/orders/{userId}")
    public List<Order> getOrders(@PathVariable("userId") long userId, @RequestHeader(value = "sign", required = false) String sign) {
        System.out.println(sign);
        return userService.queryOrderByUserId(userId);
    }

//    @GetMapping("/user/now")
//    public String now(){
//        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat));
//    }
}
