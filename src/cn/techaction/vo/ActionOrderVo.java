package cn.techaction.vo;

import java.math.BigDecimal;
import java.util.List;

public class ActionOrderVo {
	
	private Long orderNum;      //�������
	private BigDecimal amount;  //�����۸�
	private Integer type;       //֧������
	private Integer freight;    //�ʷ�
	private Integer status;		//����״̬
	private String statusDesc;	//��������״̬
	private String paymentTime;	//֧��ʱ��
	private String deliveryTime;//�ʼ�ʱ��
	private String finishTime;	//�������ʱ��
	private String closeTime;	//�����ر�ʱ��
	private String created;		//��������ʱ��
	
	private List<ActionOrderItemVo> orderItems;//��������
	private Integer addrId;		//�ջ��˵�ַid
	private String deliveryName;//�ջ�������
	private ActionAddressVo address;//�ջ�������
	public Long getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getFreight() {
		return freight;
	}
	public void setFreight(Integer freight) {
		this.freight = freight;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public List<ActionOrderItemVo> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<ActionOrderItemVo> orderItems) {
		this.orderItems = orderItems;
	}
	public Integer getAddrId() {
		return addrId;
	}
	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public ActionAddressVo getAddress() {
		return address;
	}
	public void setAddress(ActionAddressVo address) {
		this.address = address;
	}
	
	
}
