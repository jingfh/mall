package cn.techaction.vo;

import java.math.BigDecimal;

public class ActionOrderItemVo {
	private Long orderNo;
	private Integer goodsId;
	private String goodsName;
	private String iconUr1;
	private BigDecimal curPrice;
	private Integer quantity;
	private BigDecimal totalPrice;
	private String created;
	private String comment;
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getIconUr1() {
		return iconUr1;
	}
	public void setIconUr1(String iconUr1) {
		this.iconUr1 = iconUr1;
	}
	public BigDecimal getCurprice() {
		return curPrice;
	}
	public void setCurPrice(BigDecimal curPrice) {
		this.curPrice = curPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
