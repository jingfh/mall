package cn.techaction.service;

import java.util.List;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.ActionParam;

public interface ActionParamService {
	/**
	 * ��ȡȫ��������Ϣ
	 * @return
	 */
	public SverResponse<List<ActionParam>> findAllParams();
	
}
