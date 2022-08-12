package com.w;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yanmiao.wu
 * @create 2022-08-15 10:12
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.w.clients")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
