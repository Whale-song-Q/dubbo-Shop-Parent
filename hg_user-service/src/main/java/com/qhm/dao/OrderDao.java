package com.qhm.dao;

import java.util.List;

import com.qhm.pojo.Order;
import com.qhm.pojo.OrderDetail;

public interface OrderDao {

	int addDetail(OrderDetail orderDetail);

	int add(Order order);

	/**
	 * 显示一列订单
	 * @param userId
	 * @return
	 */
	List<Order> list(int userId);

	/**
	 * 显示一个订单的详情
	 * @param orderId
	 * @return
	 */
	List<OrderDetail> listDetail(int orderId);

}
