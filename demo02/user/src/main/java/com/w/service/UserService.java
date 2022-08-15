package com.w.service;

import com.w.clients.OrderClient;
import com.w.dao.UserMapper;
import com.w.pojo.Order;
import com.w.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yanmiao.wu
 * @create 2022-08-15 10:39
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderClient orderClient;

    public User queryUser(long id) {
        System.out.println("执行了一次" + new Date(System.currentTimeMillis()) + " 测试负载均衡");
        return userMapper.selectUserById(id);
    }

    public List<Order> queryOrderByUserId(long id) {
        return orderClient.findByUserId(id);
    }


}
