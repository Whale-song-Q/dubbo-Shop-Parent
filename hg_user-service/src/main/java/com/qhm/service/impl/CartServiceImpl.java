package com.qhm.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.qhm.dao.CartDao;
import com.qhm.dao.OrderDao;
import com.qhm.pojo.Cart;
import com.qhm.pojo.Order;
import com.qhm.pojo.OrderDetail;
import com.qhm.service.CartService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author qhm
 *
 */
@Service(interfaceClass=CartService.class)
public class CartServiceImpl  implements CartService {

	@Autowired
	CartDao cartDao;
	
	@Autowired
	OrderDao orderDao;

	@Override
	public int addCart(Integer uid, int skuId, int buyNum) {
		// TODO Auto-generated method stub
		// 增加一个库存数量的一个判断
		Cart cart = new Cart(uid,skuId,buyNum);
		return cartDao.add(cart);
		
	}

	@Override
	public int delCart(int[] ids) {
		// TODO Auto-generated method stub
		return cartDao.delete(ids);
	}

	@Override
	public int clearCart(int uid) {
		// TODO Auto-generated method stub
		return cartDao.clear(uid);
	}

	@Override
	public PageInfo<Cart> list(int uid, int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 5);
		
		return new PageInfo(cartDao.list(uid));
	}

	@Override
	public int createOrder(Integer uid, String address,int[] cartIds) {
		// TODO Auto-generated method stub
		//先根据购物车id生成主表的数据
		//根据购物车id 查询商品的信息
		List<Cart> cartList  = cartDao.listByIds(cartIds);
		
		//整个订单的价格
		BigDecimal sumTotal = new BigDecimal(0);
		
		int addNum = 0;
		
		//计算总价格
		for (Cart cart : cartList) {
			sumTotal=sumTotal.add(cart.getSumTotal());
		}
		
		//生成主表的数据
		Order order = new Order();
		//用户
		order.setUid(uid);
		order.setSumtotal(sumTotal);//计算总价格
		order.setAddress(address);//邮寄地址
		
		addNum += orderDao.add(order);
		
		//生成子表的数据
		for (Cart cart : cartList) {
			OrderDetail orderDetail = new OrderDetail(order.getOid(),cart);
			//插入子表
			addNum += orderDao.addDetail(orderDetail);
		}
		
		//从购物车中删除这些数据
		 cartDao.deleteByIds(cartIds);
		
		
		//成功影响数据的条数
		return addNum;
	}

	

}
