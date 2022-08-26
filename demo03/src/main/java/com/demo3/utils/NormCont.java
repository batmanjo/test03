package com.demo3.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yanmiao.wu
 * @create 2022-08-26 16:17
 */
@Component
public class NormCont {
    @Value("${myName}")
    private String name;

    public String getName() {
        return name;
    }
}
