package cn.techaction.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.techaction.common.SverResponse;
import cn.techaction.dao.ActionOrderDao;
import cn.techaction.dao.ActionOrderItemDao;
import cn.techaction.dao.ActionParamsDao;
import cn.techaction.dao.ActionProductDao;
import cn.techaction.pojo.ActionCart;
import cn.techaction.pojo.ActionOrderItem;
import cn.techaction.pojo.ActionProduct;
import cn.techaction.service.ActionProductService;
import cn.techaction.utils.ConstUtil;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionProductFloorVO;
import cn.techaction.vo.ActionProductListVo;

@Service
public class ActionProductServiceImpl implements ActionProductService{
	@Autowired
	private ActionProductDao actionProductDao;
	
	@Autowired
	private ActionParamsDao actionParamsDao;
	
	@Autowired
	private ActionOrderItemDao actionOrderItemDao;
	
	@Override
	public SverResponse<List<ActionProduct>> findHotProducts(Integer num) {
		//ֱ�Ӳ�ѯ��������
		 List<ActionProduct> products = actionProductDao.findHotProducts( num);
		
		
		return SverResponse.createRespBySuccess(products);
	}
	

	
	@Override
	public SverResponse<PageBean<ActionProduct>>findProduct(Integer product_id,Integer parts_id
			,Integer pageNum,Integer pageSize){
		//1.�ȸ���������ò�ѯ����Ʒ����������ҲҪ����dao��
		int totalCount = actionProductDao.getTotalCount(product_id, parts_id);
		PageBean<ActionProduct> pageBean=new PageBean<>(pageNum,pageSize,totalCount);
		//2.����dao���÷�ҳ��ѯ����Ʒ��Ϣ
		pageBean.setData(actionProductDao.findProductsByInfo(product_id, parts_id, pageNum, pageSize));
		
		
		return SverResponse.createRespBySuccess(pageBean);
	}



	@Override
	public SverResponse<ActionProductFloorVO> findFloorProducts() {
		//����vo�������ڷ�װ֮���������
		ActionProductFloorVO vo =new ActionProductFloorVO();
		//һ¥����
		List<ActionProduct> products1 = actionProductDao.findProductsByProductCategory(ConstUtil.ProductType.TYPE_HNTJX);
		vo.setOneFloor(products1);
		//��¥����
		List<ActionProduct> products2 = actionProductDao.findProductsByProductCategory(ConstUtil.ProductType.TYPE_JZQZJJX);
		vo.setTwoFloor(products2);
		
		//��¥����
		List<ActionProduct> products3 = actionProductDao.findProductsByProductCategory(ConstUtil.ProductType.TYPE_GCQZJJX);
		vo.setThreeFloor(products3);
		
		//��¥����
		List<ActionProduct> products4 = actionProductDao.findProductsByProductCategory(ConstUtil.ProductType.TYPE_LMJX);
		vo.setFourFloor(products4);
		
		return SverResponse.createRespBySuccess(vo);
	}



	@Override
	public SverResponse<ActionProduct> findProductDetailForPortal(Integer id) {
		//�жϲ�Ʒ����Ƿ�Ϊ��
		if(id == null){
			return SverResponse.createByErrorMessage("��Ʒ��Ų���Ϊ��!");
		}
		//��ѯ��Ʒ����
		ActionProduct product = actionProductDao.findProductsById(id);
		//�жϲ�Ʒ�Ƿ��¼�
		if(product == null){
			return SverResponse.createByErrorMessage("��Ʒ�Ѿ��¼ܣ�");
		}
		if(product.getStatus()==ConstUtil.ProductStatus.STATUS_OFF_SALE){
			return SverResponse.createByErrorMessage("��Ʒ�Ѿ��¼ܣ�");
		}
		return SverResponse.createRespBySuccess(product);
	}



	@Override
	public SverResponse<PageBean<ActionProductListVo>> findProductsForProtal(Integer productTypeId, Integer parts_id,
			String name, int pageNum, int pageSize) {
		// ���������������ݴ洢
		ActionProduct product = new ActionProduct();
		int totalRecord = 0;
		//�ж�name�Ƿ�Ϊ��
		if(name != null && !name.equals("")){
			product.setName(name);
		}
		if(productTypeId != 0){
			product.setProduct_id(productTypeId);
		}
		if(parts_id!=0){
			product.setParts_id(parts_id);
		}
		//��ǰ����ʾ��Ʒ��Ϊ����
		product.setStatus(2);
		//���ҷ����������ܼ�¼�������ڷ�ҳ��
		totalRecord = actionProductDao.getTotalCount(product);
		//������ҳ����
		PageBean<ActionProductListVo> pageBean = new PageBean<>(pageNum,pageSize,totalRecord);
		//��ȡ����
		List<ActionProduct> products = actionProductDao.findProducts(product,pageBean.getStartIndex(),pageSize);
		//��װ��vo��
		List<ActionProductListVo> voList = Lists.newArrayList();
		for(ActionProduct p:products){
			voList.add(createProductListVo(p));
		}
		pageBean.setData(voList);
		return SverResponse.createRespBySuccess(pageBean);
	}
	
	
	//��װVO����
	private ActionProductListVo createProductListVo(ActionProduct product){
		ActionProductListVo vo = new ActionProductListVo();
		vo.setId(product.getId());
		vo.setName(product.getName());
		vo.setPartsCategory(actionParamsDao.findParamById(product.getParts_id()).getName());
		vo.setProductCategory(actionParamsDao.findParamById(product.getProduct_id()).getName());
		vo.setPrice(product.getPrice());
		vo.setStatus(product.getStatus());
		vo.setIconUrl(product.getIcon_url());
		vo.setStatusDesc(ConstUtil.ProductStatus.getStatusDesc(product.getStatus()));
		vo.setHotStatus(ConstUtil.HotStatus.getHotDesc(product.getIs_hot()));
		vo.setHot(product.getIs_hot());
		return vo;
	}

