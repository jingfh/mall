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
	 * �Ż�������������Ʒ
	 * @param num   ���ҵ�����
	 * @return
	 */
	public SverResponse<List<ActionProduct>> findHotProducts(Integer num);
	
	/**
	 * �Ż��������ҳ����¥������
	 * @return
	 */
	public SverResponse<ActionProductFloorVO> findFloorProducts();
	
	/**
	 * �Ż���������Ʒ��Ų�����Ʒ��Ϣ
	 * @param id
	 * @return
	 */
	public SverResponse<ActionProduct> findProductDetailForPortal(Integer id);
	
	
	/**
	 * �Ż������ݲ�Ʒ���ͺ�������Ͳ�����Ʒ��Ϣ��ģ����ѯ��
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
	 * ���ҹ�ע��Ʒ
	 * @param
	 * @return
	 * @author ������
	 * @date 2019.07.08
	 */
	public SverResponse<PageBean<ActionProductListVo>> findLikeProducts(Integer id,Integer pageNum,Integer pageSize);
	/**
	 * ��ӹ�ע��Ʒ
	 * @param
	 * @return
	 * @author jingfh
	 * @date 2019.07.08
	 */
	public SverResponse<String> addLikeProducts(Integer id,Integer productId);
	/**
	 * ȡ����ע��Ʒ
	 * @param
	 * @return
	 * @author jingfh
	 * @date 2019.07.08
	 */
	public SverResponse<PageBean<ActionProductListVo>> cancleLikeProducts(Integer userId,Integer productId,
			Integer pageNum,Integer pageSize);
	/**
	 * ��չ�ע��Ʒ
	 * @param
	 * @return
	 * @author jingfh
	 * @date 2019.07.08
	 */
	public SverResponse<String> clearLikes(Integer userid);
	/**
	 * �����Ʒ����
	 * @param
	 * @return
	 * @author jingfh
	 * @date 2019.07.09
	 */
	public SverResponse<String> addComment(Integer userid,Integer productId,Long orderNo,String text);
	/**
	 * ������Ʒ����
	 * @param
	 * @return
	 * @author jingfh
	 * @date 2019.07.09
	 */
	public SverResponse<List<ActionOrderItem>> findComment(Integer productId);
}
