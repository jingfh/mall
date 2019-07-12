package cn.techaction.service;

import cn.techaction.common.SverResponse;
import cn.techaction.vo.ActionCartVo;

public interface ActionCartService {
	//������Ʒ��Ϣ�����ﳵ
	public SverResponse<String> saveOrUpdate(Integer uid, Integer productId, Integer count);
	//��ѯ�û����ﳵ����Ʒ��Ϣ-��ѡ�е�
	public SverResponse<ActionCartVo> findAllCartsChecked(Integer userid);
	//��ѯ�û����ﳵ����Ʒ��Ϣ
	public SverResponse<ActionCartVo> findAllCarts(Integer userid);
	//��չ��ﳵ
	public SverResponse<String> clearCart(Integer userid);
	//���¹��ﳵ����Ʒ������
	public SverResponse<ActionCartVo> updateCart(Integer userid, Integer productId, Integer count, Integer checked);
	//ɾ�����ﳵ�е���Ʒ��Ϣ
	public SverResponse<ActionCartVo> deleteCart(Integer userid, Integer productId);
	//��ȡ�û����ﳵ����Ʒ����
	public SverResponse<Integer> getCartCount(Integer userid);
	
}
