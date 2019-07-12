package cn.techaction.dao;

import cn.techaction.pojo.User;

public interface ActionUserDao {
	/**
	 * 根据账号判断用户是否存在
	 * @param account
	 * @return
	 */
	public int checkUserByAccount(String account);
	/**
	 * 根据用户名和密码查找用户
	 * @param account
	 * @param password
	 * @return
	 */
	public User findUserByAccountAndPwd(String account, String password);
	
	/**
	 * 验证电子邮箱是否被注册
	 * @param email
	 * @return
	 */
	public int checkUserByEmail(String email);
	
	/**
	 * 验证电话号码是否被注册
	 * @param str
	 * @return
	 */
	public int checkUserByPhone(String str);
	
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
	
	
	/**
	 * 根据用户名查找用户
	 * @param account
	 * @return
	 */
	public User findUserByAccount(String account);
	
	/**
	 * 检查用户密码问题的答案
	 * @param account
	 * @param question
	 * @param asw
	 * @return
	 */
	public int checkUserAnswer(String account, String question, String asw);
	
	/**
	 * 通过Id查找用户信息
	 * @param userId
	 * @return
	 */
	public User findUserById(Integer userId);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);
	
	/**
	 * 验证用户密码是否已经存在
	 * @param account
	 * @param oldPassword
	 * @return
	 */
	public int checkPassword(String account, String password);
	
	
	
	/**
	 * 通过Id查找用户积分
	 * @param userId
	 * @return
	 * @author jingfh
	 * @date 2019.07.07
	 */
	public int findPointById(Integer userId);
	/**
	 * 通过Id查找用户积分
	 * @param userId
	 * @return
	 * @author jingfh
	 * @date 2019.07.07
	 */
	public int addPointById(Integer userId,Integer points);

}
