package com.demo3.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author yanmiao.wu
 * @create 2022-08-26 14:48
 */

public class MyJedis {
/*
//    public static void main(String[] args) {
//        JedisPool pool = new JedisPool("localhost",6379);
//
//        Jedis jedis = pool.getResource();
//        jedis.set("user","孙悟空");
//        jedis.set("user2","goku");
//    }
*/


    /**
     * @return 返回jedis连接池
     */
    public static Jedis getJedis(String host, int port) {
        System.out.println(host);
        System.out.println(port);
        JedisPool pool = new JedisPool(host, port);
        Jedis jedis = pool.getResource();
        return jedis;
    }
}
