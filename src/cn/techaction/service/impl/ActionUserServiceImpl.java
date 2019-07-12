package cn.techaction.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.techaction.common.SverResponse;
import cn.techaction.dao.ActionUserDao;
import cn.techaction.pojo.User;
import cn.techaction.service.ActionUserService;
import cn.techaction.utils.ConstUtil;
import cn.techaction.utils.MD5Util;
import cn.techaction.utils.TokenCache;
import cn.techaction.vo.ActionUserVo;
@Service
public class ActionUserServiceImpl implements ActionUserService {
	
	@Autowired
	private ActionUserDao userDao;

	@Override
	public SverResponse<User> doLogin(String account, String password) {
		//判断用户是否存在

		int rs = userDao.checkUserByAccount(account);
//System.out.println("rs:"+rs);
		if(rs==0) {
			return SverResponse.createByErrorMessage("用户名不存在！");
		}
		//根据用户名和密码查找用户
		String md5Pwd = MD5Util.MD5Encode(password, "utf-8", false);
		User user = userDao.findUserByAccountAndPwd(account, md5Pwd);
		if(user==null) {
			return SverResponse.createByErrorMessage("密码错误！");
		}
		//将密码置空，防止泄密
		user.setPassword(StringUtils.EMPTY);
		//return SverResponse.createRespBySuccessMessage("登录成功");
		return SverResponse.createRespBySuccess("登陆成功",user);
	}
	@Override
	public int doLogout() {
		// TODO Auto-generated method stub
		
		return SverResponse.createByLogout();
	}
	@Override
	public SverResponse<String> doRegister(User user) {
		// 检查用户名是否存在
		SverResponse<String> resp = checkValidation(user.getAccount(),ConstUtil.TYPE_ACCOUNT);
		
		if(!resp.isSuccess()){
			return resp;
		}
		//检查邮箱是否被注册
		resp = checkValidation(user.getAccount(),ConstUtil.TYPE_EMAIL);
		if(!resp.isSuccess()){
			return resp;
		}
		//指定用户角色，通过前端注册的用户都为用户
		user.setRole(ConstUtil.Role.ROLE_CUSTOMER);
		//对密码进行加密
		user.setPassword(MD5Util.MD5Encode(user.getPassword(), "utf-8", false));
		//执行注册
		Date curDate = new Date();
		user.setCreate_time(curDate);
		user.setUpdate_time(curDate);
		int rs = userDao.insertUser(user);
		if(rs == 0){
			return SverResponse.createByErrorMessage("注册失败");
		}
		return SverResponse.createRespBySuccessMessage("注册成功");
	}

	@Override
	public SverResponse<String> checkValidation(String str, String type) {
		//判断我们的字符串不为空type
		if(StringUtils.isNotBlank(type)){
			if(ConstUtil.TYPE_ACCOUNT.equals(type)){
				int rs = userDao.checkUserByAccount(str);
				if(rs > 0){
					return SverResponse.createByErrorMessage("用户名已经存在");
				}
			}
			if (StringUtils.isNotBlank(type)){
				if(ConstUtil.TYPE_EMAIL.equals(type)){
					int rs = userDao.checkUserByEmail(str);
					if(rs >0){
						return SverResponse.createByErrorMessage("email已经存在");
					}
				}
			}
			
			if (StringUtils.isNotBlank(type)){
				if(ConstUtil.TYPE_PHONE.equals(type)){
					int rs = userDao.checkUserByPhone(str);
					if(rs >0){
						return SverResponse.createByErrorMessage("phone已经存在");
					}
				}
			}
			
		}else{
			return SverResponse.createByErrorMessage("信息验证类别错误");
			
		}
		return SverResponse.createRespBySuccessMessage("信息验证成功");
	
	}

	@Override
	public SverResponse<User> findUserByAccount(String account) {
		//通过用户名查找到用户
		User user = userDao.findUserByAccount(account);
		if(user == null){
			return SverResponse.createByErrorMessage("用户名错误");
		}
		//将密码置空
		user.setPassword(StringUtils.EMPTY);
		//将安全问题答案置空
		user.setAsw(StringUtils.EMPTY);
		return SverResponse.createRespBySuccess(user);
		
	}

