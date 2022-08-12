package com.w.controller;

import com.w.pojo.Order;
import com.w.service.OrderService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanmiao.wu
 * @create 2022-08-15 10:01
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/{id}")
    public Order queryOrderByUserId(@PathVariable("id") long id) {
        // 根据id查询订单并返回
        return orderService.queryOrder(id);
    }
}
