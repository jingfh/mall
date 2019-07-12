package cn.techaction.dao;

import cn.techaction.pojo.User;

public interface ActionUserDao {
	/**
	 * �����˺��ж��û��Ƿ����
	 * @param account
	 * @return
	 */
	public int checkUserByAccount(String account);
	/**
	 * �����û�������������û�
	 * @param account
	 * @param password
	 * @return
	 */
	public User findUserByAccountAndPwd(String account, String password);
	
	/**
	 * ��֤���������Ƿ�ע��
	 * @param email
	 * @return
	 */
	public int checkUserByEmail(String email);
	
	/**
	 * ��֤�绰�����Ƿ�ע��
	 * @param str
	 * @return
	 */
	public int checkUserByPhone(String str);
	
	
	/**
	 * �����û�
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
	
	
	/**
	 * �����û��������û�
	 * @param account
	 * @return
	 */
	public User findUserByAccount(String account);
	
	/**
	 * ����û���������Ĵ�
	 * @param account
	 * @param question
	 * @param asw
	 * @return
	 */
	public int checkUserAnswer(String account, String question, String asw);
	
	/**
	 * ͨ��Id�����û���Ϣ
	 * @param userId
	 * @return
	 */
	public User findUserById(Integer userId);
	
	/**
	 * �����û���Ϣ
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);
	
	/**
	 * ��֤�û������Ƿ��Ѿ�����
	 * @param account
	 * @param oldPassword
	 * @return
	 */
	public int checkPassword(String account, String password);
	
	
	
	/**
	 * ͨ��Id�����û�����
	 * @param userId
	 * @return
	 * @author jingfh
	 * @date 2019.07.07
	 */
	public int findPointById(Integer userId);
	/**
	 * ͨ��Id�����û�����
	 * @param userId
	 * @return
	 * @author jingfh
	 * @date 2019.07.07
	 */
	public int addPointById(Integer userId,Integer points);

}