	@Override
	public SverResponse<String> checkUserAnswer(String account, String question, String asw) {
		int rs = userDao.checkUserAnswer(account,question,asw);
		if(rs > 0){
			//答案正确，生成token
			String token = UUID.randomUUID().toString();
			//放入缓存
			TokenCache.setCacheData(TokenCache.PREFIX+account, token);
			return SverResponse.createRespBySuccessMessage(token);
		}
		return SverResponse.createByErrorMessage("问题答案错误");
	}

	@Override
	public SverResponse<String> resetPassword(Integer userId, String password) {
		//密码加密
		String pwd = MD5Util.MD5Encode(password, "utf-8", false);
		//获得user对象
		User user = userDao.findUserById(userId);
		
		//更新密码
		user.setPassword(pwd);
		user.setUpdate_time(new Date());
		int rs = userDao.updateUserInfo(user);
		if(rs > 0){
			return SverResponse.createRespBySuccessMessage("密码修改成功");
		}
		return SverResponse.createByErrorMessage("密码修改失败");
	}

	@Override
	public SverResponse<User> updateUserInfo(ActionUserVo userVo) {
		//根据Id获得user对象
		User updateUser = userDao.findUserById(userVo.getId());
		
		//更新修改用户信息
		updateUser.setAccount(userVo.getAccount());
		updateUser.setEmail(userVo.getEmail());
		updateUser.setPhone(userVo.getPhone());
		updateUser.setUpdate_time(new Date());
		updateUser.setAge(userVo.getAge());
		//判断男女
		if(userVo.getSex().equals("男")){
			updateUser.setSex(1);
		}else{
			updateUser.setSex(0);
			}
		updateUser.setName(userVo.getName());
		int rs = userDao.updateUserInfo(updateUser);
		if(rs > 0){
			return SverResponse.createRespBySuccess("用户信息修改成功", updateUser);
		}
		return SverResponse.createByErrorMessage("用户信息修改失败");
	}

	@Override
	public SverResponse<String> updatePassword(User user, String newPassword, String oldPassword) {
		// 防止越权，检测用户旧密码是否正确
		oldPassword = MD5Util.MD5Encode(oldPassword, "utf-8", false);
		int rs =userDao.checkPassword(user.getAccount(),oldPassword);
		if(rs==0){
			return SverResponse.createByErrorMessage("原始密码错误");
		}
		//将新密码修改进数据库
		newPassword = MD5Util.MD5Encode(newPassword, "utf-8", false);
		user.setPassword(newPassword);
		user.setUpdate_time(new Date());
		rs = userDao.updateUserInfo(user);
		if(rs>0){
			return SverResponse.createRespBySuccessMessage("密码修改成功");
		}
		
		return SverResponse.createByErrorMessage("密码修改失败");
	}

//	@Override
	
	
	
	/*
	 * @author jingfh
	 * @date 2019.07.07
	 */
	@Override
	public SverResponse<Integer> findPointById(Integer id) {
		//通过用户名查找到用户积分
		User user = userDao.findUserById(id);
		if(user == null){
			return SverResponse.createByErrorMessage("用户Id错误");
		}
		int p = userDao.findPointById(id);
		return SverResponse.createRespBySuccess(p);
		
	}
	/*
	 * @author jingfh
	 * @date 2019.07.07
	 */
	@Override
	public SverResponse<String> addPointById(Integer userId,Integer points) {
		
		int rs = userDao.addPointById(userId,points);
		if(rs > 0){
			return SverResponse.createRespBySuccessMessage("积分更新成功");
		}
		return SverResponse.createByErrorMessage("积分更新失败");
	}
	/**
	 * 校验用户输入密码
	 * @param userId,pwd
	 * @return
	 * jingfh
	 * 07.10
	 */
	public SverResponse<String> checkPwd(String account,String pwd){
		String md5Pwd = MD5Util.MD5Encode(pwd, "utf-8", false);
		int rs = userDao.checkPassword(account, md5Pwd);
//System.out.println("rs:"+rs);
		if(rs>0) {
			
		//将密码置空，防止泄密
		//user.setPassword(StringUtils.EMPTY);
		//答案正确，生成token
		 String token = UUID.randomUUID().toString();
		//放入缓存
		 TokenCache.setCacheData(TokenCache.PREFIX+account, token);
		 return SverResponse.createRespBySuccessMessage(token);
		}
		return SverResponse.createByErrorMessage("密码错误");
	}


	

	
}
