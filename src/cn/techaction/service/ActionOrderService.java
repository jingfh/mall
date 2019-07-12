package cn.techaction.service;

import cn.techaction.common.SverResponse;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionOrderVo;

public interface ActionOrderService {
	/*
	 * ������ҳ�б�
	 */
	public SverResponse<PageBean<ActionOrderVo>> findOrders(Integer uid, Integer status, int pageNum, int pageSize);
	//ȡ������
	public SverResponse<String> cancelOrder(Integer id, Long orderNo);
	
	
	//ȡ������
	public SverResponse<String> payOrder(Integer id, Long orderNo);
	
	//���ݱ�Ż�ȡ��������
	public SverResponse<ActionOrderVo> findOrderDetail(Integer uid, Long orderNo);
	//���ɶ���
	public SverResponse<ActionOrderVo> generateOrderChecked(Integer id, Integer addrId);
	//���ɶ���
	public SverResponse<ActionOrderVo> generateOrderProduct(Integer id, Integer addrId,Integer productId,Integer quantity);

}
