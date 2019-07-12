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
	
	//��ȡ�����б�
	@RequestMapping("/getlist.do")
	@ResponseBody
	public SverResponse<PageBean<ActionOrderVo>> list(HttpSession session,Integer status,
			@RequestParam(value="pageNum",defaultValue="1") int pageNum,
			@RequestParam(value="pageSize",defaultValue="10")int pageSize){
		//�ж��û��Ƿ��Ѿ���¼
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("���¼���ٽ��в���");
		}
		return actionOrderService.findOrders(user.getId(),status,pageNum,pageSize);
	}
	//ȡ������
	@RequestMapping("/cancelorder.do")
	@ResponseBody
	public SverResponse<String> cancelOrder(HttpSession session,Long orderNo){
		//�ж��û��Ƿ��Ѿ���¼
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("���¼���ٽ��в���");
		}
		return actionOrderService.cancelOrder(user.getId(),orderNo);
	}
	//ȷ���ջ�
	@RequestMapping("/confirmreceipt.do")
	@ResponseBody
	public SverResponse<String> confirmReceipt(HttpSession session,Long orderNo){
		//�ж��û��Ƿ��Ѿ���¼
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("���¼���ٽ��в���");
		}
		return actionOrderService.cancelOrder(user.getId(),orderNo);
	}
	
	/*
	 * �����޸����ݿ�Ϊ�ջ�״̬
	 */
	//ȷ���ջ�
	@RequestMapping("/payedorder.do")
	@ResponseBody
	public SverResponse<String> payedOrder(HttpSession session,Long orderNo){
		//�ж��û��Ƿ��Ѿ���¼
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("���¼���ٽ��в���");
		}
		return actionOrderService.payOrder(user.getId(),orderNo);
	}
	
	
	//��ȡ������ϸ��Ϣ
	@RequestMapping("/getdetail.do")
	@ResponseBody
	public SverResponse<ActionOrderVo> getDetail(HttpSession session,Long orderNo){
		//�ж��û��Ƿ��Ѿ���¼
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("���¼���ٽ��в���");
		}
		return actionOrderService.findOrderDetail(user.getId(),orderNo);
	}
	//��������
	@RequestMapping(value="/createorderChecked.do")
	@ResponseBody
	public SverResponse<ActionOrderVo> createOrderChecked(HttpSession session,Integer addrId){
		//�ж��û��Ƿ��Ѿ���¼
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("���¼���ٽ��в���");
		}
		return actionOrderService.generateOrderChecked(user.getId(),addrId);
	}
	/*
	 * @author jingfh
	 * @data 2019.07.07
	 */
	//�����������򶩵�
	@RequestMapping(value="/createorderProduct.do")
	@ResponseBody
	public SverResponse<ActionOrderVo> createOrderPro(HttpSession session,Integer addrId,Integer productId,Integer quantity){
		//�ж��û��Ƿ��Ѿ���¼
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("���¼���ٽ��в���");
		}
		return actionOrderService.generateOrderProduct(user.getId(),addrId,productId,quantity);
	}
	
	
	
	/**
	 * ��ȡ����Item��Ʒ����
	 * @author jingfh
	 * @data 2019.07.09
	 */
	@RequestMapping(value="/addComment.do")
	@ResponseBody
	public SverResponse<String>  addProductComment(HttpSession session,Integer productId ,Long orderNo,String text){
		//�ж��û��Ƿ��Ѿ���¼
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("���¼���ٽ��в���");
		}
		return actionProductService.addComment(user.getId(), productId, orderNo, text);
	}
	/**
	 * ��Ӷ���Item��Ʒ����
	 * @author jingfh
	 * @data 2019.07.09
	 */
	@RequestMapping(value="/findComment.do")
	@ResponseBody
	public SverResponse<List<ActionOrderItem>> findProductComment(HttpSession session,Integer productId){
		//�ж��û��Ƿ��Ѿ���¼
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("���¼���ٽ��в���");
		}
		return actionProductService.findComment(productId);
	}
}
