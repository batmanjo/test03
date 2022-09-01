package com.demo3.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import org.springframework.web.bind.annotation.*;

/**
 * @author yanmiao.wu
 * @create 2022-09-01 9:57
 */
@RestController
@RequestMapping("/hutool")
public class HuToolController {

    @GetMapping("/md5")
    public String getMD5Message(@RequestParam("msg") String msg) {
        return SecureUtil.md5(msg);
    }

    @GetMapping("/randomString")
    public String getRandomString(@RequestParam("length") int length) {
        if(length > 10000){
            return "length should <= 10000";
        }
        return RandomUtil.randomString(length);
    }
}
