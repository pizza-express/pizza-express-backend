package com.ecnu.pizzaexpress.mapper;

import com.ecnu.pizzaexpress.model.User;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    @Select({"SELECT *",
            "FROM user",
            "WHERE account = #{account}"})
    @ResultMap("BaseResultMap")
    User findByAccount(String account);
}