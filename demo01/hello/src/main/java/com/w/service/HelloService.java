package com.w.service;

import com.w.client.NameClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yanmiao.wu
 * @create 2022-08-11 18:10
 */

@Service
public class HelloService {
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private NameClient nameClient;

    public String getName() {

//        String name = restTemplate.getForObject("http://name-service/name",String.class);
        String name = nameClient.findName();
        return name;
    }
}
