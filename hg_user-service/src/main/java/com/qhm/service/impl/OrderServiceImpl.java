package com.qhm.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.qhm.dao.OrderDao;
import com.qhm.pojo.Order;
import com.qhm.pojo.OrderDetail;
import com.qhm.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author qhm
 *
 */
@Service(interfaceClass=OrderService.class)
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderDao orderDao;

	@Override
	public PageInfo<Order> list(int userId, int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 10);
		
		return new PageInfo<Order>(orderDao.list(userId));
	}

	@Override
	public List<OrderDetail> listDetail(int orderId, int page) {
		// TODO Auto-generated method stub
		return orderDao.listDetail(orderId);
	}
	
}
