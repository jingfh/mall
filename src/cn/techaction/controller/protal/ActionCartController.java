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
	//���빺�ﳵ
	@RequestMapping(value="/savecart.do")
	@ResponseBody
	public SverResponse<String> addProductCart(HttpSession session,Integer productId,Integer count){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null ) {
			return SverResponse.createByErrorMessage("���¼���ٲ鿴���ﳵ");
		}
		return actionCartService.saveOrUpdate(user.getId(),productId,count);
	}
	//�鿴���ﳵ
	@RequestMapping(value="/findallcarts.do")
	@ResponseBody
	public SverResponse<ActionCartVo> findAllCarts(HttpSession session){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null ) {
			return SverResponse.createByErrorMessage("���¼���ٲ鿴���ﳵ");
		}
		return actionCartService.findAllCarts(user.getId());
	}
	//�鿴���ﳵ-ѡ�е�
		@RequestMapping(value="/findallcartsChecked.do")
		@ResponseBody
		public SverResponse<ActionCartVo> findAllCartsChecked(HttpSession session){
			User user = (User) session.getAttribute(ConstUtil.CUR_USER);
			if (user == null ) {
				return SverResponse.createByErrorMessage("���¼���ٲ鿴���ﳵ");
			}
			return actionCartService.findAllCartsChecked(user.getId());
	}
		
	//��չ��ﳵ
	@RequestMapping("/clearcarts.do")
	@ResponseBody
	public SverResponse<String> clearCarts(HttpSession session){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null ) {
			return SverResponse.createByErrorMessage("���¼���ٲ鿴���ﳵ");
		}
		//��չ��ﳵ
		return actionCartService.clearCart(user.getId());
	}
	//���¹��ﳵ
	@RequestMapping("/updatecarts.do")
	@ResponseBody
	public SverResponse<ActionCartVo> updateCarts(HttpSession session,Integer productId,Integer count,Integer checked){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null ) {
			return SverResponse.createByErrorMessage("���¼���ٲ鿴���ﳵ");
		}
		return actionCartService.updateCart(user.getId(),productId,count,checked);
	}
	//ɾ�����ﳵ�е���Ʒ
	@RequestMapping("/delcarts.do")
	@ResponseBody
	public SverResponse<ActionCartVo> deleteCart(HttpSession session,Integer productId){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null ) {
			return SverResponse.createByErrorMessage("���¼���ٲ鿴���ﳵ");
		}
		return actionCartService.deleteCart(user.getId(),productId);
	}
	//���ﳵ����
	@RequestMapping("/getcartcount.do")
	@ResponseBody
	public SverResponse<Integer> getCartsCount(HttpSession session){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null ) {
			return SverResponse.createByErrorMessage("���¼���ٲ鿴���ﳵ");
		}
		return actionCartService.getCartCount(user.getId());
	}
}
