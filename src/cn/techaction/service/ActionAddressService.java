package cn.techaction.service;

import java.util.List;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.Address;
import cn.techaction.vo.ActionAddressVo;

public interface ActionAddressService {
	//增加地址
	public SverResponse<String> addAddress(Address addr,Integer uid);
	//更新地址
	public SverResponse<String> UpdateAddress(Address addr);
	//查找用户所有地址
	public SverResponse<List<Address>> FindAddrsByUserId(Integer uid);
	//查找id对应地址
	public SverResponse<ActionAddressVo> FindAddrById(Integer id);
	//删除地址
	public SverResponse<String> DelAddress(Integer uid, Integer id);
	//设置默认地址
	public SverResponse<String> SetDefault(Integer uid, Integer id);
	
	
	
	//获取默认地址
	public SverResponse<Address> getDefault(Integer uid);
}
