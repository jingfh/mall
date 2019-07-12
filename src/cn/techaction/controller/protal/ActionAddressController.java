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
			return SverResponse.createByErrorMessage("请登录后再进行操作");
		}
		addr.setUser_id(user.getId());
		//判断修改或添加
		SverResponse<String> result = null;
		if (addr.getId()==null) {
			result = actionAddressService.addAddress(addr,user.getId());
		}else {
			result = actionAddressService.UpdateAddress(addr);
		}
		//添加成功后，返回用户所有地址
		if (result.isSuccess()) {
			return actionAddressService.FindAddrsByUserId(user.getId());
		}
		return SverResponse.createByErrorMessage(result.getMsg());
	}
	/*
	 * 删除地址
	 */
	@RequestMapping("/deladdr.do")
	@ResponseBody
	public SverResponse<List<Address>> deleteAddress(HttpSession session,Integer id){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作");
		}
		// 隐性删除地址
		SverResponse<String> result = actionAddressService.DelAddress(user.getId(),id);
		//删除成功后返回所有地址
		if (result.isSuccess()) {
			return actionAddressService.FindAddrsByUserId(user.getId());
		}
		return SverResponse.createByErrorMessage(result.getMsg());
	}
	//设置默认地址
		@RequestMapping("/setdefault.do")
		@ResponseBody
		public SverResponse<List<Address>> setDefault(HttpSession session,Integer id){
			User user = (User) session.getAttribute(ConstUtil.CUR_USER);
			if (user == null) {
				return SverResponse.createByErrorMessage("请登录后再进行操作");
			}
			SverResponse<String> result = actionAddressService.SetDefault(user.getId(),id);
			if (result.isSuccess()) {
				return actionAddressService.FindAddrsByUserId(user.getId());
			}
			return SverResponse.createByErrorMessage(result.getMsg());
		}
		//查找登陆用户的所有地址
		@RequestMapping("/findaddrs.do")
		@ResponseBody
		public SverResponse<List<Address>> findAddrs(HttpSession session){
			User user = (User) session.getAttribute(ConstUtil.CUR_USER);
			if (user == null) {
				return SverResponse.createByErrorMessage("请登录后再进行操作");
			}
			return actionAddressService.FindAddrsByUserId(user.getId());
		}
		
		/*
		 * 添加：依据id查找地址
		 * jingfh-0701PM
		 */
		@RequestMapping("/findAddressById.do")
		@ResponseBody
		public SverResponse<ActionAddressVo> findAddrById(HttpSession session,Integer id){
			User user = (User) session.getAttribute(ConstUtil.CUR_USER);
			if (user == null) {
				return SverResponse.createByErrorMessage("请登录后再进行操作");
			}
			return actionAddressService.FindAddrById(id);
		}
		
		//获取默认地址
		@RequestMapping("/getdefault.do")
		@ResponseBody
		public SverResponse<Address> getDefault(HttpSession session){
			User user = (User) session.getAttribute(ConstUtil.CUR_USER);
			if (user == null) {
				return SverResponse.createByErrorMessage("请登录后再进行操作");
			}
			//Address defaultAddr = actionAddressService.getDefault(user.getId());
			return actionAddressService.getDefault(user.getId());
		}
}
