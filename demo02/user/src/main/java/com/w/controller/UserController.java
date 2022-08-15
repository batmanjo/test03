package com.w.controller;

import com.w.pojo.Order;
import com.w.pojo.User;
import com.w.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yanmiao.wu
 * @create 2022-08-15 10:36
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") long id) {
        return userService.queryUser(id);
    }

    @GetMapping("/user-orders/{userId}")
    public List<Order> getOrders(@PathVariable("userId") long userId) {
        return userService.queryOrderByUserId(userId);
    }
}
