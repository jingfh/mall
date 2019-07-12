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
			// �жϲ���
				if (addr == null) {
					return SverResponse.createByErrorMessage("��������");
				}
				//�ж����е�ַ���Ƿ���Ĭ�ϵ�ַ�����û��������ַΪĬ�ϵ�ַ
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
					return SverResponse.createRespBySuccessMessage("�����ɹ�");
				}
				return SverResponse.createByErrorMessage("����ʧ��");	
	}

	@Override
	public SverResponse<String> UpdateAddress(Address addr) {
		if (addr == null) {
			return SverResponse.createByErrorMessage("��������");
		}
		addr.setUpdated(new Date());
		int rs = actionAddressDao.UpdateAddress(addr);
		if (rs>0) {
			return SverResponse.createRespBySuccessMessage("���³ɹ�");
		}
		return SverResponse.createByErrorMessage("����ʧ��");
		
	}

	@Override
	public SverResponse<List<Address>> FindAddrsByUserId(Integer uid) {
		if (uid == null) {
			return SverResponse.createByErrorMessage("��������");
		}
		List<Address> list = actionAddressDao.FindAddrsByUserId(uid);
		return SverResponse.createRespBySuccess(list);
	
	}
	@Override
	public SverResponse<ActionAddressVo> FindAddrById(Integer id) {
		if (id == null) {
			return SverResponse.createByErrorMessage("��������");
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
			return SverResponse.createByErrorMessage("��������");
		}
		//ɾ����ַ��del_state�ֶθ���
		Address address = new Address();
		address.setId(id);
		address.setDel_state(1);
		address.setUpdated(new Date());
		int rs = actionAddressDao.UpdateAddress(address);
		if (rs > 0) {
			return SverResponse.createRespBySuccessMessage("ɾ���ɹ�");
		}
		
		return SverResponse.createByErrorMessage("ɾ��ʧ��");
	}

	@Override
	public SverResponse<String> SetDefault(Integer uid, Integer id) {
		if (uid == null || id == null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//��ȡԭ����Ĭ�ϵ�ַ
		Address oldaddrs = actionAddressDao.findDefaultAddress(uid);
		if (oldaddrs != null) {
			//ȡ��Ĭ�ϵ�ַ
			oldaddrs.setDefault_addr(0);
			oldaddrs.setUpdated(new Date());
			if (actionAddressDao.UpdateAddress(oldaddrs) <= 0) {
				return SverResponse.createByErrorMessage("Ĭ�ϵ�ַ����ʧ��");
			}
		}
		Address newaddr = new Address();
		newaddr.setDefault_addr(1);
		newaddr.setId(id);
		newaddr.setUpdated(new Date());
		if (actionAddressDao.UpdateAddress(newaddr)<=0) {
			return SverResponse.createByErrorMessage("Ĭ�ϵ�ַ����ʧ��");
		}
		return SverResponse.createRespBySuccessMessage("Ĭ�ϵ�ַ���óɹ�");
	}
	
	
	
	
	/**
	 * jinfh
	 * 07.09
	 */
	@Override
	public SverResponse<Address> getDefault(Integer uid) {
		if (uid == null ) {
			return SverResponse.createByErrorMessage("��������");
		}
		//��ȡԭ����Ĭ�ϵ�ַ
		Address oldaddrs = actionAddressDao.findDefaultAddress(uid);
		if (oldaddrs == null) {
			return SverResponse.createByErrorMessage("��ȡĬ�ϵ�ַʧ��");
		}
		
		return SverResponse.createRespBySuccess(oldaddrs);
	}

}
