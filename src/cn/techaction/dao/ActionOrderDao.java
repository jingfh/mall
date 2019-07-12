package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.Address;
import cn.techaction.pojo.Order;

public interface ActionOrderDao {
	/*
	 * 获取用户订单总数（各种状态下的）
	 */
	public int getTotalRecord(Integer uid, Integer status);
	/*
	 * 获取用户订单分页列表
	 */
	public List<Order> findOrders(Integer uid, Integer status, int startIndex, int pageSize);
	//根据用户和订单号查询订单信息
	public Order findOrderByUserAndOrderNo(Integer uid, Long orderNo);
	//更新订单信息
	public int updateOrder(Order updateOrder);
	//保存订单信息
	public int insertOrder(Order order);

}
