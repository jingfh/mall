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
		//�ж�uid�Ƿ�Ϊ��
		if (uid == null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//���ҷ����������ܼ�¼��
		int totalRecord = actionOrderDao.getTotalRecord(uid,status);
		//������ҳ��װ����
		PageBean<ActionOrderVo> pageBean  = new PageBean<>(pageNum, pageSize, totalRecord);
		//��ȡ����
		List<Order> orders = actionOrderDao.findOrders(uid,status,pageBean.getStartIndex(),pageSize);
		//��װvo
		List<ActionOrderVo> voList = Lists.newArrayList();
		for(Order order:orders) {
			voList.add(createOrderVo1(order,false));
		}
		pageBean.setData(voList);
		return SverResponse.createRespBySuccess(pageBean);
	}
	//��װVO
	private ActionOrderVo createOrderVo1(Order order, boolean hasAddress) {
		// TODO Auto-generated method stub
		ActionOrderVo orderVo = new ActionOrderVo();
		setNormalProperty(order,orderVo);
		setAddressProperty(order,orderVo,hasAddress);
		//���ö�����
		List<ActionOrderItem> orderItems = aOrderItemDao.getItemsByOrderNum(order.getOrderNo());
		serOrderItemProperty(orderItems,orderVo);
		return orderVo;
	}
	//��װ����������
	private void serOrderItemProperty(List<ActionOrderItem> orderItems, ActionOrderVo orderVo) {
		// TODO Auto-generated method stub
		List<ActionOrderItemVo> items = Lists.newArrayList();
		for(ActionOrderItem orderItem:orderItems) {
			items.add(createdOrderItemVo(orderItem));
		}
		orderVo.setOrderItems(items);
	}
	//��װ������vo
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
	//��װ��ַ����
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
	//��װ��ַVo
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
		//��ѯ����
		Order order = actionOrderDao.findOrderByUserAndOrderNo(uid,orderNo);
		//�ж϶����Ƿ����
		if (order == null) {
			return SverResponse.createByErrorMessage("�ö��������ڻ�ɾ��");
		}
		//�����Ƿ��Ѹ���
		if (order.getStatus() == ConstUtil.OrderStatus.ORDER_PAID) {
			return SverResponse.createByErrorMessage("�ö����Ѿ�����޷�ȡ��");
		}
		//�ж�״̬ �޸Ķ�����Ϣ
		Order updateOrder = new Order();
		updateOrder.setId(order.getId());
		updateOrder.setUpdateTime(new Date());
		if (order.getStatus() == 1) {
			updateOrder.setCloseTime(new Date());
			updateOrder.setStatus(ConstUtil.OrderStatus.ORDER_CANCELED);
			int row = actionOrderDao.updateOrder(updateOrder);
			if (row > 0) {
				return SverResponse.createRespBySuccessMessage("�����Ѿ���ȡ��");
			}
		}
		if (order.getStatus() == 3) {
			updateOrder.setFinishTime(new Date());
			updateOrder.setStatus(ConstUtil.OrderStatus.ORDER_SUCCESS);
			int row = actionOrderDao.updateOrder(updateOrder);
			if (row > 0) {
				return SverResponse.createRespBySuccessMessage("�����Ѿ�ȷ���ջ�");
			}
		}
		return SverResponse.createByErrorMessage("�޸Ķ���״̬����>��ȡ�� ʧ��");
	}
	/*
	 * �޸Ķ���״̬Ϊ��֧��
	 * @author jingfh
	 * @date 0704
	 */
	@Override
	public SverResponse<String> payOrder(Integer uid, Long orderNo) {
		//��ѯ����
		Order order = actionOrderDao.findOrderByUserAndOrderNo(uid,orderNo);
		//�ж϶����Ƿ����
		if (order == null) {
			return SverResponse.createByErrorMessage("�ö��������ڻ�ɾ��");
		}
		//�����Ƿ���ȡ��
		if (order.getStatus() == ConstUtil.OrderStatus.ORDER_CANCELED) {
			return SverResponse.createByErrorMessage("�ö����Ѿ�ȡ�����޷�����");
		}
		//�ж�״̬�޸Ķ�����Ϣ
		Order updateOrder = new Order();
		updateOrder.setId(order.getId());
		updateOrder.setUpdateTime(new Date());
		if (order.getStatus() == 1) {
			updateOrder.setPaymentTime(new Date());
			updateOrder.setStatus(ConstUtil.OrderStatus.ORDER_PAID);
			int row = actionOrderDao.updateOrder(updateOrder);
			if (row > 0) {
				return SverResponse.createRespBySuccessMessage("������֧��");
			}
		}
		return SverResponse.createByErrorMessage("�޸Ķ���״̬������֧�� ʧ��");
	}
	@Override
	public SverResponse<ActionOrderVo> findOrderDetail(Integer uid, Long orderNo) {
		// TODO Auto-generated method stub
		//�жϲ����Ƿ���ȷ
		if (uid == null || orderNo == null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//��ѯ��������װ
		Order order = actionOrderDao.findOrderByUserAndOrderNo(uid, orderNo);
		if (order == null) {
			return SverResponse.createByErrorMessage("�ö��������ڻ���ɾ��");
		}
		ActionOrderVo orderVo = createOrderVo1(order, true);
		return SverResponse.createRespBySuccess(orderVo);
	}
	@Override
	public SverResponse<ActionOrderVo> generateOrderChecked(Integer uid, Integer addrId) {
		// ��ȡ���ﳵ����Ϣ
		List<ActionCart> carts = aCartDao.findCartByUserChecked(uid);
		//���㹺�ﳵ��ÿ����Ʒ�۸����ɶ�����
		SverResponse res = this.cart2OrderItem(uid,carts);
		if (!res.isSuccess()) {
			return res;
		}
		//ȥ���������еļ۸���㶩���ܼ۸�
		List<ActionOrderItem> orderItems = (List<ActionOrderItem>) res.getData();
		BigDecimal totalPrice = this.calcOrderTotalPrice(orderItems);
		//���ɶ�������������
//System.out.println(addrId);
		Order order = saveOrder(uid,addrId,totalPrice);
		if (order == null) {
			return SverResponse.createByErrorMessage("����������������������ύ");
		}
		if (CollectionUtils.isEmpty(orderItems)) {
			return SverResponse.createByErrorMessage("������Ϊ�գ���ѡ��Ҫ�������Ʒ");
		}
		//����������붩����
		for (ActionOrderItem OrderItem : orderItems) {
			//Ϊ���������ö�������
			OrderItem.setOrderNO(order.getOrderNo());
		}
		aOrderItemDao.batchInsert(orderItems);
		//������Ʒ���п��
		for (ActionOrderItem actionOrderItem : orderItems) {
			//���ٿ��
			ActionProduct product = aProductDao.findProductsById(actionOrderItem.getGoodsId());
			product.setStock(product.getStock() - actionOrderItem.getQuantity());
			product.setUpdated(new Date());
			//���¿��
			aProductDao.updateProduct(product);
		}
		//��չ��ﳵ
		aProductDao.deleteCartProduct(uid);
		//��װ����ǰ��
		ActionOrderVo orderVo = createOrderVo2(order, orderItems);
		
		return SverResponse.createRespBySuccess(orderVo);
	}
	
	
	/*
	 * @author jingfh
	 * @data 2019.07.07
	 */
	@Override
	public SverResponse<ActionOrderVo> generateOrderProduct(Integer uid, Integer addrId,Integer productId,Integer quantity) {
		
		//��ѯ��Ʒ����
		ActionProduct product = actionProductDao.findProductsById(productId);
		BigDecimal totalPrice = new BigDecimal("0");
		totalPrice = CalcUtil.add(totalPrice.doubleValue(), product.getPrice().doubleValue()*quantity.doubleValue());
		
		Order order = saveOrder(uid,addrId,totalPrice);
		if (order == null) {
			return SverResponse.createByErrorMessage("����������������������ύ");
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
		
		//Ϊ���������ö�������
		orderItem.setOrderNO(order.getOrderNo());
		//���붩��
		aOrderItemDao.SingleInsert(orderItem);
		//������Ʒ���п��	
		product.setStock(product.getStock() - quantity);
		product.setUpdated(new Date());
		aProductDao.updateProduct(product);
		
		//��װ����ǰ��
		ActionOrderVo orderVo = createOrderVo1(order, true);
		
		return SverResponse.createRespBySuccess(orderVo);
	}
	
	
	
	//��װ����Vo
	private ActionOrderVo createOrderVo2(Order order, List<ActionOrderItem> orderItems) {
		// TODO Auto-generated method stub
		ActionOrderVo orderVo = new ActionOrderVo();
		setNormalProperty(order,orderVo);
		setAddressProperty(order,orderVo,true);
		//���ö�����
		serOrderItemProperty(orderItems,orderVo);
		return orderVo;
	}
	//���涩��
	private Order saveOrder(Integer uid, Integer addrId, BigDecimal totalPrice) {
		// TODO Auto-generated method stub
		Order order = new Order();
		//���ɶ�����
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
		//���붩��
		int rs = actionOrderDao.insertOrder(order);
		if (rs > 0) {
			return order;
		}
		return null;
	}
	//���㶩���ܼ۸�
	private BigDecimal calcOrderTotalPrice(List<ActionOrderItem> orderItems) {
		// TODO Auto-generated method stub
		BigDecimal totalPrice = new BigDecimal("0");
		for (ActionOrderItem item:orderItems) {
			totalPrice = CalcUtil.add(totalPrice.doubleValue(), item.getTotalPrice().doubleValue());
		}
		return totalPrice;
	}
	//�����ﳵ����Ʒ��װΪ������
	private SverResponse cart2OrderItem(Integer uid, List<ActionCart> carts) {
		// TODO Auto-generated method stub
		List<ActionOrderItem> items = Lists.newArrayList();
		//�жϹ��ﳵ�Ƿ�Ϊ��
		if (CollectionUtils.isEmpty(carts)) {
			return SverResponse.createByErrorMessage("���ﳵΪ�գ���ѡ��Ҫ�������Ʒ");
		}
		for(ActionCart cart:carts) {
			//�鿴���ﳵ����Ʒ״̬
			ActionProduct product = aProductDao.findProductsById(cart.getProductId());
			//������Ʒװ��
			if (ConstUtil.ProductStatus.STATUS_ON_SALE != product.getStatus()) {
				//�����Ʒδ�ϼ����ۣ��򷵻���ʾ��Ϣ
				return SverResponse.createByErrorMessage("��Ʒ"+product.getName() +"���¼ܣ��������߹���");
			}
			//�������
			if (cart.getQuantity() > product.getStock()) {
				return SverResponse.createByErrorMessage("��Ʒ"+product.getName() +"��治��");
			}
			//��װ������
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
