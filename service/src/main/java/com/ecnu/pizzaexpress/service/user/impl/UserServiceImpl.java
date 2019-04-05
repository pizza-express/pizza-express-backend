package com.ecnu.pizzaexpress.service.user.impl;

import com.ecnu.pizzaexpress.dto.Token;
import com.ecnu.pizzaexpress.mapper.UserMapper;
import com.ecnu.pizzaexpress.model.User;
import com.ecnu.pizzaexpress.service.base.BaseServiceImpl;
import com.ecnu.pizzaexpress.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yerunjie on 2019-03-13
 *
 * @author yerunjie
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User findByAccount(String account) {
        return userMapper.findByAccount(account);
    }

    @Override
    public int Register(User user) {
        return userMapper.insertSelective(user);
    }
}
