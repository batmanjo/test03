package com.w.service;

import com.w.clients.UserClient;
import com.w.dao.OrderMapper;
import com.w.pojo.Order;
import com.w.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yanmiao.wu
 * @create 2022-08-15 10:18
 */
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserClient userClient;

    public Order queryOrder(long id) {
        Order order = orderMapper.selectOrderById(id);
        User user = userClient.findById(order.getUserId());
        order.setUser(user);
        return order;
    }

    public List<Order> queryOrderByUserId(long id) {
        System.out.println("查询了用户id为" + id + "的所有订单" + new Date(System.currentTimeMillis()));
        return orderMapper.selectOrderByUserId(id);
    }
}
