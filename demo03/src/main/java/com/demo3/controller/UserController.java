package com.demo3.controller;

import com.demo3.utils.MyJedis;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

/**
 * @author yanmiao.wu
 * @create 2022-08-26 15:34
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;

    @PostMapping("/add/{name}")
    public void addUser(@PathVariable(name = "name") String name) {
        Jedis jedis = MyJedis.getJedis(host, port);
        jedis.set(name, name + "redis");
        jedis.close();
    }

    @GetMapping("/{name}")
    public String getUser(@PathVariable(name = "name") String name) {
        Jedis jedis = MyJedis.getJedis(host, port);
        String s = "";
        try {
            s = jedis.get(name);
            System.out.println(s);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            jedis.close();
        }
        return s;

    }


}
