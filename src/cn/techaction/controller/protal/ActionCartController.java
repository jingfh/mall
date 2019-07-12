package cn.techaction.controller.protal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.User;
import cn.techaction.service.ActionCartService;
import cn.techaction.utils.ConstUtil;
import cn.techaction.vo.ActionCartVo;

@Controller
@RequestMapping("/cart")
public class ActionCartController {
	@Autowired
	private ActionCartService actionCartService;
	//加入购物车
	@RequestMapping(value="/savecart.do")
	@ResponseBody
	public SverResponse<String> addProductCart(HttpSession session,Integer productId,Integer count){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null ) {
			return SverResponse.createByErrorMessage("请登录后，再查看购物车");
		}
		return actionCartService.saveOrUpdate(user.getId(),productId,count);
	}
	//查看购物车
	@RequestMapping(value="/findallcarts.do")
	@ResponseBody
	public SverResponse<ActionCartVo> findAllCarts(HttpSession session){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null ) {
			return SverResponse.createByErrorMessage("请登录后，再查看购物车");
		}
		return actionCartService.findAllCarts(user.getId());
	}
	//查看购物车-选中的
		@RequestMapping(value="/findallcartsChecked.do")
		@ResponseBody
		public SverResponse<ActionCartVo> findAllCartsChecked(HttpSession session){
			User user = (User) session.getAttribute(ConstUtil.CUR_USER);
			if (user == null ) {
				return SverResponse.createByErrorMessage("请登录后，再查看购物车");
			}
			return actionCartService.findAllCartsChecked(user.getId());
	}
		
	//清空购物车
	@RequestMapping("/clearcarts.do")
	@ResponseBody
	public SverResponse<String> clearCarts(HttpSession session){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null ) {
			return SverResponse.createByErrorMessage("请登录后，再查看购物车");
		}
		//清空购物车
		return actionCartService.clearCart(user.getId());
	}
	//更新购物车
	@RequestMapping("/updatecarts.do")
	@ResponseBody
	public SverResponse<ActionCartVo> updateCarts(HttpSession session,Integer productId,Integer count,Integer checked){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null ) {
			return SverResponse.createByErrorMessage("请登录后，再查看购物车");
		}
		return actionCartService.updateCart(user.getId(),productId,count,checked);
	}
	//删除购物车中的商品
	@RequestMapping("/delcarts.do")
	@ResponseBody
	public SverResponse<ActionCartVo> deleteCart(HttpSession session,Integer productId){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null ) {
			return SverResponse.createByErrorMessage("请登录后，再查看购物车");
		}
		return actionCartService.deleteCart(user.getId(),productId);
	}
	//购物车数量
	@RequestMapping("/getcartcount.do")
	@ResponseBody
	public SverResponse<Integer> getCartsCount(HttpSession session){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null ) {
			return SverResponse.createByErrorMessage("请登录后，再查看购物车");
		}
		return actionCartService.getCartCount(user.getId());
	}
}
