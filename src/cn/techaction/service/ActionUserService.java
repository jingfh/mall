package cn.techaction.service;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.User;
import cn.techaction.vo.ActionUserVo;
import cn.techaction.pojo.User;
public interface ActionUserService {
	/**
	 * 用户登录
	 * @param account
	 * @param password
	 * @return
	 */
	public SverResponse<User> doLogin(String account, String password);
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public SverResponse<String> doRegister(User user);
	/**
	 * 用户登出
	 * @param user
	 * @return
	 */
	public int doLogout();

	/**
	 * 信息校验验证
	 * @param str
	 * @param type
	 * @return
	 */
	public SverResponse<String> checkValidation(String str,String type);
	
	/**
	 * 根据用户名获得用户对象
	 * @param account
	 * @return
	 */
	public SverResponse<User> findUserByAccount(String account);
	
	/**
	 *校验用户问题答案
	 * @param account
	 * @param question
	 * @param asw
	 * @return
	 */
	public SverResponse<String> checkUserAnswer(String account, String question, String asw);
	
	/**
	 * 重置密码
	 * @param userId
	 * @param newpwd
	 * @return
	 */
	public SverResponse<String> resetPassword(Integer userId, String newpwd);
	
	
	/**
	 * 更新用户信息
	 * @param userVo
	 * @return
	 */
	public SverResponse<User> updateUserInfo(ActionUserVo userVo);
	
	
	/**
	 * 重设密码
	 * @param user
	 * @param newpwd
	 * @param oldpwd
	 * @return
	 */
	public SverResponse<String> updatePassword(User user, String newpassword, String oldpassword);
	
	
	
	
	/**
	 * 根据Id获得用户积分
	 * @param userId
	 * @return
	 * @author jingfh
	 * @date 2019.07.07
	 */
	public SverResponse<Integer> findPointById(Integer userId);
	/**
	 * 根据Id更新用户积分
	 * @param userId,points
	 * @return
	 * @author jingfh
	 * @date 2019.07.07
	 */
	public SverResponse<String> addPointById(Integer userId,Integer points);
	/**
	 * 校验用户输入密码
	 * @param userId,pwd
	 * @return
	 * jingfh
	 * 07.10
	 */
	public SverResponse<String> checkPwd(String account,String pwd);
}
