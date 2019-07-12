package cn.techaction.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.techaction.common.SverResponse;
import cn.techaction.dao.ActionAddressDao;
import cn.techaction.pojo.Address;
import cn.techaction.service.ActionAddressService;
import cn.techaction.vo.ActionAddressVo;
@Service
public class ActionAddressServiceImpl implements ActionAddressService {
	@Autowired
	private ActionAddressDao actionAddressDao;
	@Override
	public SverResponse<String> addAddress(Address addr,Integer uid) {
			// 判断参数
				if (addr == null) {
					return SverResponse.createByErrorMessage("参数错误");
				}
				//判断已有地址中是否还有默认地址，如果没有则本条地址为默认地址
				addr.getUser_id();
				int count = actionAddressDao.findDefaultAddressByUserId(uid);
				if (count == 0) {
					addr.setDefault_addr(1);
				}else {
					addr.setDefault_addr(0);
				}
				addr.setCreated(new Date());
				addr.setUpdated(new Date());
				int rs = actionAddressDao.InsertAddress(addr);
				System.out.println(rs);
				if (rs>0) {
					return SverResponse.createRespBySuccessMessage("新增成功");
				}
				return SverResponse.createByErrorMessage("新增失败");	
	}

	@Override
	public SverResponse<String> UpdateAddress(Address addr) {
		if (addr == null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		addr.setUpdated(new Date());
		int rs = actionAddressDao.UpdateAddress(addr);
		if (rs>0) {
			return SverResponse.createRespBySuccessMessage("更新成功");
		}
		return SverResponse.createByErrorMessage("更新失败");
		
	}

	@Override
	public SverResponse<List<Address>> FindAddrsByUserId(Integer uid) {
		if (uid == null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		List<Address> list = actionAddressDao.FindAddrsByUserId(uid);
		return SverResponse.createRespBySuccess(list);
	
	}
	@Override
	public SverResponse<ActionAddressVo> FindAddrById(Integer id) {
		if (id == null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		Address addr = actionAddressDao.findAddrsById(id);
		ActionAddressVo addrVo=new ActionAddressVo();
		addrVo.setName(addr.getName());
		addrVo.setPhone(addr.getPhone());
		addrVo.setMobile(addr.getMobile());
		addrVo.setZip(addr.getZip());
		addrVo.setAddr(addr.getAddr());
		addrVo.setProvince(addr.getProvince());
		addrVo.setCity(addr.getCity());
		addrVo.setDistrict(addr.getDistrict());
		return SverResponse.createRespBySuccess(addrVo);
	
	}

	@Override
	public SverResponse<String> DelAddress(Integer uid, Integer id) {
		if (uid == null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		//删除地址并del_state字段更新
		Address address = new Address();
		address.setId(id);
		address.setDel_state(1);
		address.setUpdated(new Date());
		int rs = actionAddressDao.UpdateAddress(address);
		if (rs > 0) {
			return SverResponse.createRespBySuccessMessage("删除成功");
		}
		
		return SverResponse.createByErrorMessage("删除失败");
	}

	@Override
	public SverResponse<String> SetDefault(Integer uid, Integer id) {
		if (uid == null || id == null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		//读取原来的默认地址
		Address oldaddrs = actionAddressDao.findDefaultAddress(uid);
		if (oldaddrs != null) {
			//取消默认地址
			oldaddrs.setDefault_addr(0);
			oldaddrs.setUpdated(new Date());
			if (actionAddressDao.UpdateAddress(oldaddrs) <= 0) {
				return SverResponse.createByErrorMessage("默认地址设置失败");
			}
		}
		Address newaddr = new Address();
		newaddr.setDefault_addr(1);
		newaddr.setId(id);
		newaddr.setUpdated(new Date());
		if (actionAddressDao.UpdateAddress(newaddr)<=0) {
			return SverResponse.createByErrorMessage("默认地址设置失败");
		}
		return SverResponse.createRespBySuccessMessage("默认地址设置成功");
	}
	
	
	
	
	/**
	 * jinfh
	 * 07.09
	 */
	@Override
	public SverResponse<Address> getDefault(Integer uid) {
		if (uid == null ) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		//读取原来的默认地址
		Address oldaddrs = actionAddressDao.findDefaultAddress(uid);
		if (oldaddrs == null) {
			return SverResponse.createByErrorMessage("获取默认地址失败");
		}
		
		return SverResponse.createRespBySuccess(oldaddrs);
	}

}
