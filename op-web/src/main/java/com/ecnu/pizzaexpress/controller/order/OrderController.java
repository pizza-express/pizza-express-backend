package com.ecnu.pizzaexpress.controller.order;

import com.ecnu.pizzaexpress.annotation.Authentication;
import com.ecnu.pizzaexpress.constants.OrderStatus;
import com.ecnu.pizzaexpress.constants.Role;
import com.ecnu.pizzaexpress.controller.BaseController;
import com.ecnu.pizzaexpress.model.Order;
import com.ecnu.pizzaexpress.mq.MessageProducer;
import com.ecnu.pizzaexpress.request.SearchOrderRequest;
import com.ecnu.pizzaexpress.service.order.IOrderService;
import com.ecnu.pizzaexpress.service.order.OrderBo;
import com.ecnu.pizzaexpress.utils.DateTimeUtils;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yerunjie on 2019-03-27
 *
 * @author yerunjie
 */
@RestController
@RequestMapping("/v1/api/order")
public class OrderController extends BaseController {

  @Autowired
  private MessageProducer messageProducer;

  @Autowired
  private IOrderService orderService;

  @RequestMapping(value = "", method = RequestMethod.POST)
  @Authentication(Role.User)
  public OrderBo createOrder(@RequestBody OrderBo orderBo) {
    orderService.createOrder(orderBo);
    return orderBo;
  }

  @RequestMapping(value = "/{id}/status/{status}", method = RequestMethod.PUT)
  @Authentication(Role.User)
  public ResultVo updateStatus(@PathVariable("id") int id,
      @PathVariable("status") int statusInt) {
    OrderStatus status = OrderStatus.valueOf(statusInt);
    orderService.updateOrderStatus(id, status);
    ResultVo resultVo = new ResultVo();
    resultVo.setSuccess(true);
    return resultVo;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  @Authentication({Role.User,Role.Admin})
  public List<OrderVo> queryOrders(
      @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
      @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber) {
    SearchOrderRequest request = new SearchOrderRequest();
    request.setOffset(pageNumber * pageSize);
    request.setPageSize(pageSize);
    if (getToken().isUser()) {
      request.setUserId(getToken().getId());
    }
    return orderService.searchOrders(request).stream().map(this::parse)
        .collect(Collectors.toList());
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @Authentication(Role.User)
  public OrderVo queryOrder(@PathVariable("id") int id) {
    OrderBo orderBo = orderService.findById(id);
    return parse(orderBo);
  }

  private OrderVo parse(Order source) {
    OrderVo result = new OrderVo();
    BeanUtils.copyProperties(source, result);
    if (!getToken().isAdmin()) {
      result.setCostPrice(null);
    }
    result.setStatus(source.getStatus().getDesc());
    result.setCreateTime(DateTimeUtils.formatDateTime(source.getCreateTime()));
    result.setDeliverTime(DateTimeUtils.formatDateTime(source.getDeliverTime()));
    return result;
  }
}
