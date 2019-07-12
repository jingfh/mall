package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.ActionProduct;

public interface ActionProductDao {
	
	
	/**
	 * ����������Ʒ
	 * @param num
	 * @return
	 */
	public List<ActionProduct> findHotProducts(Integer num);
	
	
	
	/**
	 * ����������ò�ѯ��Ʒ������
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
	 * ���ݲ�Ʒ���Ͳ�ѯ��Ʒ��Ϣ
	 * @param typeHntjx
	 * @return
	 */
	public List<ActionProduct> findProductsByProductCategory(Integer categoryId);


	/**
	 * ������Ʒ��Ų�ѯ��Ʒ��Ϣ
	 * @param id
	 * @return
	 */
	public ActionProduct findProductsById(Integer id);


	/**
	 * ����������ѯ�ܼ�¼��
	 * @param product
	 * @return
	 */
	public Integer getTotalCount(ActionProduct product);


	/**
	 * ����������ҳ��ѯ
	 * @param product
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<ActionProduct> findProducts(ActionProduct product, int startIndex, int pageSize);

	
	//������Ʒ��Ϣ
	public int updateProduct(ActionProduct product);
	//ɾ��ĳ���û����ﳵ��������Ʒ
	public int deleteCartProduct(Integer uid);
	
	
	
	/**
	 * ���ҹ�ע��Ʒ
	 * @param userId
	 * @return
	 * @author jingfh
	 * @date 2019.07.08
	 */
	public List<ActionProduct> findLikeProducts(Integer userId);
	/**
	 * ��ӹ�ע��Ʒ
	 * @param userId��productId
	 * @return
	 * @author jingfh
	 * @date 2019.07.08
	 */
	public int addLikeProduct(Integer userId,Integer productId);
	/**
	 * ȡ����ע��Ʒ
	 * @param userId��productId
	 * @return
	 * @author jingfh
	 * @date 2019.07.08
	 */
	public int cancleLikeProduct(Integer userId,Integer productId);
	/**
	 * ��չ�ע��Ʒ
	 * @param userId��productId
	 * @return
	 * @author jingfh
	 * @date 2019.07.08
	 */
	public int deleteLikeByUserId(Integer userid);
	
}
