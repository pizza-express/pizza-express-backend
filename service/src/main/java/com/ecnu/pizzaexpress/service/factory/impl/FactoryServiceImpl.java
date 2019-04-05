package com.ecnu.pizzaexpress.service.factory.impl;

import com.ecnu.pizzaexpress.constants.FactoryStatus;
import com.ecnu.pizzaexpress.mapper.FactoryMapper;
import com.ecnu.pizzaexpress.model.Factory;
import com.ecnu.pizzaexpress.service.base.BaseServiceImpl;
import com.ecnu.pizzaexpress.service.deliver.impl.baidumap.BaiduMapApi;
import com.ecnu.pizzaexpress.service.deliver.impl.baidumap.response.GeocoderResponse;
import com.ecnu.pizzaexpress.service.deliver.impl.baidumap.response.Location;
import com.ecnu.pizzaexpress.service.factory.IFactoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yerunjie on 2019-03-15
 *
 * @author yerunjie
 */
@Service
public class FactoryServiceImpl extends BaseServiceImpl implements IFactoryService {

  @Autowired
  private FactoryMapper factoryMapper;

  @Autowired
  private BaiduMapApi baiduMapApi;

  @Override
  public Factory create(Factory factory) {
    factory.setStatus(FactoryStatus.ONLINE);
    resetAddress(factory);
    factoryMapper.insert(factory);
    return factory;
  }

  @Override
  public Factory findById(int id) {
    Factory factory = factoryMapper.selectByPrimaryKey(id);
    return factory;
  }

  @Override
  public boolean update(Factory factory) {
    resetAddress(factory);
    factoryMapper.updateByPrimaryKey(factory);
    return true;
  }

  @Override
  public List<Factory> findAll() {
    return factoryMapper.selectAll();
  }

  private void resetAddress(Factory factory) {
    GeocoderResponse geocoder = baiduMapApi.geocoder(factory.getAddress());
    Location location = geocoder.getResult().getLocation();
    factory.setLng(location.getLng());
    factory.setLat(location.getLat());
  }
}
