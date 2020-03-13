package com.qhm.service;

import java.util.List;

import com.qhm.pojo.Order;
import com.qhm.pojo.OrderDetail;
import com.github.pagehelper.PageInfo;

/**
 * 订单的服务
 * @author qhm
 *
 */
public interface OrderService {

	
	/**
	 * 查看订单
	 * @param userId
	 * @param page
	 * @return
	 */
	PageInfo<Order> list(int userId,int page);
	
	/**
	 * 查看订单详情
	 * @param orderId
	 * @param page
	 * @return
	 */
	List<OrderDetail> listDetail(int orderId,int page);
	
	
	
	

}
