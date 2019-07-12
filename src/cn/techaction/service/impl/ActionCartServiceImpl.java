package cn.techaction.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.techaction.common.SverResponse;
import cn.techaction.dao.ActionCartDao;
import cn.techaction.dao.ActionProductDao;
import cn.techaction.pojo.ActionCart;
import cn.techaction.pojo.ActionProduct;
import cn.techaction.service.ActionCartService;
import cn.techaction.utils.CalcUtil;
import cn.techaction.vo.ActionCartVo;
import cn.techaction.vo.ActionCartsListVo;
@Service
public class ActionCartServiceImpl implements ActionCartService {
	@Autowired
	private ActionCartDao actionCartDao;
	@Autowired
	private ActionProductDao actionProductDao;
	@Override
	public SverResponse<String> saveOrUpdate(Integer uid, Integer productId, Integer count) {
		// TODO Auto-generated method stub
		//��֤�����Ƿ���ȷ
		if (uid == null || productId == null || count == null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//�鿴�û��Ĺ��ﳵ�Ƿ������Ʒ
		ActionCart actionCart = actionCartDao.findCartByUserAndProduct(uid,productId);
		if (actionCart==null) {
			//������������
			ActionCart cart = new ActionCart();
			cart.setUserId(uid);
			cart.setProductId(productId);
			cart.setQuantity(count);
			cart.setCreated(new Date());
			cart.setUpdated(new Date());
			actionCartDao.insertCart(cart);
		}else {
			//��������������
			int cartCount = actionCart.getQuantity()+count;
			actionCart.setQuantity(cartCount);
			actionCartDao.updateCartById(actionCart);
		}
		return SverResponse.createRespBySuccessMessage("��Ʒ�ѳɹ����빺�ﳵ");
	}
	@Override
	public SverResponse<ActionCartVo> findAllCarts(Integer userid) {
		// TODO Auto-generated method stub
		if (userid == null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//�����û����ﳵ�е���Ʒ
		List<ActionCart> list = actionCartDao.findCartByUser(userid);
		//��װactioncartvo����
		ActionCartVo cartVo = creatCartVo(list);
		return SverResponse.createRespBySuccess(cartVo);
	}
	public SverResponse<ActionCartVo> findAllCartsChecked(Integer userid) {
		// TODO Auto-generated method stub
		if (userid == null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//�����û����ﳵ�е���Ʒ
		List<ActionCart> list = actionCartDao.findCartByUserChecked(userid);
		//��װactioncartvo����
		ActionCartVo cartVo = creatCartVo(list);
		return SverResponse.createRespBySuccess(cartVo);
	}
	//��װ���ﳵVo����
	private ActionCartVo creatCartVo(List<ActionCart> carts) {
		// TODO Auto-generated method stub
		ActionCartVo cartVo = new ActionCartVo();
		List<ActionCartsListVo> list = Lists.newArrayList();
		//���ﳵ��Ʒ�ܼ۸�
		BigDecimal cartTotalPrice = new BigDecimal("0");
		if (CollectionUtils.isNotEmpty(carts)) {
			for (ActionCart cart : carts) {
				//ת������
				ActionCartsListVo listVo = new ActionCartsListVo();
				listVo.setId(cart.getId());
				listVo.setUserId(cart.getUserId());
				listVo.setProductId(cart.getProductId());
				listVo.setChecked(cart.getChecked());
				//��װ��Ʒ��Ϣ
				ActionProduct product = actionProductDao.findProductsById(listVo.getProductId());
				if (product != null) {
					listVo.setName(product.getName());
					listVo.setStatus(product.getStatus());
					listVo.setPrice(product.getPrice());
					listVo.setStock(product.getStock());
					listVo.setIconUrl(product.getIcon_url());
					//�жϿ��
					int buyCount = 0;
					if (product.getStock() >= cart.getQuantity()) {
						buyCount = cart.getQuantity();
					}else {
						buyCount = product.getStock();
						//���¹��ﳵ��Ʒ����
						ActionCart updateCart = new ActionCart();
						updateCart.setId(cart.getId());
						updateCart.setQuantity(buyCount);
						//����״̬
						updateCart.setChecked(cart.getChecked());
						actionCartDao.updateCartById(updateCart);
					}
					listVo.setQuantity(buyCount);
					//���㹺�ﳵ��ĳ��Ʒ�ܼ۸�
					BigDecimal totalPrice = CalcUtil.mul(listVo.getPrice().doubleValue(), listVo.getQuantity().doubleValue());
					listVo.setTotalPrice(totalPrice);
					if (cart.getChecked() == 1) {
						//���㹺�ﳵ��ѡ��״̬��Ʒ���ܼ۸�

//System.out.println("��ǰ�ܼ۸�="+cartTotalPrice.doubleValue()+"��\n ����Ʒ�۸�="+listVo.getTotalPrice().doubleValue());
						cartTotalPrice = CalcUtil.add(cartTotalPrice.doubleValue(), listVo.getTotalPrice().doubleValue());
//System.out.println("��\n �ܼ۸�="+cartTotalPrice);
					}
				}
				list.add(listVo);
			}
		}
		cartVo.setLists(list);
		cartVo.setTotalPrice(cartTotalPrice);
		return cartVo;
	}
	@Override
	public SverResponse<String> clearCart(Integer userid) {
		// TODO Auto-generated method stub
		if (userid == null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//��չ��ﳵ���ж���ȷ
		int rs = actionCartDao.deleteCartByUserId(userid);
		if (rs > 0) {
			return SverResponse.createRespBySuccessMessage("�ɹ���չ��ﳵ");
			
		}
		return SverResponse.createByErrorMessage("��չ��ﳵʧ��");
	}
	@Override
	public SverResponse<ActionCartVo> updateCart(Integer userid, Integer productId, Integer count, Integer checked) {
		// TODO Auto-generated method stub
		if (userid == null || productId == null || count == null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//���¹��ﳵ��Ϣ
		ActionCart actionCart = new ActionCart();
		actionCart.setUserId(userid);
		actionCart.setProductId(productId);
		actionCart.setQuantity(count);
		actionCart.setChecked(checked);
		actionCartDao.updateCartByUserIdAndProductId(actionCart);
		//�������й��ﳵ��Ϣ
		return findAllCarts(userid);
	}
	@Override
	public SverResponse<ActionCartVo> deleteCart(Integer userid, Integer productId) {
		// TODO Auto-generated method stub
		if (userid == null || productId == null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//ɾ����Ʒ
		int rs = actionCartDao.deleteCarts(userid,productId);
		if (rs > 0) {
			//�������й��ﳵ��Ϣ
			return this.findAllCarts(userid);
		}
		return SverResponse.createByErrorMessage("ɾ����Ʒʧ��");
	}
	@Override
	public SverResponse<Integer> getCartCount(Integer userid) {
		// TODO Auto-generated method stub
		if (userid == null) {
			return SverResponse.createByErrorMessage("��������");
		}
		int count = actionCartDao.getCartCountByUserId(userid);
		return SverResponse.createRespBySuccess(Integer.valueOf(count));
	}

}
