package cn.techaction.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.techaction.common.SverResponse;
import cn.techaction.dao.ActionParamsDao;
import cn.techaction.pojo.ActionParam;

import cn.techaction.service.ActionParamService;
@Service
public class ActionParamServiceImpl implements ActionParamService {
	@Autowired
	private ActionParamsDao aParamDao;

	@Override
	public SverResponse<List<ActionParam>> findAllParams() {
		//����һ���ӽڵ�
		List<ActionParam> paramList = aParamDao.findParamsByParentId(0);
		//�ݹ��ѯ�����ӽڵ�
		for(ActionParam param:paramList){
			findDirectChildren(param);
		}
		return SverResponse.createRespBySuccess(paramList);
	}
	//�ݹ����
	private void findDirectChildren(ActionParam parentParam) {
		// TODO �Զ����ɵķ������
		//�����ӽڵ�
		List<ActionParam> paramList = aParamDao.findParamsByParentId(parentParam.getId());
		parentParam.setChildren(paramList);
		for(ActionParam p:paramList){
			findDirectChildren(p);
		}
		
	}
	
	
	
}
