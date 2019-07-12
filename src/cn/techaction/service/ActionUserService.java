package cn.techaction.service;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.User;
import cn.techaction.vo.ActionUserVo;
import cn.techaction.pojo.User;
public interface ActionUserService {
	/**
	 * �û���¼
	 * @param account
	 * @param password
	 * @return
	 */
	public SverResponse<User> doLogin(String account, String password);
	/**
	 * �û�ע��
	 * @param user
	 * @return
	 */
	public SverResponse<String> doRegister(User user);
	/**
	 * �û��ǳ�
	 * @param user
	 * @return
	 */
	public int doLogout();

	/**
	 * ��ϢУ����֤
	 * @param str
	 * @param type
	 * @return
	 */
	public SverResponse<String> checkValidation(String str,String type);
	
	/**
	 * �����û�������û�����
	 * @param account
	 * @return
	 */
	public SverResponse<User> findUserByAccount(String account);
	
	/**
	 *У���û������
	 * @param account
	 * @param question
	 * @param asw
	 * @return
	 */
	public SverResponse<String> checkUserAnswer(String account, String question, String asw);
	
	/**
	 * ��������
	 * @param userId
	 * @param newpwd
	 * @return
	 */
	public SverResponse<String> resetPassword(Integer userId, String newpwd);
	
	
	/**
	 * �����û���Ϣ
	 * @param userVo
	 * @return
	 */
	public SverResponse<User> updateUserInfo(ActionUserVo userVo);
	
	
	/**
	 * ��������
	 * @param user
	 * @param newpwd
	 * @param oldpwd
	 * @return
	 */
	public SverResponse<String> updatePassword(User user, String newpassword, String oldpassword);
	
	
	
	
	/**
	 * ����Id����û�����
	 * @param userId
	 * @return
	 * @author jingfh
	 * @date 2019.07.07
	 */
	public SverResponse<Integer> findPointById(Integer userId);
	/**
	 * ����Id�����û�����
	 * @param userId,points
	 * @return
	 * @author jingfh
	 * @date 2019.07.07
	 */
	public SverResponse<String> addPointById(Integer userId,Integer points);
	/**
	 * У���û���������
	 * @param userId,pwd
	 * @return
	 * jingfh
	 * 07.10
	 */
	public SverResponse<String> checkPwd(String account,String pwd);
}
