package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.Address;

public interface ActionAddressDao {
	//查询是否有默认地址
	public int findDefaultAddressByUserId(Integer uid);
	//新增地址
	public int InsertAddress(Address addr);
	//更新地址
	public int UpdateAddress(Address addr);
	 //查询地址
	public List<Address> FindAddrsByUserId(Integer uid);
	//设置默认地址
	public Address findDefaultAddress(Integer uid);
	//根据id查询收货人地址信息
	public Address findAddrsById(Integer addr);
	public Address findOrderAddrsById(Integer addr);
	
	/**
	 * jingfh
	 * 07.09
	 * @param uid
	 * @return
	 */
	 

}
