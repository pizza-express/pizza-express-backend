package com.ecnu.pizzaexpress.controller.menu;

import com.ecnu.pizzaexpress.annotation.Authentication;
import com.ecnu.pizzaexpress.constants.Role;
import com.ecnu.pizzaexpress.controller.BaseController;
import com.ecnu.pizzaexpress.model.Dishes;
import com.ecnu.pizzaexpress.service.menu.IMenuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yerunjie on 2019-03-28
 *
 * @author yerunjie
 */
@RestController
@RequestMapping("/v1/api/menu")
public class MenuController extends BaseController {

  @Autowired
  private IMenuService menuService;

  @RequestMapping(value = "", method = RequestMethod.GET)
  @Authentication({Role.User, Role.Admin})
  public List<Dishes> createOrder() {
    return menuService.getMenu();
  }
}
