package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.ActionOrderItem;
import cn.techaction.pojo.Order;

public interface ActionOrderItemDao {
	//���ݶ����Ż�ö�����
	public List<ActionOrderItem> getItemsByOrderNum(Long orderNo);
	//�������붩����
	public int[] batchInsert(List<ActionOrderItem> orderItems);

	//��һ���붩����
	public int SingleInsert(ActionOrderItem orderItems);
	
	
	
	/**
	 * ��������
	 * @author jingfh
	 * @date 2019.07.09
	 */
	public int addComment(ActionOrderItem aoi);
	/**
	 * ��ȡ����
	 * @author jingfh
	 * @date 2019.07.09
	 */
	public List<ActionOrderItem> findComment(Integer productId);
	
	

}
