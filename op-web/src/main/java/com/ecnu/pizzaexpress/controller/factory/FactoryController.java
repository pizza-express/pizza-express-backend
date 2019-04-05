package com.ecnu.pizzaexpress.controller.factory;

import com.ecnu.pizzaexpress.annotation.Authentication;
import com.ecnu.pizzaexpress.constants.Role;
import com.ecnu.pizzaexpress.controller.BaseController;
import com.ecnu.pizzaexpress.model.Factory;
import com.ecnu.pizzaexpress.service.dishes.DishesWithContent;
import com.ecnu.pizzaexpress.service.dishes.IDishesService;
import com.ecnu.pizzaexpress.service.factory.IFactoryService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yerunjie on 2019-03-15
 *
 * @author yerunjie
 */
@RestController
@RequestMapping("/v1/api/factory")
public class FactoryController extends BaseController {

  @Autowired
  private IFactoryService factoryService;

  @RequestMapping(value = "", method = RequestMethod.POST)
  @Authentication(Role.Admin)
  public Factory createFactory(@RequestBody Factory factory) {
    factoryService.create(factory);
    return factory;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  @Authentication(Role.Admin)
  public List<Factory> queryFactory() {
    return factoryService.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @Authentication({Role.Admin, Role.User})
  public Factory getFactoryById(@PathVariable("id") int id) {
    return factoryService.findById(id);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @Authentication(Role.Admin)
  public Factory updateFactory(@PathVariable("id") int id,
      @RequestBody Factory factory) {
    factory.setId(id);
    factoryService.update(factory);
    return factory;
  }

}
