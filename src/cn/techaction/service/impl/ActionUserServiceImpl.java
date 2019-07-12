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
		//�ж��û��Ƿ����

		int rs = userDao.checkUserByAccount(account);
//System.out.println("rs:"+rs);
		if(rs==0) {
			return SverResponse.createByErrorMessage("�û��������ڣ�");
		}
		//�����û�������������û�
		String md5Pwd = MD5Util.MD5Encode(password, "utf-8", false);
		User user = userDao.findUserByAccountAndPwd(account, md5Pwd);
		if(user==null) {
			return SverResponse.createByErrorMessage("�������");
		}
		//�������ÿգ���ֹй��
		user.setPassword(StringUtils.EMPTY);
		//return SverResponse.createRespBySuccessMessage("��¼�ɹ�");
		return SverResponse.createRespBySuccess("��½�ɹ�",user);
	}
	@Override
	public int doLogout() {
		// TODO Auto-generated method stub
		
		return SverResponse.createByLogout();
	}
	@Override
	public SverResponse<String> doRegister(User user) {
		// ����û����Ƿ����
		SverResponse<String> resp = checkValidation(user.getAccount(),ConstUtil.TYPE_ACCOUNT);
		
		if(!resp.isSuccess()){
			return resp;
		}
		//��������Ƿ�ע��
		resp = checkValidation(user.getAccount(),ConstUtil.TYPE_EMAIL);
		if(!resp.isSuccess()){
			return resp;
		}
		//ָ���û���ɫ��ͨ��ǰ��ע����û���Ϊ�û�
		user.setRole(ConstUtil.Role.ROLE_CUSTOMER);
		//��������м���
		user.setPassword(MD5Util.MD5Encode(user.getPassword(), "utf-8", false));
		//ִ��ע��
		Date curDate = new Date();
		user.setCreate_time(curDate);
		user.setUpdate_time(curDate);
		int rs = userDao.insertUser(user);
		if(rs == 0){
			return SverResponse.createByErrorMessage("ע��ʧ��");
		}
		return SverResponse.createRespBySuccessMessage("ע��ɹ�");
	}

	@Override
	public SverResponse<String> checkValidation(String str, String type) {
		//�ж����ǵ��ַ�����Ϊ��type
		if(StringUtils.isNotBlank(type)){
			if(ConstUtil.TYPE_ACCOUNT.equals(type)){
				int rs = userDao.checkUserByAccount(str);
				if(rs > 0){
					return SverResponse.createByErrorMessage("�û����Ѿ�����");
				}
			}
			if (StringUtils.isNotBlank(type)){
				if(ConstUtil.TYPE_EMAIL.equals(type)){
					int rs = userDao.checkUserByEmail(str);
					if(rs >0){
						return SverResponse.createByErrorMessage("email�Ѿ�����");
					}
				}
			}
			
			if (StringUtils.isNotBlank(type)){
				if(ConstUtil.TYPE_PHONE.equals(type)){
					int rs = userDao.checkUserByPhone(str);
					if(rs >0){
						return SverResponse.createByErrorMessage("phone�Ѿ�����");
					}
				}
			}
			
		}else{
			return SverResponse.createByErrorMessage("��Ϣ��֤������");
			
		}
		return SverResponse.createRespBySuccessMessage("��Ϣ��֤�ɹ�");
	
	}

	@Override
	public SverResponse<User> findUserByAccount(String account) {
		//ͨ���û������ҵ��û�
		User user = userDao.findUserByAccount(account);
		if(user == null){
			return SverResponse.createByErrorMessage("�û�������");
		}
		//�������ÿ�
		user.setPassword(StringUtils.EMPTY);
		//����ȫ������ÿ�
		user.setAsw(StringUtils.EMPTY);
		return SverResponse.createRespBySuccess(user);
		
	}

	@Override
	public SverResponse<String> checkUserAnswer(String account, String question, String asw) {
		int rs = userDao.checkUserAnswer(account,question,asw);
		if(rs > 0){
			//����ȷ������token
			String token = UUID.randomUUID().toString();
			//���뻺��
			TokenCache.setCacheData(TokenCache.PREFIX+account, token);
			return SverResponse.createRespBySuccessMessage(token);
		}
		return SverResponse.createByErrorMessage("����𰸴���");
	}

	@Override
	public SverResponse<String> resetPassword(Integer userId, String password) {
		//�������
		String pwd = MD5Util.MD5Encode(password, "utf-8", false);
		//���user����
		User user = userDao.findUserById(userId);
		
		//��������
		user.setPassword(pwd);
		user.setUpdate_time(new Date());
		int rs = userDao.updateUserInfo(user);
		if(rs > 0){
			return SverResponse.createRespBySuccessMessage("�����޸ĳɹ�");
		}
		return SverResponse.createByErrorMessage("�����޸�ʧ��");
	}

	@Override
	public SverResponse<User> updateUserInfo(ActionUserVo userVo) {
		//����Id���user����
		User updateUser = userDao.findUserById(userVo.getId());
		
		//�����޸��û���Ϣ
		updateUser.setAccount(userVo.getAccount());
		updateUser.setEmail(userVo.getEmail());
		updateUser.setPhone(userVo.getPhone());
		updateUser.setUpdate_time(new Date());
		updateUser.setAge(userVo.getAge());
		//�ж���Ů
		if(userVo.getSex().equals("��")){
			updateUser.setSex(1);
		}else{
			updateUser.setSex(0);
			}
		updateUser.setName(userVo.getName());
		int rs = userDao.updateUserInfo(updateUser);
		if(rs > 0){
			return SverResponse.createRespBySuccess("�û���Ϣ�޸ĳɹ�", updateUser);
		}
		return SverResponse.createByErrorMessage("�û���Ϣ�޸�ʧ��");
	}

	@Override
	public SverResponse<String> updatePassword(User user, String newPassword, String oldPassword) {
		// ��ֹԽȨ������û��������Ƿ���ȷ
		oldPassword = MD5Util.MD5Encode(oldPassword, "utf-8", false);
		int rs =userDao.checkPassword(user.getAccount(),oldPassword);
		if(rs==0){
			return SverResponse.createByErrorMessage("ԭʼ�������");
		}
		//���������޸Ľ����ݿ�
		newPassword = MD5Util.MD5Encode(newPassword, "utf-8", false);
		user.setPassword(newPassword);
		user.setUpdate_time(new Date());
		rs = userDao.updateUserInfo(user);
		if(rs>0){
			return SverResponse.createRespBySuccessMessage("�����޸ĳɹ�");
		}
		
		return SverResponse.createByErrorMessage("�����޸�ʧ��");
	}

//	@Override
	
	
	
	/*
	 * @author jingfh
	 * @date 2019.07.07
	 */
	@Override
	public SverResponse<Integer> findPointById(Integer id) {
		//ͨ���û������ҵ��û�����
		User user = userDao.findUserById(id);
		if(user == null){
			return SverResponse.createByErrorMessage("�û�Id����");
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
			return SverResponse.createRespBySuccessMessage("���ָ��³ɹ�");
		}
		return SverResponse.createByErrorMessage("���ָ���ʧ��");
	}
	/**
	 * У���û���������
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
			
		//�������ÿգ���ֹй��
		//user.setPassword(StringUtils.EMPTY);
		//����ȷ������token
		 String token = UUID.randomUUID().toString();
		//���뻺��
		 TokenCache.setCacheData(TokenCache.PREFIX+account, token);
		 return SverResponse.createRespBySuccessMessage(token);
		}
		return SverResponse.createByErrorMessage("�������");
	}


	

	
}
