package com.w.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yanmiao.wu
 * @create 2022-08-12 17:44
 */
@FeignClient("name-service")
public interface NameClient {
    /**
     * 简单实现
     * @return 返回名字
     */
    @GetMapping("/name")
    String findName();
}
