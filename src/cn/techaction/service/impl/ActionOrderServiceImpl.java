package cn.techaction.service.impl;

import java.math.BigDecimal;
import java.rmi.server.UID;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

import cn.techaction.common.SverResponse;
import cn.techaction.dao.ActionAddressDao;
import cn.techaction.dao.ActionCartDao;
import cn.techaction.dao.ActionOrderDao;
import cn.techaction.dao.ActionOrderItemDao;
import cn.techaction.dao.ActionProductDao;
import cn.techaction.dao.impl.ActionOrderItemDaoImpl;
import cn.techaction.pojo.ActionCart;
import cn.techaction.pojo.ActionOrderItem;
import cn.techaction.pojo.ActionProduct;
import cn.techaction.pojo.Address;
import cn.techaction.pojo.Order;
import cn.techaction.service.ActionOrderService;
import cn.techaction.utils.CalcUtil;
import cn.techaction.utils.ConstUtil;
import cn.techaction.utils.DateUtils;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionAddressVo;
import cn.techaction.vo.ActionOrderItemVo;
import cn.techaction.vo.ActionOrderVo;
@Service
public class ActionOrderServiceImpl implements ActionOrderService {
	@Autowired
	private ActionOrderDao actionOrderDao;
	@Autowired
	private ActionProductDao actionProductDao;
	@Autowired
	private ActionAddressDao actionAddressDao;
	@Autowired
	private ActionOrderItemDao  aOrderItemDao;
	@Autowired
	private ActionCartDao aCartDao;
	@Autowired
	private ActionProductDao aProductDao;
	@Override
	public SverResponse<PageBean<ActionOrderVo>> findOrders(Integer uid, Integer status, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		//判断uid是否为空
		if (uid == null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		//查找符合条件的总记录数
		int totalRecord = actionOrderDao.getTotalRecord(uid,status);
		//创建分页封装对象
		PageBean<ActionOrderVo> pageBean  = new PageBean<>(pageNum, pageSize, totalRecord);
		//读取数据
		List<Order> orders = actionOrderDao.findOrders(uid,status,pageBean.getStartIndex(),pageSize);
		//封装vo
		List<ActionOrderVo> voList = Lists.newArrayList();
		for(Order order:orders) {
			voList.add(createOrderVo1(order,false));
		}
		pageBean.setData(voList);
		return SverResponse.createRespBySuccess(pageBean);
	}
	//封装VO
	private ActionOrderVo createOrderVo1(Order order, boolean hasAddress) {
		// TODO Auto-generated method stub
		ActionOrderVo orderVo = new ActionOrderVo();
		setNormalProperty(order,orderVo);
		setAddressProperty(order,orderVo,hasAddress);
		//设置订单项
		List<ActionOrderItem> orderItems = aOrderItemDao.getItemsByOrderNum(order.getOrderNo());
		serOrderItemProperty(orderItems,orderVo);
		return orderVo;
	}
	//封装订单项属性
	private void serOrderItemProperty(List<ActionOrderItem> orderItems, ActionOrderVo orderVo) {
		// TODO Auto-generated method stub
		List<ActionOrderItemVo> items = Lists.newArrayList();
		for(ActionOrderItem orderItem:orderItems) {
			items.add(createdOrderItemVo(orderItem));
		}
		orderVo.setOrderItems(items);
	}
	//封装订单项vo
	private ActionOrderItemVo createdOrderItemVo(ActionOrderItem orderItem) {
		// TODO Auto-generated method stub
		ActionOrderItemVo itemVo = new ActionOrderItemVo();
		itemVo.setOrderNo(orderItem.getOrderNO());
		itemVo.setGoodsId(orderItem.getGoodsId());
		itemVo.setGoodsName(orderItem.getGoodsName());
		itemVo.setIconUr1(orderItem.getIconUrl());
		itemVo.setCurPrice(orderItem.getPrice());
		itemVo.setTotalPrice(orderItem.getTotalPrice());
		itemVo.setQuantity(orderItem.getQuantity());
		itemVo.setComment(orderItem.getComment());
		return itemVo;
	}
	//封装地址属性
	private void setAddressProperty(Order order, ActionOrderVo orderVo, boolean hasAddress) {
		// TODO Auto-generated method stub
		Address address = actionAddressDao.findOrderAddrsById(order.getAddr());
		if (address != null) {
			orderVo.setDeliveryName(address.getName());
			if (hasAddress) {
				orderVo.setAddress(createAddressVo(address));
			}else {
				orderVo.setAddress(null);
			}
		}
	}
	//封装地址Vo
	private ActionAddressVo createAddressVo(Address address) {
		// TODO Auto-generated method stub
		ActionAddressVo addressVo = new ActionAddressVo();
		addressVo.setName(address.getName());
		addressVo.setMobile(address.getMobile());
		addressVo.setPhone(address.getPhone());
		addressVo.setProvince(address.getProvince());
		addressVo.setCity(address.getCity());
		addressVo.setDistrict(address.getDistrict());
		addressVo.setAddr(address.getAddr());
		addressVo.setZip(address.getZip());
		return addressVo;
	}
	private void setNormalProperty(Order order, ActionOrderVo orderVo) {
		// TODO Auto-generated method stub
		orderVo.setOrderNum(order.getOrderNo());
		orderVo.setAmount(order.getAmount());
		orderVo.setType(order.getType());
		orderVo.setFreight(order.getFreight());
		orderVo.setStatus(order.getStatus());
		orderVo.setStatusDesc(ConstUtil.OrderStatus.getStatusDesc(order.getStatus()));
		orderVo.setAddrId(order.getAddr());
		//time
		orderVo.setPaymentTime(DateUtils.date2Str(order.getPaymentTime()));
		orderVo.setDeliveryTime(DateUtils.date2Str(order.getDeliveryTime()));
		orderVo.setFinishTime(DateUtils.date2Str(order.getFinishTime()));
		orderVo.setCloseTime(DateUtils.date2Str(order.getCloseTime()));
		orderVo.setCreated(DateUtils.date2Str(order.getCreateTime()));
	}
	@Override
	public SverResponse<String> cancelOrder(Integer uid, Long orderNo) {
		//查询订单
		Order order = actionOrderDao.findOrderByUserAndOrderNo(uid,orderNo);
		//判断订单是否存在
		if (order == null) {
			return SverResponse.createByErrorMessage("该订单不存在或被删除");
		}
		//订单是否已付款
		if (order.getStatus() == ConstUtil.OrderStatus.ORDER_PAID) {
			return SverResponse.createByErrorMessage("该订单已经付款，无法取消");
		}
		//判断状态 修改订单信息
		Order updateOrder = new Order();
		updateOrder.setId(order.getId());
		updateOrder.setUpdateTime(new Date());
		if (order.getStatus() == 1) {
			updateOrder.setCloseTime(new Date());
			updateOrder.setStatus(ConstUtil.OrderStatus.ORDER_CANCELED);
			int row = actionOrderDao.updateOrder(updateOrder);
			if (row > 0) {
				return SverResponse.createRespBySuccessMessage("订单已经被取消");
			}
		}
		if (order.getStatus() == 3) {
			updateOrder.setFinishTime(new Date());
			updateOrder.setStatus(ConstUtil.OrderStatus.ORDER_SUCCESS);
			int row = actionOrderDao.updateOrder(updateOrder);
			if (row > 0) {
				return SverResponse.createRespBySuccessMessage("订单已经确认收货");
			}
		}
		return SverResponse.createByErrorMessage("修改订单状态——>已取消 失败");
	}
	/*
	 * 修改订单状态为已支付
	 * @author jingfh
	 * @date 0704
	 */
	@Override
	public SverResponse<String> payOrder(Integer uid, Long orderNo) {
		//查询订单
		Order order = actionOrderDao.findOrderByUserAndOrderNo(uid,orderNo);
		//判断订单是否存在
		if (order == null) {
			return SverResponse.createByErrorMessage("该订单不存在或被删除");
		}
		//订单是否已取消
		if (order.getStatus() == ConstUtil.OrderStatus.ORDER_CANCELED) {
			return SverResponse.createByErrorMessage("该订单已经取消，无法付款");
		}
		//判断状态修改订单信息
		Order updateOrder = new Order();
		updateOrder.setId(order.getId());
		updateOrder.setUpdateTime(new Date());
		if (order.getStatus() == 1) {
			updateOrder.setPaymentTime(new Date());
			updateOrder.setStatus(ConstUtil.OrderStatus.ORDER_PAID);
			int row = actionOrderDao.updateOrder(updateOrder);
			if (row > 0) {
				return SverResponse.createRespBySuccessMessage("订单已支付");
			}
		}
		return SverResponse.createByErrorMessage("修改订单状态——已支付 失败");
	}
	@Override
	public SverResponse<ActionOrderVo> findOrderDetail(Integer uid, Long orderNo) {
		// TODO Auto-generated method stub
		//判断参数是否正确
		if (uid == null || orderNo == null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		//查询订单，封装
		Order order = actionOrderDao.findOrderByUserAndOrderNo(uid, orderNo);
		if (order == null) {
			return SverResponse.createByErrorMessage("该订单不存在或已删除");
		}
		ActionOrderVo orderVo = createOrderVo1(order, true);
		return SverResponse.createRespBySuccess(orderVo);
	}
	@Override
	public SverResponse<ActionOrderVo> generateOrderChecked(Integer uid, Integer addrId) {
		// 提取购物车中信息
		List<ActionCart> carts = aCartDao.findCartByUserChecked(uid);
		//计算购物车中每件商品价格并生成订单项
		SverResponse res = this.cart2OrderItem(uid,carts);
		if (!res.isSuccess()) {
			return res;
		}
		//去除订单项中的价格计算订单总价格
		List<ActionOrderItem> orderItems = (List<ActionOrderItem>) res.getData();
		BigDecimal totalPrice = this.calcOrderTotalPrice(orderItems);
		//生成订单，插入数据
//System.out.println(addrId);
		Order order = saveOrder(uid,addrId,totalPrice);
		if (order == null) {
			return SverResponse.createByErrorMessage("订单产生错误，请检查后重新提交");
		}
		if (CollectionUtils.isEmpty(orderItems)) {
			return SverResponse.createByErrorMessage("订单项为空，请选择要购买的商品");
		}
		//批量处理插入订单项
		for (ActionOrderItem OrderItem : orderItems) {
			//为订单项设置订单主键
			OrderItem.setOrderNO(order.getOrderNo());
		}
		aOrderItemDao.batchInsert(orderItems);
		//减少商品表中库存
		for (ActionOrderItem actionOrderItem : orderItems) {
			//减少库存
			ActionProduct product = aProductDao.findProductsById(actionOrderItem.getGoodsId());
			product.setStock(product.getStock() - actionOrderItem.getQuantity());
			product.setUpdated(new Date());
			//更新库存
			aProductDao.updateProduct(product);
		}
		//清空购物车
		aProductDao.deleteCartProduct(uid);
		//封装返回前端
		ActionOrderVo orderVo = createOrderVo2(order, orderItems);
		
		return SverResponse.createRespBySuccess(orderVo);
	}
	
	
	/*
	 * @author jingfh
	 * @data 2019.07.07
	 */
	@Override
	public SverResponse<ActionOrderVo> generateOrderProduct(Integer uid, Integer addrId,Integer productId,Integer quantity) {
		
		//查询商品详情
		ActionProduct product = actionProductDao.findProductsById(productId);
		BigDecimal totalPrice = new BigDecimal("0");
		totalPrice = CalcUtil.add(totalPrice.doubleValue(), product.getPrice().doubleValue()*quantity.doubleValue());
		
		Order order = saveOrder(uid,addrId,totalPrice);
		if (order == null) {
			return SverResponse.createByErrorMessage("订单产生错误，请检查后重新提交");
		}
		ActionOrderItem orderItem = new ActionOrderItem();
		orderItem.setUid(uid);
		orderItem.setGoodsName(product.getName());
		orderItem.setGoodsId(product.getId());
		orderItem.setIconUrl(product.getIcon_url());
		orderItem.setPrice(product.getPrice());
		orderItem.setQuantity(quantity);
		orderItem.setTotalPrice(totalPrice);
		orderItem.setCreated(new Date());
		orderItem.setUpdated(new Date());
		
		//为订单项设置订单主键
		orderItem.setOrderNO(order.getOrderNo());
		//插入订单
		aOrderItemDao.SingleInsert(orderItem);
		//减少商品表中库存	
		product.setStock(product.getStock() - quantity);
		product.setUpdated(new Date());
		aProductDao.updateProduct(product);
		
		//封装返回前端
		ActionOrderVo orderVo = createOrderVo1(order, true);
		
		return SverResponse.createRespBySuccess(orderVo);
	}
	
	
	
	//封装订单Vo
	private ActionOrderVo createOrderVo2(Order order, List<ActionOrderItem> orderItems) {
		// TODO Auto-generated method stub
		ActionOrderVo orderVo = new ActionOrderVo();
		setNormalProperty(order,orderVo);
		setAddressProperty(order,orderVo,true);
		//设置订单项
		serOrderItemProperty(orderItems,orderVo);
		return orderVo;
	}
	//保存订单
	private Order saveOrder(Integer uid, Integer addrId, BigDecimal totalPrice) {
		// TODO Auto-generated method stub
		Order order = new Order();
		//生成订单号
		Long currentTime = System.currentTimeMillis();
		Long orderNo = currentTime + new Random().nextInt(100);
		order.setOrderNo(orderNo);
		order.setStatus(ConstUtil.OrderStatus.ORDER_NO_PAY);
		order.setType(ConstUtil.PaymentType.PAY_ON_LINE);
		order.setFreight(0);
		order.setAmount(totalPrice);
		order.setAddr(addrId);
		System.out.println(addrId);
		order.setUid(uid);
		order.setUpdateTime(new Date());
		order.setCreateTime(new Date());
		//插入订单
		int rs = actionOrderDao.insertOrder(order);
		if (rs > 0) {
			return order;
		}
		return null;
	}
	//计算订单总价格
	private BigDecimal calcOrderTotalPrice(List<ActionOrderItem> orderItems) {
		// TODO Auto-generated method stub
		BigDecimal totalPrice = new BigDecimal("0");
		for (ActionOrderItem item:orderItems) {
			totalPrice = CalcUtil.add(totalPrice.doubleValue(), item.getTotalPrice().doubleValue());
		}
		return totalPrice;
	}
	//将购物车中商品封装为订单项
	private SverResponse cart2OrderItem(Integer uid, List<ActionCart> carts) {
		// TODO Auto-generated method stub
		List<ActionOrderItem> items = Lists.newArrayList();
		//判断购物车是否为空
		if (CollectionUtils.isEmpty(carts)) {
			return SverResponse.createByErrorMessage("购物车为空，请选择要购买的商品");
		}
		for(ActionCart cart:carts) {
			//查看购物车的商品状态
			ActionProduct product = aProductDao.findProductsById(cart.getProductId());
			//产看商品装填
			if (ConstUtil.ProductStatus.STATUS_ON_SALE != product.getStatus()) {
				//如果商品未上架在售，则返回提示信息
				return SverResponse.createByErrorMessage("商品"+product.getName() +"已下架，不能在线购买");
			}
			//产看库存
			if (cart.getQuantity() > product.getStock()) {
				return SverResponse.createByErrorMessage("商品"+product.getName() +"库存不足");
			}
			//封装订单项
			ActionOrderItem orderItem = new ActionOrderItem();
			orderItem.setUid(uid);
			orderItem.setGoodsName(product.getName());
			orderItem.setGoodsId(product.getId());
			orderItem.setIconUrl(product.getIcon_url());
			orderItem.setPrice(product.getPrice());
			orderItem.setQuantity(cart.getQuantity());
			orderItem.setTotalPrice(CalcUtil.mul(orderItem.getPrice().doubleValue(), orderItem.getQuantity().doubleValue()));
			orderItem.setCreated(new Date());
			orderItem.setUpdated(new Date());
			items.add(orderItem);
		}
		return SverResponse.createRespBySuccess(items);
	}
}
