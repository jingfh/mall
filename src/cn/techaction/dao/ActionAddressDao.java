package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.Address;

public interface ActionAddressDao {
	//��ѯ�Ƿ���Ĭ�ϵ�ַ
	public int findDefaultAddressByUserId(Integer uid);
	//������ַ
	public int InsertAddress(Address addr);
	//���µ�ַ
	public int UpdateAddress(Address addr);
	 //��ѯ��ַ
	public List<Address> FindAddrsByUserId(Integer uid);
	//����Ĭ�ϵ�ַ
	public Address findDefaultAddress(Integer uid);
	//����id��ѯ�ջ��˵�ַ��Ϣ
	public Address findAddrsById(Integer addr);
	public Address findOrderAddrsById(Integer addr);
	
	/**
	 * jingfh
	 * 07.09
	 * @param uid
	 * @return
	 */
	 

}
