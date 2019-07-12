package cn.techaction.service;

import cn.techaction.common.SverResponse;
import cn.techaction.vo.ActionCartVo;

public interface ActionCartService {
	//保存商品信息到购物车
	public SverResponse<String> saveOrUpdate(Integer uid, Integer productId, Integer count);
	//查询用户购物车中商品信息-已选中的
	public SverResponse<ActionCartVo> findAllCartsChecked(Integer userid);
	//查询用户购物车中商品信息
	public SverResponse<ActionCartVo> findAllCarts(Integer userid);
	//清空购物车
	public SverResponse<String> clearCart(Integer userid);
	//更新购物车中商品的梳理
	public SverResponse<ActionCartVo> updateCart(Integer userid, Integer productId, Integer count, Integer checked);
	//删除购物车中的商品信息
	public SverResponse<ActionCartVo> deleteCart(Integer userid, Integer productId);
	//获取用户购物车中商品个数
	public SverResponse<Integer> getCartCount(Integer userid);
	
}
