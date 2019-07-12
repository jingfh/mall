package cn.techaction.controller.protal;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.techaction.common.SverResponse;
import cn.techaction.pojo.ActionOrderItem;
import cn.techaction.pojo.User;
import cn.techaction.service.ActionOrderService;
import cn.techaction.service.ActionProductService;
import cn.techaction.utils.ConstUtil;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionOrderVo;

@Controller
@RequestMapping("/order")
public class ActionOrderController {
	@Autowired
	private ActionOrderService actionOrderService ;
	
	@Autowired
	private ActionProductService actionProductService;
	
	//获取订单列表
	@RequestMapping("/getlist.do")
	@ResponseBody
	public SverResponse<PageBean<ActionOrderVo>> list(HttpSession session,Integer status,
			@RequestParam(value="pageNum",defaultValue="1") int pageNum,
			@RequestParam(value="pageSize",defaultValue="10")int pageSize){
		//判断用户是否已经登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作");
		}
		return actionOrderService.findOrders(user.getId(),status,pageNum,pageSize);
	}
	//取消订单
	@RequestMapping("/cancelorder.do")
	@ResponseBody
	public SverResponse<String> cancelOrder(HttpSession session,Long orderNo){
		//判断用户是否已经登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作");
		}
		return actionOrderService.cancelOrder(user.getId(),orderNo);
	}
	//确认收货
	@RequestMapping("/confirmreceipt.do")
	@ResponseBody
	public SverResponse<String> confirmReceipt(HttpSession session,Long orderNo){
		//判断用户是否已经登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作");
		}
		return actionOrderService.cancelOrder(user.getId(),orderNo);
	}
	
	/*
	 * 加上修改数据库为收获状态
	 */
	//确认收货
	@RequestMapping("/payedorder.do")
	@ResponseBody
	public SverResponse<String> payedOrder(HttpSession session,Long orderNo){
		//判断用户是否已经登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作");
		}
		return actionOrderService.payOrder(user.getId(),orderNo);
	}
	
	
	//获取订单详细信息
	@RequestMapping("/getdetail.do")
	@ResponseBody
	public SverResponse<ActionOrderVo> getDetail(HttpSession session,Long orderNo){
		//判断用户是否已经登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作");
		}
		return actionOrderService.findOrderDetail(user.getId(),orderNo);
	}
	//创建订单
	@RequestMapping(value="/createorderChecked.do")
	@ResponseBody
	public SverResponse<ActionOrderVo> createOrderChecked(HttpSession session,Integer addrId){
		//判断用户是否已经登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作");
		}
		return actionOrderService.generateOrderChecked(user.getId(),addrId);
	}
	/*
	 * @author jingfh
	 * @data 2019.07.07
	 */
	//创建立即购买订单
	@RequestMapping(value="/createorderProduct.do")
	@ResponseBody
	public SverResponse<ActionOrderVo> createOrderPro(HttpSession session,Integer addrId,Integer productId,Integer quantity){
		//判断用户是否已经登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作");
		}
		return actionOrderService.generateOrderProduct(user.getId(),addrId,productId,quantity);
	}
	
	
	
	/**
	 * 获取订单Item商品评论
	 * @author jingfh
	 * @data 2019.07.09
	 */
	@RequestMapping(value="/addComment.do")
	@ResponseBody
	public SverResponse<String>  addProductComment(HttpSession session,Integer productId ,Long orderNo,String text){
		//判断用户是否已经登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作");
		}
		return actionProductService.addComment(user.getId(), productId, orderNo, text);
	}
	/**
	 * 添加订单Item商品评论
	 * @author jingfh
	 * @data 2019.07.09
	 */
	@RequestMapping(value="/findComment.do")
	@ResponseBody
	public SverResponse<List<ActionOrderItem>> findProductComment(HttpSession session,Integer productId){
		//判断用户是否已经登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作");
		}
		return actionProductService.findComment(productId);
	}
}
