package cn.techaction.service;

import java.util.List;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.Address;
import cn.techaction.vo.ActionAddressVo;

public interface ActionAddressService {
	//���ӵ�ַ
	public SverResponse<String> addAddress(Address addr,Integer uid);
	//���µ�ַ
	public SverResponse<String> UpdateAddress(Address addr);
	//�����û����е�ַ
	public SverResponse<List<Address>> FindAddrsByUserId(Integer uid);
	//����id��Ӧ��ַ
	public SverResponse<ActionAddressVo> FindAddrById(Integer id);
	//ɾ����ַ
	public SverResponse<String> DelAddress(Integer uid, Integer id);
	//����Ĭ�ϵ�ַ
	public SverResponse<String> SetDefault(Integer uid, Integer id);
	
	
	
	//��ȡĬ�ϵ�ַ
	public SverResponse<Address> getDefault(Integer uid);
}
