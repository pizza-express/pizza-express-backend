package com.ecnu.pizzaexpress.service.user;

import com.ecnu.pizzaexpress.model.User;
import java.util.List;

/**
 * Created by yerunjie on 2019-03-13
 *
 * @author yerunjie
 */
public interface IUserService {

  List<User> findAll();

  User findById(int id);

  User findByAccount(String account);

  int register(User user);

  int modifyUserInfo(User user);
}
