package com.w.dao;

import com.w.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yanmiao.wu
 * @create 2022-08-15 10:03
 */
@Mapper
public interface OrderMapper {
    @Select("select * from tb_order where id = #{id}")
    Order selectOrderById(long id);

    @Select("select * from tb_order where user_id = #{userId}")
    List<Order> selectOrderByUserId(long userId);
}
