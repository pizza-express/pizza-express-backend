package com.ecnu.pizzaexpress.mapper;

import com.ecnu.pizzaexpress.model.Order;
import com.ecnu.pizzaexpress.request.SearchOrderRequest;
import java.util.List;

public interface OrderMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(Order record);

  Order selectByPrimaryKey(Integer id);

  List<Order> selectAll();

  List<Order>  findByRequest(SearchOrderRequest request);

  int updateOrderInfo(Order record);

  int updateDeliverInfo(Order record);

  int updateOrderStatus(Order order);
}