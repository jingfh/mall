package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.Address;
import cn.techaction.pojo.Order;

public interface ActionOrderDao {
	/*
	 * ��ȡ�û���������������״̬�µģ�
	 */
	public int getTotalRecord(Integer uid, Integer status);
	/*
	 * ��ȡ�û�������ҳ�б�
	 */
	public List<Order> findOrders(Integer uid, Integer status, int startIndex, int pageSize);
	//�����û��Ͷ����Ų�ѯ������Ϣ
	public Order findOrderByUserAndOrderNo(Integer uid, Long orderNo);
	//���¶�����Ϣ
	public int updateOrder(Order updateOrder);
	//���涩����Ϣ
	public int insertOrder(Order order);

}
