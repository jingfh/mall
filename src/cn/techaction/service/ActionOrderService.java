package cn.techaction.service;

import cn.techaction.common.SverResponse;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionOrderVo;

public interface ActionOrderService {
	/*
	 * 订单分页列表
	 */
	public SverResponse<PageBean<ActionOrderVo>> findOrders(Integer uid, Integer status, int pageNum, int pageSize);
	//取消订单
	public SverResponse<String> cancelOrder(Integer id, Long orderNo);
	
	
	//取消订单
	public SverResponse<String> payOrder(Integer id, Long orderNo);
	
	//根据编号获取订单详情
	public SverResponse<ActionOrderVo> findOrderDetail(Integer uid, Long orderNo);
	//生成订单
	public SverResponse<ActionOrderVo> generateOrderChecked(Integer id, Integer addrId);
	//生成订单
	public SverResponse<ActionOrderVo> generateOrderProduct(Integer id, Integer addrId,Integer productId,Integer quantity);

}
