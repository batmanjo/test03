package com.demo3.controller;

import com.demo3.utils.NormCont;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanmiao.wu
 * @create 2022-08-26 16:24
 */
@RestController
public class ValueController {

    @Value("1234")
    private String code;

    @Value("${myName}")
    private String name;

    @GetMapping("/user1")
    public String getname(){
        NormCont normCont = new NormCont();
        System.out.println(normCont.getName());
        return code + name;
    }
}
