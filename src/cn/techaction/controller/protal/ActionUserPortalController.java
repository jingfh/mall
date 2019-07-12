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
	 * �û���¼
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
			//��¼�ɹ������û���Ϣ����session
			session.setAttribute(ConstUtil.CUR_USER, response.getData());
//System.out.println(response.getData()+"user/do_login.do");
		}
		return response;
	}
	
	/**
	 * �û��ǳ�
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/do_logout.do")
	@ResponseBody
	public int doLogout(HttpSession session){
		session.removeAttribute(ConstUtil.CUR_USER);//���session
//		System.out.println("�ǳ�����******************8");
//		System.out.println("userService.doLogout()");
		return userService.doLogout();
	}
	/*
	 * �û�session��ȡ
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
	 * �û�ע��
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/do_register.do")
	@ResponseBody
	public SverResponse<String> registerUser(User user){
		return userService.doRegister(user);
	}
	
	
	/**
	 * ��֤�û�������û�����
	 * @param account
	 * @return
	 */
//!!!����Ϊ��ʹͨ�ýṹ���������ʾ��Ҫ����Ϊpost+get��default��ʽ
	@RequestMapping(value="/getuserbyaccount.do")
	@ResponseBody
	public SverResponse<User> getUserByAccount(String account){
		return userService.findUserByAccount(account);
	}
	
	/**
	 * ��֤�û�������ʾ�����
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
	 * ��������
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
	 * �޸��û���������
	 * @param session
	 * @param userVo
	 * @return
	 */
	@RequestMapping(value="/updateuserinfo.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<User> updateUserInfo(HttpSession session,ActionUserVo userVo){
		User curUser = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(curUser == null){
			return SverResponse.createRespBySuccessMessage("�û���δ��¼");
		}
		userVo.setId(curUser.getId());
		userVo.setAccount(curUser.getAccount());
		SverResponse<User> resp = userService.updateUserInfo(userVo);
		if(resp.isSuccess()){
			//��дsession
			session.setAttribute(ConstUtil.CUR_USER, resp.getData());
		}
		return resp;
	}
	/**
	 * ��¼�û��޸�����
	 * @param session
	 * @param newpwd
	 * @param oldpwd
	 * @return
	 */
	@RequestMapping(value = "/updatepassword.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> updatePassword(HttpSession session,String newpwd,String oldpwd){
		//��sessionȡ��
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user==null){
			return SverResponse.createByErrorMessage("���¼�����޸�����");
		}
		SverResponse<String> result = userService.updatePassword(user,newpwd,oldpwd);
		
		//���session��׼�����µ�¼
		if(result.isSuccess()){
			session.removeAttribute(ConstUtil.CUR_USER);
		}
		return result;
	}
	
	
	
	
	
	/**
	 * �û�point��ȡ
	 * @author jingfh
	 * @date 2019.07.07
	 */
	@RequestMapping(value="/getpoint.do")
	@ResponseBody
	public SverResponse<Integer> doGetPoint(HttpSession session){
		
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user==null)
			return SverResponse.createByErrorMessage("��Ŀǰ��δ��¼�����½���ٽ��л��ֲ�ѯ��");
		return userService.findPointById(user.getId());
	}
	/**
	 * �û�point����
	 * @author jingfh
	 * @date 2019.07.07
	 */
	@RequestMapping(value="/addpoint.do")
	@ResponseBody
	public SverResponse<String> doAddPoint(HttpSession session,Integer points){
		
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user==null)
			return SverResponse.createByErrorMessage("��Ŀǰ��δ��¼�����½���ٽ��л��ָ��£�");
		return userService.addPointById(user.getId(),points);
	}
	
	/**
	 * У���û�����
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/checkpassword.do")
	@ResponseBody
	public SverResponse<String> checkPassword(HttpSession session,String pwd){
		//��sessionȡ��
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user==null){
			return SverResponse.createByErrorMessage("���¼�����޸�����");
		}
		SverResponse<String> result = userService.checkPwd(user.getAccount(),pwd);
		
		//���session��׼�����µ�¼
		if(result.isSuccess()){
			session.removeAttribute(ConstUtil.CUR_USER);
		}
		return result;
	}
	
	
	@Autowired
	private HttpServletRequest req;
	/**
	 * ��ȡ·��
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
		//String path = session.getServletContext().getRealPath("/");//��ȡ��Ŀ·��
		String path =req.getRequestURL().toString();
  System.out.println("path :   "+path);
		return SverResponse.createRespBySuccessMessage(path);
	}
}
