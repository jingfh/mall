package cn.techaction.controller.protal;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.ActionProduct;
import cn.techaction.pojo.User;
import cn.techaction.service.ActionProductService;
import cn.techaction.utils.ConstUtil;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionProductFloorVO;
import cn.techaction.vo.ActionProductListVo;

@Controller
@RequestMapping("/product")
public class ActionProductController {
	@Autowired
	private ActionProductService actionProductService;
	//查询商品信息
	/**
	 * 查询热销商品
	 * @param num
	 * @return
	 */
	@RequestMapping("/findhotproducts.do")
	@ResponseBody
	public SverResponse<List<ActionProduct>> findHotProducts(Integer num){
		return actionProductService.findHotProducts(num);
	}
	
	
	/**
	 * 查找楼层商品
	 * @return
	 */
	@RequestMapping("/findfloors.do")
	@ResponseBody
	public SverResponse<ActionProductFloorVO> findFloorProducts(){
		return actionProductService.findFloorProducts();
		
	}
	/**
	 * 根据商品编号获取商品详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/getdetail.do")
	@ResponseBody
	public SverResponse<ActionProduct> getProductDetail(Integer productId){
		return actionProductService.findProductDetailForPortal(productId);
	}
	
	
	
	/**
	 * 根据条件查询商品
	 * @param productTypeId
	 * @param parts_id
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findproducts.do")
	@ResponseBody
	public SverResponse<PageBean<ActionProductListVo>> searchProducts(Integer productTypeId,
			Integer partsId,String name,
			@RequestParam(value = "pageNum",defaultValue="1") int pageNum,
			@RequestParam(value = "pageSize",defaultValue="10") int pageSize){
		
		return actionProductService.findProductsForProtal(productTypeId,partsId,name,pageNum,pageSize);
		
		
	}
	
	
	
	/**
	 * 查询关注商品
	 * @param num
	 * @return
	 * @author 荆芳浩
	 * @date 2019.07.08
	 */
	@RequestMapping("/findlikeproducts.do")
	@ResponseBody
	public SverResponse<PageBean<ActionProductListVo>> findlikeProducts(HttpSession session,
			@RequestParam(value="pageNum",defaultValue="1") int pageNum,
			@RequestParam(value="pageSize",defaultValue="10")int pageSize){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null){
			return SverResponse.createRespBySuccessMessage("用户尚未登录");
		}
		return actionProductService.findLikeProducts(user.getId(),pageNum,pageSize);
	}
	/**
	 * 加入关注商品
	 * @param productId
	 * @return
	 * @author 荆芳浩
	 * @date 2019.07.08
	 */
	@RequestMapping("/addlikeproducts.do")
	@ResponseBody
	public SverResponse<String> addlikeProducts(HttpSession session,Integer productId){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null){
			return SverResponse.createRespBySuccessMessage("用户尚未登录");
		}
		return actionProductService.addLikeProducts(user.getId(),productId);
	}
	/**
	 * 取消关注商品
	 * @param productId
	 * @return
	 * @author 荆芳浩
	 * @date 2019.07.08
	 */
	@RequestMapping("/canclelikeproducts.do")
	@ResponseBody
	public SverResponse<PageBean<ActionProductListVo>> canclelikeProducts(HttpSession session,Integer productId,
			@RequestParam(value="pageNum",defaultValue="1") int pageNum,
			@RequestParam(value="pageSize",defaultValue="10")int pageSize){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null){
			return SverResponse.createRespBySuccessMessage("用户尚未登录");
		}
		return actionProductService.cancleLikeProducts(user.getId(),productId,pageNum,pageSize);
	}
	
	/**
	 * 清空关注商品
	 * @param 
	 * @return
	 * @author 荆芳浩
	 * @date 2019.07.08
	 */
	@RequestMapping("/clearlikes.do")
	@ResponseBody
	public SverResponse<String> clearCarts(HttpSession session){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user == null ) {
			return SverResponse.createByErrorMessage("请登录后，再查看购物车");
		}
		//清空购物车
		return actionProductService.clearLikes(user.getId());
	}
	

}
