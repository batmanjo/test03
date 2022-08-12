package com.w.dao;

import com.w.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author yanmiao.wu
 * @create 2022-08-15 10:37
 */
@Mapper
public interface UserMapper {
    @Select("select * from tb_user where id = #{id}")
    User selectUserById(long id);
}
