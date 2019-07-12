package cn.techaction.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.beans.factory.parsing.AliasDefinition;
import org.springframework.stereotype.Repository;

import cn.techaction.dao.ActionOrderItemDao;
import cn.techaction.pojo.ActionOrderItem;
@Repository
public class ActionOrderItemDaoImpl implements ActionOrderItemDao {
	@Resource
	private QueryRunner queryRunner;
	private String Alias = "id,uid,order_no as orderNo,goods_id as goodsId,"
			+ "goods_name as goodsName,icon_url as iconUrl,price,quantity,"
			+ "total_price as totalPrice,created,updated,"
			+"comment as comment";
	@Override
	public List<ActionOrderItem> getItemsByOrderNum(Long orderNo) {
		// TODO Auto-generated method stub
		String sql = "select "+this.Alias+" from action_order_items where order_no = ?";
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionOrderItem>(ActionOrderItem.class),orderNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int[] batchInsert(List<ActionOrderItem> orderItems) {
		// TODO Auto-generated method stub
		String sql = "insert into action_order_items(uid,order_no,goods_id,goods_name,icon_url,price,"
				+ "quantity,total_price,created,updated) values(?,?,?,?,?,?,?,?,?,?)";
		Object[][] params = new Object[orderItems.size()][];
		for(int i=0;i<orderItems.size();i++) {
			ActionOrderItem item = orderItems.get(i);
			params[i] =new Object[] {
					item.getUid(),item.getOrderNO(),item.getGoodsId(),item.getGoodsName(),item.getIconUrl(),item.getPrice(),
					item.getQuantity(),item.getTotalPrice(),item.getCreated(),item.getUpdated()
			};
		}
		try {
			return queryRunner.batch(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int SingleInsert(ActionOrderItem item) {
		// TODO Auto-generated method stub
		String sql = "insert into action_order_items(uid,order_no,goods_id,goods_name,icon_url,price,"
				+ "quantity,total_price,created,updated) values(?,?,?,?,?,?,?,?,?,?)";
	
		Object[] params = {
				item.getUid(),item.getOrderNO(),item.getGoodsId(),item.getGoodsName(),item.getIconUrl(),item.getPrice(),
				item.getQuantity(),item.getTotalPrice(),item.getCreated(),item.getUpdated()
		};
		try {
			return queryRunner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	
	/**
	 * 插入评论
	 * @author jingfh
	 * @date 2019.07.09
	 */
	public int addComment(ActionOrderItem aoi) {
		
		String sql = "update action_order_items set comment = ?, updated = ? where uid = ? and order_no = ? and goods_id = ?";
		try {
			//String date= new Date().toString();
			return queryRunner.update(sql, aoi.getComment(), aoi.getUpdated(), aoi.getUid(), aoi.getOrderNO(), aoi.getGoodsId());
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 获取评论
	 * @author jingfh
	 * @date 2019.07.09
	 */
	public List<ActionOrderItem> findComment(Integer productId) {
		String sql = "select  "+this.Alias+" from action_order_items where goods_id = ? "+
					"order by updated desc";
		try {
			List<ActionOrderItem> rs= (List<ActionOrderItem>)queryRunner.query(sql, new BeanListHandler<ActionOrderItem>(ActionOrderItem.class),productId);
			List<String> result=new ArrayList<String>();
//			for(String str:rs) {
//				if(str!=null) result.add(str);
////System.out.println(str);
//			}
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
