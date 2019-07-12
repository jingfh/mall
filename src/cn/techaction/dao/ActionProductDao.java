package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.ActionProduct;

public interface ActionProductDao {
	
	
	/**
	 * 查找热门商品
	 * @param num
	 * @return
	 */
	public List<ActionProduct> findHotProducts(Integer num);
	
	
	
	/**
	 * 根据条件获得查询商品的数量
	 * @param product_id
	 * @param parts_id
	 * @return
	 */
	public Integer getTotalCount(Integer product_id,Integer parts_id);
	/**
	 * 
	 * @param product_id
	 * @param parts_id
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ActionProduct> findProductsByInfo(Integer product_id,Integer parts_id,Integer pageNum,Integer pageSize);


	/**
	 * 根据产品类型查询商品信息
	 * @param typeHntjx
	 * @return
	 */
	public List<ActionProduct> findProductsByProductCategory(Integer categoryId);


	/**
	 * 根据商品编号查询商品信息
	 * @param id
	 * @return
	 */
	public ActionProduct findProductsById(Integer id);


	/**
	 * 根据条件查询总记录数
	 * @param product
	 * @return
	 */
	public Integer getTotalCount(ActionProduct product);


	/**
	 * 根据条件分页查询
	 * @param product
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<ActionProduct> findProducts(ActionProduct product, int startIndex, int pageSize);

	
	//更新商品信息
	public int updateProduct(ActionProduct product);
	//删除某个用户购物车中所有商品
	public int deleteCartProduct(Integer uid);
	
	
	
	/**
	 * 查找关注商品
	 * @param userId
	 * @return
	 * @author jingfh
	 * @date 2019.07.08
	 */
	public List<ActionProduct> findLikeProducts(Integer userId);
	/**
	 * 添加关注商品
	 * @param userId，productId
	 * @return
	 * @author jingfh
	 * @date 2019.07.08
	 */
	public int addLikeProduct(Integer userId,Integer productId);
	/**
	 * 取消关注商品
	 * @param userId，productId
	 * @return
	 * @author jingfh
	 * @date 2019.07.08
	 */
	public int cancleLikeProduct(Integer userId,Integer productId);
	/**
	 * 清空关注商品
	 * @param userId，productId
	 * @return
	 * @author jingfh
	 * @date 2019.07.08
	 */
	public int deleteLikeByUserId(Integer userid);
	
}
