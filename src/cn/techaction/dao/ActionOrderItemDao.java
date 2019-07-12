package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.ActionOrderItem;
import cn.techaction.pojo.Order;

public interface ActionOrderItemDao {
	//根据订单号获得订单项
	public List<ActionOrderItem> getItemsByOrderNum(Long orderNo);
	//批量插入订单项
	public int[] batchInsert(List<ActionOrderItem> orderItems);

	//逐一插入订单项
	public int SingleInsert(ActionOrderItem orderItems);
	
	
	
	/**
	 * 插入评论
	 * @author jingfh
	 * @date 2019.07.09
	 */
	public int addComment(ActionOrderItem aoi);
	/**
	 * 获取评论
	 * @author jingfh
	 * @date 2019.07.09
	 */
	public List<ActionOrderItem> findComment(Integer productId);
	
	

}
