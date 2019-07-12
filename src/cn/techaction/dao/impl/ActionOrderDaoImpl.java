package cn.techaction.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import cn.techaction.dao.ActionOrderDao;
import cn.techaction.pojo.Order;
@Repository
public class ActionOrderDaoImpl implements ActionOrderDao {
	@Resource
	private QueryRunner queryRunner;
	private String all="id,order_no as orderNo,uid,addr_id as addr,amount,type,"
			+ "freight,status,payment_time as paymentTime,delivery_time as deliveryTime,finish_time as"
			+ "finishTime,close_time as closeTime,updated as updateTime, created as createTime ";
	@Override
	public int getTotalRecord(Integer uid, Integer status) {
		// TODO Auto-generated method stub
		String sql = "select count(id) as num from action_orders where uid= ?";
		List<Object> params = new ArrayList<>();
		params.add(uid);
		if (status !=0) {
			sql+=" and status = ?";
			params.add(status);
		}
		try {
			return queryRunner.query(sql, new ColumnListHandler<Long>("num"),params.toArray()).get(0).intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Order> findOrders(Integer uid, Integer status, int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		String sql = "select "+this.all+" from action_orders where uid = ?";
		List<Object> params = new ArrayList<>();
		params.add(uid);
		if (status != 0) {
			sql+=" and status = ?";
			params.add(status);
		}
		sql+=" limit ?,? ";
		params.add(startIndex);
		params.add(pageSize);
		try {
			return queryRunner.query(sql, new BeanListHandler<Order>(Order.class),params.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Order findOrderByUserAndOrderNo(Integer uid, Long orderNo) {
		// TODO Auto-generated method stub
		String sql ="select "+this.all+" from action_orders where uid = ? and order_no = ?";
		try {
			return queryRunner.query(sql, new BeanHandler<Order>(Order.class), uid,orderNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateOrder(Order updateOrder) {
		// TODO Auto-generated method stub
		String sql = "update action_orders set updated = ?";
		List<Object> params = new ArrayList<>();
		params.add(updateOrder.getUpdateTime());
		if (updateOrder.getStatus() != null) {
			sql+=" ,status = ?";
			params.add(updateOrder.getStatus());
		}
		if (updateOrder.getPaymentTime()!=null) {
			sql+=" ,payment_time = ?";
			params.add(updateOrder.getPaymentTime());
		}
		if (updateOrder.getDeliveryTime()!=null) {
			sql+=" ,delivery_time = ?";
			params.add(updateOrder.getDeliveryTime());
		}
		if (updateOrder.getFinishTime()!=null) {
			sql+=" ,finish_time = ?";
			params.add(updateOrder.getFinishTime());
		}
		if (updateOrder.getCloseTime()!=null) {
			sql+=" ,close_time = ?";
			params.add(updateOrder.getCloseTime());
		}
		sql+=" where id = ?";
		params.add(updateOrder.getId());
		
		try {
			return queryRunner.update(sql,params.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int insertOrder(Order order) {
		// TODO Auto-generated method stub
		String sql = "insert into action_orders(order_no, uid,addr_id,amount,type,freight,status"
				+ ",payment_time,delivery_time,finish_time,close_time,updated,created) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {order.getOrderNo(),order.getUid(),order.getAddr(),order.getAmount(),order.getType()
				,order.getFreight(),order.getStatus(),order.getPaymentTime(),order.getDeliveryTime(),order.getFinishTime(),
				order.getCloseTime(),order.getUpdateTime(),order.getCreateTime()};
		try {
			return queryRunner.update(sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	
	}



}
