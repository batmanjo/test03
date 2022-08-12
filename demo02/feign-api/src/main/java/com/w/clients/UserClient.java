package com.w.clients;

import com.w.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yanmiao.wu
 * @create 2022-08-15 10:06
 */
@FeignClient(value = "user-service")
public interface UserClient {
    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") long id);

}
