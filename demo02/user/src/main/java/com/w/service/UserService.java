package com.w.service;

import com.w.dao.UserMapper;
import com.w.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yanmiao.wu
 * @create 2022-08-15 10:39
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryUser(long id){
        return userMapper.selectUserById(id);
    }
}
