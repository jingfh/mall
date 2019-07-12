package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.ActionCart;

public interface ActionCartDao {
	//查看用户购物车中的商品信息
	public List<ActionCart> findCartByUser(Integer uid);
	//查看用户购物车中的商品信息
	public List<ActionCart> findCartByUserChecked(Integer uid);
	//根据用户id和产品id查询购物车
	public ActionCart findCartByUserAndProduct(Integer uid, Integer productId);
	//保存购物车
	public int insertCart(ActionCart cart);
	//更新商品购物车的数量
	public int updateCartById(ActionCart actionCart);
	//删除某个用户购物车中的所有商品
	public int deleteCartByUserId(Integer userid);
	//更新购物车中的商品数量
	public int updateCartByUserIdAndProductId(ActionCart actionCart);
	//删除购物车中的商品信息
	public int deleteCarts(Integer userid, Integer productId);
	//获取当前用户购物车中的商品数量
	public int getCartCountByUserId(Integer userid);
}
