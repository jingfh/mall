package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.ActionParam;

public interface ActionParamsDao {
	/**
	 * ���ݽ��id���Ҳο�����
	 * @param id
	 * @return
	 */
	
	public ActionParam findParamById(Integer id);
	
	
	/**
	 * ���ݸ��ڵ�Id���ҽ�����
	 * @param i
	 * @return
	 */
	public List<ActionParam> findParamsByParentId(Integer parentId);


	
}
