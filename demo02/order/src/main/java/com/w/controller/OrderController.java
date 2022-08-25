package com.w.controller;

import com.w.pojo.Order;
import com.w.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yanmiao.wu
 * @create 2022-08-15 10:01
 */
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public Order queryOrderById(@PathVariable("id") long id) {
        // 根据id查询订单并返回
        return orderService.queryOrder(id);
    }

    @GetMapping("/orders/{userId}")
    public List<Order> queryOrderByUserId(@PathVariable("userId") long userId) {
        // 根据id查询订单并返回
        return orderService.queryOrderByUserId(userId);
    }
}