	/**
	 * ���ҹ�ע��Ʒ
	 * @author jigfh
	 * @date 2019.07.08
	 */
	@Override
	public SverResponse<PageBean<ActionProductListVo>> findLikeProducts(Integer id,Integer pageNum,Integer pageSize) {
		//ֱ�Ӳ�ѯ��������
		List<ActionProduct> products = actionProductDao.findLikeProducts(id);
		//��ҳ
		int totalRecord=products.size();
		
		//������ҳ����
		PageBean<ActionProductListVo> pageBean = new PageBean<>(pageNum,pageSize,totalRecord);
		//��ȡ����
		//List<ActionProduct> products = actionProductDao.findProducts(product,pageBean.getStartIndex(),pageSize);
		//��װ��vo��
		List<ActionProductListVo> voList = Lists.newArrayList();
		for(ActionProduct p:products){
			voList.add(createProductListVo(p));
		}
		pageBean.setData(voList);
		return SverResponse.createRespBySuccess(pageBean);
		
	}
	/**
	 * ��ӹ�ע��Ʒ
	 * @author jigfh
	 * @date 2019.07.08
	 */
	@Override
	public SverResponse<String> addLikeProducts(Integer id,Integer productId) {
		//��֤�����Ƿ���ȷ
		if (id == null || productId == null) {
			return SverResponse.createByErrorMessage("��������");
		}
		
		int rs = actionProductDao.addLikeProduct(id,productId);
		if(rs>0) {
		
			return SverResponse.createRespBySuccessMessage("��Ʒ�ѳɹ���ע");
		}
		return SverResponse.createByErrorMessage("��Ʒ��עʧ��");
	}
	/**
	 * ȡ����ע��Ʒ
	 * @param
	 * @return
	 * @author jingfh
	 * @date 2019.07.08
	 */
	public SverResponse<PageBean<ActionProductListVo>> cancleLikeProducts(Integer userId,
			Integer productId,Integer pageNum,Integer pageSize){
		//��֤�����Ƿ���ȷ
		if (userId == null || productId == null) {
			return SverResponse.createByErrorMessage("��������");
		}
		
		int rs = actionProductDao.cancleLikeProduct(userId,productId);
		if(rs>0) {
		
			return this.findLikeProducts(userId, pageNum, pageSize);
		}
		return SverResponse.createByErrorMessage("��Ʒȡ����עʧ��");
	}
	/**
	 * ��չ�ע��Ʒ
	 * @param
	 * @return
	 * @author jingfh
	 * @date 2019.07.08
	 */
	@Override
	public SverResponse<String> clearLikes(Integer userid) {
		// TODO Auto-generated method stub
		if (userid == null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//��չ��ﳵ���ж���ȷ
		int rs = actionProductDao.deleteLikeByUserId(userid);
		if (rs > 0) {
			return SverResponse.createRespBySuccessMessage("�ɹ���չ�ע��Ʒ");
			
		}
		return SverResponse.createByErrorMessage("��չ�ע��Ʒʧ��");
	}
	/**
	 * �����Ʒ����
	 * @param
	 * @return
	 * @author jingfh
	 * @date 2019.07.09
	 */
	public SverResponse<String> addComment(Integer userId,Integer productId,Long orderNo,String text){
		if (userId == null || productId == null || orderNo==null || text == null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//��չ��ﳵ���ж���ȷ
		ActionOrderItem aoi = new ActionOrderItem();
		aoi.setUpdated(new Date());
		aoi.setOrderNO(orderNo);
		aoi.setComment(text);
		aoi.setUid(userId);
		aoi.setGoodsId(productId);
		int rs = actionOrderItemDao.addComment(aoi);
		if (rs > 0) {
			return SverResponse.createRespBySuccessMessage("�����Ʒ���۳ɹ�");
			
		}
		return SverResponse.createByErrorMessage("�����Ʒ����ʧ��");
	}
	/**
	 * ������Ʒ����
	 * @param
	 * @return
	 * @author jingfh
	 * @date 2019.07.09
	 */
	public SverResponse<List<ActionOrderItem>> findComment(Integer productId){
		if (productId == null ) {
			return SverResponse.createByErrorMessage("��������");
		}
		//��ȡ��Ʒ���ۣ��ж���ȷ
		List<ActionOrderItem> rs = actionOrderItemDao.findComment(productId);
		if (rs == null) {
			return SverResponse.createByErrorMessage("��ȡ��Ʒ����ʧ��");	
		}
		return SverResponse.createRespBySuccess(rs);
	}


}