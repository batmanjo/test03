package com.w.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author yanmiao.wu
 * @create 2022-08-11 18:10
 */

@Service
public class HelloService {
    @Autowired
    private RestTemplate restTemplate;

    public String getName() {
        String name = restTemplate.getForObject("http://name-service/name",String.class);
        return name;
    }
}
