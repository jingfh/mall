package cn.techaction.service;

import java.util.List;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.ActionOrderItem;
import cn.techaction.pojo.ActionProduct;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionProductFloorVO;
import cn.techaction.vo.ActionProductListVo;

public interface ActionProductService {
	/**
	 * 
	 * @param product_id
	 * @param parts_id
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public SverResponse<PageBean<ActionProduct>>findProduct(Integer product_id,Integer parts_id
			,Integer pageNum,Integer pageSize);
	/**
	 * 门户：查找热门商品
	 * @param num   查找的数量
	 * @return
	 */
	public SverResponse<List<ActionProduct>> findHotProducts(Integer num);
	
	/**
	 * 门户：获得首页所有楼层数据
	 * @return
	 */
	public SverResponse<ActionProductFloorVO> findFloorProducts();
	
	/**
	 * 门户：根据商品编号查找商品信息
	 * @param id
	 * @return
	 */
	public SverResponse<ActionProduct> findProductDetailForPortal(Integer id);
	
	
	/**
	 * 门户：根据产品类型和配件类型查找商品信息（模糊查询）
	 * @param productTypeId
	 * @param partsId
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public SverResponse<PageBean<ActionProductListVo>> findProductsForProtal(Integer productTypeId, Integer parts_id,
			String name, int pageNum, int pageSize);
	
	
	
	
	/**
	 * 查找关注商品
	 * @param
	 * @return
	 * @author 荆芳浩
	 * @date 2019.07.08
	 */
	public SverResponse<PageBean<ActionProductListVo>> findLikeProducts(Integer id,Integer pageNum,Integer pageSize);
	/**
	 * 添加关注商品
	 * @param
	 * @return
	 * @author jingfh
	 * @date 2019.07.08
	 */
	public SverResponse<String> addLikeProducts(Integer id,Integer productId);
	/**
	 * 取消关注商品
	 * @param
	 * @return
	 * @author jingfh
	 * @date 2019.07.08
	 */
	public SverResponse<PageBean<ActionProductListVo>> cancleLikeProducts(Integer userId,Integer productId,
			Integer pageNum,Integer pageSize);
	/**
	 * 清空关注商品
	 * @param
	 * @return
	 * @author jingfh
	 * @date 2019.07.08
	 */
	public SverResponse<String> clearLikes(Integer userid);
	/**
	 * 添加商品评论
	 * @param
	 * @return
	 * @author jingfh
	 * @date 2019.07.09
	 */
	public SverResponse<String> addComment(Integer userid,Integer productId,Long orderNo,String text);
	/**
	 * 查找商品评论
	 * @param
	 * @return
	 * @author jingfh
	 * @date 2019.07.09
	 */
	public SverResponse<List<ActionOrderItem>> findComment(Integer productId);
}
