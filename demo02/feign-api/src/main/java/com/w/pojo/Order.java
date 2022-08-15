package com.w.pojo;

import lombok.Data;

/**
 * @author yanmiao.wu
 * @create 2022-08-15 13:59
 */
@Data
public class Order {
    private long id;
    private long userId;
    private String name;
    private long price;
    private int num;
    private User user;
}
