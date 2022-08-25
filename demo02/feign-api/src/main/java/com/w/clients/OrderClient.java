package com.w.clients;

import com.w.pojo.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author yanmiao.wu
 * @create 2022-08-15 14:02
 */
@FeignClient("order-service")
public interface OrderClient {
    @GetMapping("/order/orders/{userId}")
    List<Order> findByUserId(@PathVariable("userId") long userId);
}
