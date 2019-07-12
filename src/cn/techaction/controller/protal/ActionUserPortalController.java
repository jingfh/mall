package cn.techaction.controller.protal;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.User;
import cn.techaction.service.ActionUserService;
import cn.techaction.utils.ConstUtil;
import cn.techaction.vo.ActionUserVo;

@Controller
@RequestMapping("/user")
public class ActionUserPortalController {
	
	@Autowired
	private ActionUserService userService;
	
	/**
	 * 用户登录
	 * @param account
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/do_login.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<User> doLogin(String account,String password,HttpSession session){
		SverResponse<User> response = userService.doLogin(account,password);
		if(response.isSuccess()){
			//登录成功，将用户信息存入session
			session.setAttribute(ConstUtil.CUR_USER, response.getData());
//System.out.println(response.getData()+"user/do_login.do");
		}
		return response;
	}
	
	/**
	 * 用户登出
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/do_logout.do")
	@ResponseBody
	public int doLogout(HttpSession session){
		session.removeAttribute(ConstUtil.CUR_USER);//清空session
//		System.out.println("登出测试******************8");
//		System.out.println("userService.doLogout()");
		return userService.doLogout();
	}
	/*
	 * 用户session获取
	 * 
	 */
	@RequestMapping(value="/do_get_info.do")
	@ResponseBody
	public SverResponse<User> doGetInfo(HttpSession session){
		
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user==null)
			return SverResponse.createRespByError(user);
		return SverResponse.createRespBySuccess(user);
	}
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/do_register.do")
	@ResponseBody
	public SverResponse<String> registerUser(User user){
		return userService.doRegister(user);
	}
	
	
	/**
	 * 验证用户，获得用户对象
	 * @param account
	 * @return
	 */
//!!!这里为了使通用结构里的名字显示，要设置为post+get的default形式
	@RequestMapping(value="/getuserbyaccount.do")
	@ResponseBody
	public SverResponse<User> getUserByAccount(String account){
		return userService.findUserByAccount(account);
	}
	
	/**
	 * 验证用户密码提示问题答案
	 * @param account
	 * @param question
	 * @param asw
	 * @return
	 */
	@RequestMapping(value="/checkuserasw.do",method = RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> checkUserAnswer(String account,String question,String asw){
		return userService.checkUserAnswer(account,question,asw);
	}
	
	/**
	 * 重置密码
	 * @param userId
	 * @param newpwd
	 * @return
	 */
	@RequestMapping(value="/resetpassword.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> resetPassword(Integer userId,String password){
		return userService.resetPassword(userId,password);
	}
	
	/**
	 * 修改用户个人资料
	 * @param session
	 * @param userVo
	 * @return
	 */
	@RequestMapping(value="/updateuserinfo.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<User> updateUserInfo(HttpSession session,ActionUserVo userVo){
		User curUser = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(curUser == null){
			return SverResponse.createRespBySuccessMessage("用户尚未登录");
		}
		userVo.setId(curUser.getId());
		userVo.setAccount(curUser.getAccount());
		SverResponse<User> resp = userService.updateUserInfo(userVo);
		if(resp.isSuccess()){
			//重写session
			session.setAttribute(ConstUtil.CUR_USER, resp.getData());
		}
		return resp;
	}
	/**
	 * 登录用户修改密码
	 * @param session
	 * @param newpwd
	 * @param oldpwd
	 * @return
	 */
	@RequestMapping(value = "/updatepassword.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> updatePassword(HttpSession session,String newpwd,String oldpwd){
		//将session取出
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user==null){
			return SverResponse.createByErrorMessage("请登录后再修改密码");
		}
		SverResponse<String> result = userService.updatePassword(user,newpwd,oldpwd);
		
		//清空session，准备重新登录
		if(result.isSuccess()){
			session.removeAttribute(ConstUtil.CUR_USER);
		}
		return result;
	}
	
	
	
	
	
	/**
	 * 用户point获取
	 * @author jingfh
	 * @date 2019.07.07
	 */
	@RequestMapping(value="/getpoint.do")
	@ResponseBody
	public SverResponse<Integer> doGetPoint(HttpSession session){
		
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user==null)
			return SverResponse.createByErrorMessage("您目前还未登录，请登陆后再进行积分查询！");
		return userService.findPointById(user.getId());
	}
	/**
	 * 用户point更新
	 * @author jingfh
	 * @date 2019.07.07
	 */
	@RequestMapping(value="/addpoint.do")
	@ResponseBody
	public SverResponse<String> doAddPoint(HttpSession session,Integer points){
		
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user==null)
			return SverResponse.createByErrorMessage("您目前还未登录，请登陆后再进行积分更新！");
		return userService.addPointById(user.getId(),points);
	}
	
	/**
	 * 校验用户密码
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/checkpassword.do")
	@ResponseBody
	public SverResponse<String> checkPassword(HttpSession session,String pwd){
		//将session取出
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user==null){
			return SverResponse.createByErrorMessage("请登录后再修改密码");
		}
		SverResponse<String> result = userService.checkPwd(user.getAccount(),pwd);
		
		//清空session，准备重新登录
		if(result.isSuccess()){
			session.removeAttribute(ConstUtil.CUR_USER);
		}
		return result;
	}
	
	
	@Autowired
	private HttpServletRequest req;
	/**
	 * 获取路径
	 * @param account
	 * @param password
	 * @param session
	 * @return
	 * @author jingfh
	 * @date 2019.07.09
	 */
	@RequestMapping(value="/getRootPath.do")
	@ResponseBody
	public SverResponse<String> doGetRootPath(HttpSession session){
		//String path = session.getServletContext().getRealPath("/");//获取项目路径
		String path =req.getRequestURL().toString();
  System.out.println("path :   "+path);
		return SverResponse.createRespBySuccessMessage(path);
	}
}
