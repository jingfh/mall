package cn.techaction.controller.protal;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.Address;
import cn.techaction.pojo.User;
import cn.techaction.service.ActionAddressService;
import cn.techaction.utils.ConstUtil;
import cn.techaction.vo.ActionAddressVo;
@Controller
@RequestMapping("/addr")
public class ActionAddressController {
	@Autowired
	private ActionAddressService actionAddressService;
	@RequestMapping(value="/saveaddr.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<List<Address>> saveAddress(HttpSession session,Address addr){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("���¼���ٽ��в���");
		}
		addr.setUser_id(user.getId());
		//�ж��޸Ļ����
		SverResponse<String> result = null;
		if (addr.getId()==null) {
			result = actionAddressService.addAddress(addr,user.getId());
		}else {
			result = actionAddressService.UpdateAddress(addr);
		}
		//��ӳɹ��󣬷����û����е�ַ
		if (result.isSuccess()) {
			return actionAddressService.FindAddrsByUserId(user.getId());
		}
		return SverResponse.createByErrorMessage(result.getMsg());
	}
	/*
	 * ɾ����ַ
	 */
	@RequestMapping("/deladdr.do")
	@ResponseBody
	public SverResponse<List<Address>> deleteAddress(HttpSession session,Integer id){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("���¼���ٽ��в���");
		}
		// ����ɾ����ַ
		SverResponse<String> result = actionAddressService.DelAddress(user.getId(),id);
		//ɾ���ɹ��󷵻����е�ַ
		if (result.isSuccess()) {
			return actionAddressService.FindAddrsByUserId(user.getId());
		}
		return SverResponse.createByErrorMessage(result.getMsg());
	}
	//����Ĭ�ϵ�ַ
		@RequestMapping("/setdefault.do")
		@ResponseBody
		public SverResponse<List<Address>> setDefault(HttpSession session,Integer id){
			User user = (User) session.getAttribute(ConstUtil.CUR_USER);
			if (user == null) {
				return SverResponse.createByErrorMessage("���¼���ٽ��в���");
			}
			SverResponse<String> result = actionAddressService.SetDefault(user.getId(),id);
			if (result.isSuccess()) {
				return actionAddressService.FindAddrsByUserId(user.getId());
			}
			return SverResponse.createByErrorMessage(result.getMsg());
		}
		//���ҵ�½�û������е�ַ
		@RequestMapping("/findaddrs.do")
		@ResponseBody
		public SverResponse<List<Address>> findAddrs(HttpSession session){
			User user = (User) session.getAttribute(ConstUtil.CUR_USER);
			if (user == null) {
				return SverResponse.createByErrorMessage("���¼���ٽ��в���");
			}
			return actionAddressService.FindAddrsByUserId(user.getId());
		}
		
		/*
		 * ��ӣ�����id���ҵ�ַ
		 * jingfh-0701PM
		 */
		@RequestMapping("/findAddressById.do")
		@ResponseBody
		public SverResponse<ActionAddressVo> findAddrById(HttpSession session,Integer id){
			User user = (User) session.getAttribute(ConstUtil.CUR_USER);
			if (user == null) {
				return SverResponse.createByErrorMessage("���¼���ٽ��в���");
			}
			return actionAddressService.FindAddrById(id);
		}
		
		//��ȡĬ�ϵ�ַ
		@RequestMapping("/getdefault.do")
		@ResponseBody
		public SverResponse<Address> getDefault(HttpSession session){
			User user = (User) session.getAttribute(ConstUtil.CUR_USER);
			if (user == null) {
				return SverResponse.createByErrorMessage("���¼���ٽ��в���");
			}
			//Address defaultAddr = actionAddressService.getDefault(user.getId());
			return actionAddressService.getDefault(user.getId());
		}
}
