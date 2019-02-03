package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IOrdersBiz;
import cn.itcast.erp.dao.IOrdersDao;
import cn.itcast.erp.entity.Orderdetail;
import cn.itcast.erp.entity.Orders;

import java.util.Date;

/**
 * 订单业务逻辑类
 * @author Administrator
 *
 */
public class OrdersBiz extends BaseBiz<Orders> implements IOrdersBiz {

	private IOrdersDao ordersDao;
	
	public void setOrdersDao(IOrdersDao ordersDao) {
		this.ordersDao = ordersDao;
		super.setBaseDao(this.ordersDao);
	}

	@Override
	public void add(Orders orders) {
		//1. 设置订单的状态
		orders.setState(Orders.STATE_CREATE);
		//2. 订单的类型
		orders.setType(Orders.TYPE_IN);
		//3. 下单时间
		orders.setCreatetime(new Date());

		// 合计金额
		double total = 0;

		for (Orderdetail detail : orders.getOrderDetails()) {
			//累计金额
			total += detail.getMoney();
			//明细的状态
			detail.setState(Orderdetail.STATE_NOT_IN);
			//跟订单的关系
			detail.setOrders(orders);
		}
		//设置订单总金额
		orders.setTotalmoney(total);

		//保存到DB
		ordersDao.add(orders);
	}
}
