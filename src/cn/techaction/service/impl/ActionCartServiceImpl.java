package cn.techaction.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.techaction.common.SverResponse;
import cn.techaction.dao.ActionCartDao;
import cn.techaction.dao.ActionProductDao;
import cn.techaction.pojo.ActionCart;
import cn.techaction.pojo.ActionProduct;
import cn.techaction.service.ActionCartService;
import cn.techaction.utils.CalcUtil;
import cn.techaction.vo.ActionCartVo;
import cn.techaction.vo.ActionCartsListVo;
@Service
public class ActionCartServiceImpl implements ActionCartService {
	@Autowired
	private ActionCartDao actionCartDao;
	@Autowired
	private ActionProductDao actionProductDao;
	@Override
	public SverResponse<String> saveOrUpdate(Integer uid, Integer productId, Integer count) {
		// TODO Auto-generated method stub
		//验证参数是否正确
		if (uid == null || productId == null || count == null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		//查看用户的购物车是否存在商品
		ActionCart actionCart = actionCartDao.findCartByUserAndProduct(uid,productId);
		if (actionCart==null) {
			//不存在则新增
			ActionCart cart = new ActionCart();
			cart.setUserId(uid);
			cart.setProductId(productId);
			cart.setQuantity(count);
			cart.setCreated(new Date());
			cart.setUpdated(new Date());
			actionCartDao.insertCart(cart);
		}else {
			//存在则数量增加
			int cartCount = actionCart.getQuantity()+count;
			actionCart.setQuantity(cartCount);
			actionCartDao.updateCartById(actionCart);
		}
		return SverResponse.createRespBySuccessMessage("商品已成功加入购物车");
	}
	@Override
	public SverResponse<ActionCartVo> findAllCarts(Integer userid) {
		// TODO Auto-generated method stub
		if (userid == null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		//查找用户购物车中的商品
		List<ActionCart> list = actionCartDao.findCartByUser(userid);
		//封装actioncartvo对象
		ActionCartVo cartVo = creatCartVo(list);
		return SverResponse.createRespBySuccess(cartVo);
	}
	public SverResponse<ActionCartVo> findAllCartsChecked(Integer userid) {
		// TODO Auto-generated method stub
		if (userid == null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		//查找用户购物车中的商品
		List<ActionCart> list = actionCartDao.findCartByUserChecked(userid);
		//封装actioncartvo对象
		ActionCartVo cartVo = creatCartVo(list);
		return SverResponse.createRespBySuccess(cartVo);
	}
	//封装购物车Vo对象
	private ActionCartVo creatCartVo(List<ActionCart> carts) {
		// TODO Auto-generated method stub
		ActionCartVo cartVo = new ActionCartVo();
		List<ActionCartsListVo> list = Lists.newArrayList();
		//购物车商品总价格
		BigDecimal cartTotalPrice = new BigDecimal("0");
		if (CollectionUtils.isNotEmpty(carts)) {
			for (ActionCart cart : carts) {
				//转换对象
				ActionCartsListVo listVo = new ActionCartsListVo();
				listVo.setId(cart.getId());
				listVo.setUserId(cart.getUserId());
				listVo.setProductId(cart.getProductId());
				listVo.setChecked(cart.getChecked());
				//封装商品信息
				ActionProduct product = actionProductDao.findProductsById(listVo.getProductId());
				if (product != null) {
					listVo.setName(product.getName());
					listVo.setStatus(product.getStatus());
					listVo.setPrice(product.getPrice());
					listVo.setStock(product.getStock());
					listVo.setIconUrl(product.getIcon_url());
					//判断库存
					int buyCount = 0;
					if (product.getStock() >= cart.getQuantity()) {
						buyCount = cart.getQuantity();
					}else {
						buyCount = product.getStock();
						//更新购物车商品数量
						ActionCart updateCart = new ActionCart();
						updateCart.setId(cart.getId());
						updateCart.setQuantity(buyCount);
						//更新状态
						updateCart.setChecked(cart.getChecked());
						actionCartDao.updateCartById(updateCart);
					}
					listVo.setQuantity(buyCount);
					//计算购物车中某商品总价格
					BigDecimal totalPrice = CalcUtil.mul(listVo.getPrice().doubleValue(), listVo.getQuantity().doubleValue());
					listVo.setTotalPrice(totalPrice);
					if (cart.getChecked() == 1) {
						//计算购物车中选中状态商品的总价格

//System.out.println("当前总价格="+cartTotalPrice.doubleValue()+"，\n 各商品价格="+listVo.getTotalPrice().doubleValue());
						cartTotalPrice = CalcUtil.add(cartTotalPrice.doubleValue(), listVo.getTotalPrice().doubleValue());
//System.out.println("，\n 总价格="+cartTotalPrice);
					}
				}
				list.add(listVo);
			}
		}
		cartVo.setLists(list);
		cartVo.setTotalPrice(cartTotalPrice);
		return cartVo;
	}
	@Override
	public SverResponse<String> clearCart(Integer userid) {
		// TODO Auto-generated method stub
		if (userid == null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		//清空购物车，判断正确
		int rs = actionCartDao.deleteCartByUserId(userid);
		if (rs > 0) {
			return SverResponse.createRespBySuccessMessage("成功清空购物车");
			
		}
		return SverResponse.createByErrorMessage("清空购物车失败");
	}
	@Override
	public SverResponse<ActionCartVo> updateCart(Integer userid, Integer productId, Integer count, Integer checked) {
		// TODO Auto-generated method stub
		if (userid == null || productId == null || count == null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		//更新购物车信息
		ActionCart actionCart = new ActionCart();
		actionCart.setUserId(userid);
		actionCart.setProductId(productId);
		actionCart.setQuantity(count);
		actionCart.setChecked(checked);
		actionCartDao.updateCartByUserIdAndProductId(actionCart);
		//返回所有购物车信息
		return findAllCarts(userid);
	}
	@Override
	public SverResponse<ActionCartVo> deleteCart(Integer userid, Integer productId) {
		// TODO Auto-generated method stub
		if (userid == null || productId == null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		//删除商品
		int rs = actionCartDao.deleteCarts(userid,productId);
		if (rs > 0) {
			//返回所有购物车信息
			return this.findAllCarts(userid);
		}
		return SverResponse.createByErrorMessage("删除商品失败");
	}
	@Override
	public SverResponse<Integer> getCartCount(Integer userid) {
		// TODO Auto-generated method stub
		if (userid == null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		int count = actionCartDao.getCartCountByUserId(userid);
		return SverResponse.createRespBySuccess(Integer.valueOf(count));
	}

}
