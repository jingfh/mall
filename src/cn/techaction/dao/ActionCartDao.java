package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.ActionCart;

public interface ActionCartDao {
	//�鿴�û����ﳵ�е���Ʒ��Ϣ
	public List<ActionCart> findCartByUser(Integer uid);
	//�鿴�û����ﳵ�е���Ʒ��Ϣ
	public List<ActionCart> findCartByUserChecked(Integer uid);
	//�����û�id�Ͳ�Ʒid��ѯ���ﳵ
	public ActionCart findCartByUserAndProduct(Integer uid, Integer productId);
	//���湺�ﳵ
	public int insertCart(ActionCart cart);
	//������Ʒ���ﳵ������
	public int updateCartById(ActionCart actionCart);
	//ɾ��ĳ���û����ﳵ�е�������Ʒ
	public int deleteCartByUserId(Integer userid);
	//���¹��ﳵ�е���Ʒ����
	public int updateCartByUserIdAndProductId(ActionCart actionCart);
	//ɾ�����ﳵ�е���Ʒ��Ϣ
	public int deleteCarts(Integer userid, Integer productId);
	//��ȡ��ǰ�û����ﳵ�е���Ʒ����
	public int getCartCountByUserId(Integer userid);
}
