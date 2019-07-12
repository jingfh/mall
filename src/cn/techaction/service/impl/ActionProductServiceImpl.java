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
		//直接查询所需数据
		 List<ActionProduct> products = actionProductDao.findHotProducts( num);
		
		
		return SverResponse.createRespBySuccess(products);
	}
	

	
	@Override
	public SverResponse<PageBean<ActionProduct>>findProduct(Integer product_id,Integer parts_id
			,Integer pageNum,Integer pageSize){
		//1.先根据条件获得查询的商品的总条数，也要调用dao层
		int totalCount = actionProductDao.getTotalCount(product_id, parts_id);
		PageBean<ActionProduct> pageBean=new PageBean<>(pageNum,pageSize,totalCount);
		//2.调用dao层获得分页查询的商品信息
		pageBean.setData(actionProductDao.findProductsByInfo(product_id, parts_id, pageNum, pageSize));
		
		
		return SverResponse.createRespBySuccess(pageBean);
	}



	@Override
	public SverResponse<ActionProductFloorVO> findFloorProducts() {
		//创建vo对象，用于封装之后传输的数据
		ActionProductFloorVO vo =new ActionProductFloorVO();
		//一楼数据
		List<ActionProduct> products1 = actionProductDao.findProductsByProductCategory(ConstUtil.ProductType.TYPE_HNTJX);
		vo.setOneFloor(products1);
		//二楼数据
		List<ActionProduct> products2 = actionProductDao.findProductsByProductCategory(ConstUtil.ProductType.TYPE_JZQZJJX);
		vo.setTwoFloor(products2);
		
		//三楼数据
		List<ActionProduct> products3 = actionProductDao.findProductsByProductCategory(ConstUtil.ProductType.TYPE_GCQZJJX);
		vo.setThreeFloor(products3);
		
		//四楼数据
		List<ActionProduct> products4 = actionProductDao.findProductsByProductCategory(ConstUtil.ProductType.TYPE_LMJX);
		vo.setFourFloor(products4);
		
		return SverResponse.createRespBySuccess(vo);
	}



	@Override
	public SverResponse<ActionProduct> findProductDetailForPortal(Integer id) {
		//判断产品编号是否为空
		if(id == null){
			return SverResponse.createByErrorMessage("产品编号不能为空!");
		}
		//查询商品详情
		ActionProduct product = actionProductDao.findProductsById(id);
		//判断产品是否下架
		if(product == null){
			return SverResponse.createByErrorMessage("产品已经下架！");
		}
		if(product.getStatus()==ConstUtil.ProductStatus.STATUS_OFF_SALE){
			return SverResponse.createByErrorMessage("产品已经下架！");
		}
		return SverResponse.createRespBySuccess(product);
	}



	@Override
	public SverResponse<PageBean<ActionProductListVo>> findProductsForProtal(Integer productTypeId, Integer parts_id,
			String name, int pageNum, int pageSize) {
		// 创建对象用于数据存储
		ActionProduct product = new ActionProduct();
		int totalRecord = 0;
		//判断name是否为空
		if(name != null && !name.equals("")){
			product.setName(name);
		}
		if(productTypeId != 0){
			product.setProduct_id(productTypeId);
		}
		if(parts_id!=0){
			product.setParts_id(parts_id);
		}
		//让前端显示商品都为在售
		product.setStatus(2);
		//查找符合条件的总记录数（用于分页）
		totalRecord = actionProductDao.getTotalCount(product);
		//创建分页对象
		PageBean<ActionProductListVo> pageBean = new PageBean<>(pageNum,pageSize,totalRecord);
		//读取数据
		List<ActionProduct> products = actionProductDao.findProducts(product,pageBean.getStartIndex(),pageSize);
		//封装到vo里
		List<ActionProductListVo> voList = Lists.newArrayList();
		for(ActionProduct p:products){
			voList.add(createProductListVo(p));
		}
		pageBean.setData(voList);
		return SverResponse.createRespBySuccess(pageBean);
	}
	
	
	//封装VO对象
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
	 * 查找关注商品
	 * @author jigfh
	 * @date 2019.07.08
	 */
	@Override
	public SverResponse<PageBean<ActionProductListVo>> findLikeProducts(Integer id,Integer pageNum,Integer pageSize) {
		//直接查询所需数据
		List<ActionProduct> products = actionProductDao.findLikeProducts(id);
		//分页
		int totalRecord=products.size();
		
		//创建分页对象
		PageBean<ActionProductListVo> pageBean = new PageBean<>(pageNum,pageSize,totalRecord);
		//读取数据
		//List<ActionProduct> products = actionProductDao.findProducts(product,pageBean.getStartIndex(),pageSize);
		//封装到vo里
		List<ActionProductListVo> voList = Lists.newArrayList();
		for(ActionProduct p:products){
			voList.add(createProductListVo(p));
		}
		pageBean.setData(voList);
		return SverResponse.createRespBySuccess(pageBean);
		
	}
	/**
	 * 添加关注商品
	 * @author jigfh
	 * @date 2019.07.08
	 */
	@Override
	public SverResponse<String> addLikeProducts(Integer id,Integer productId) {
		//验证参数是否正确
		if (id == null || productId == null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		
		int rs = actionProductDao.addLikeProduct(id,productId);
		if(rs>0) {
		
			return SverResponse.createRespBySuccessMessage("商品已成功关注");
		}
		return SverResponse.createByErrorMessage("商品关注失败");
	}
	/**
	 * 取消关注商品
	 * @param
	 * @return
	 * @author jingfh
	 * @date 2019.07.08
	 */
	public SverResponse<PageBean<ActionProductListVo>> cancleLikeProducts(Integer userId,
			Integer productId,Integer pageNum,Integer pageSize){
		//验证参数是否正确
		if (userId == null || productId == null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		
		int rs = actionProductDao.cancleLikeProduct(userId,productId);
		if(rs>0) {
		
			return this.findLikeProducts(userId, pageNum, pageSize);
		}
		return SverResponse.createByErrorMessage("商品取消关注失败");
	}
	/**
	 * 清空关注商品
	 * @param
	 * @return
	 * @author jingfh
	 * @date 2019.07.08
	 */
	@Override
	public SverResponse<String> clearLikes(Integer userid) {
		// TODO Auto-generated method stub
		if (userid == null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		//清空购物车，判断正确
		int rs = actionProductDao.deleteLikeByUserId(userid);
		if (rs > 0) {
			return SverResponse.createRespBySuccessMessage("成功清空关注商品");
			
		}
		return SverResponse.createByErrorMessage("清空关注商品失败");
	}
	/**
	 * 添加商品评论
	 * @param
	 * @return
	 * @author jingfh
	 * @date 2019.07.09
	 */
	public SverResponse<String> addComment(Integer userId,Integer productId,Long orderNo,String text){
		if (userId == null || productId == null || orderNo==null || text == null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		//清空购物车，判断正确
		ActionOrderItem aoi = new ActionOrderItem();
		aoi.setUpdated(new Date());
		aoi.setOrderNO(orderNo);
		aoi.setComment(text);
		aoi.setUid(userId);
		aoi.setGoodsId(productId);
		int rs = actionOrderItemDao.addComment(aoi);
		if (rs > 0) {
			return SverResponse.createRespBySuccessMessage("添加商品评论成功");
			
		}
		return SverResponse.createByErrorMessage("添加商品评论失败");
	}
	/**
	 * 查找商品评论
	 * @param
	 * @return
	 * @author jingfh
	 * @date 2019.07.09
	 */
	public SverResponse<List<ActionOrderItem>> findComment(Integer productId){
		if (productId == null ) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		//获取商品评论，判断正确
		List<ActionOrderItem> rs = actionOrderItemDao.findComment(productId);
		if (rs == null) {
			return SverResponse.createByErrorMessage("获取商品评论失败");	
		}
		return SverResponse.createRespBySuccess(rs);
	}


}