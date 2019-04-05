package com.ecnu.pizzaexpress.service.user;

import com.ecnu.pizzaexpress.model.User;

/**
 * Created by yerunjie on 2019-03-13
 *
 * @author yerunjie
 */
public interface IUserService {

    User findById(int id);

    User findByAccount(String account);

    int Register(User user);
}
