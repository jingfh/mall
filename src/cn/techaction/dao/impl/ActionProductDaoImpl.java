package cn.techaction.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;

import cn.techaction.dao.ActionProductDao;
import cn.techaction.pojo.ActionProduct;


@Repository
public class ActionProductDaoImpl implements ActionProductDao{
	@Autowired
	private QueryRunner queryRunner;
	
	private String alias = "id,name,product_id,parts_id,icon_url,sub_images,detail,spec_param,price,stock,status,is_hot,created,updated";
	

	@Override
	public List<ActionProduct> findHotProducts(Integer num) {
		String sql ="select "+ this.alias +" from action_products  where is_hot = 1 and status = 2 order by updated,id desc";

		if(num != null){
			sql+=" limit 0,?";
		}
		try{
			if(num != null){
				return queryRunner.query(sql, new BeanListHandler<ActionProduct>(ActionProduct.class), num);
			}else{
				return queryRunner.query(sql, new BeanListHandler<ActionProduct>(ActionProduct.class));
			}
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}	
	}

	
	
	@Override
	public Integer getTotalCount(Integer product_id,Integer parts_id){
		String sql = "select count(*) as num from action_products where 1=1";
		List <Object>params = new ArrayList<>();
		if(product_id!=null){
			sql+=" and product_id=?";
			params.add(product_id);
		}
		if(parts_id!=null){
			sql+=" and parts_id =?";
			params.add(parts_id);
		}
		try{
			return queryRunner.query(sql,new ColumnListHandler<Long>("num"),params.toArray())
					.get(0).intValue();
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<ActionProduct> findProductsByInfo(Integer product_id, Integer parts_id, Integer pageNum,
			Integer pageSize) {

		String sql="select id,name,product_id,parts_id,icon_url,sub_images,detail,spec_param,price,stock,status,is_hot,created,updated from action_products where 1 = 1";
		List <Object>params = new ArrayList<>();
		if(product_id!=null){
			sql+="and product_id=?";
			params.add(product_id);
		}
		if(parts_id!=null){
			sql+="and parts_id =?";
			params.add(parts_id);
		}try{
			return queryRunner.query(sql, new BeanListHandler<ActionProduct>(ActionProduct.class
					),params.toArray()) ;
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public List<ActionProduct> findProductsByProductCategory(Integer categoryId) {
		String sql = "select "+this.alias+" from action_products where product_id = ? and status = 2 order by updated desc";
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionProduct>(ActionProduct.class), categoryId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}



	@Override
	public ActionProduct findProductsById(Integer id) {
		String sql ="select "+this.alias+" from action_products where id =?";
		try {
			return queryRunner.query(sql, new BeanHandler<ActionProduct>(ActionProduct.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}



	@Override
	public Integer getTotalCount(ActionProduct product) {
		
		String sql = "select count(id) as num from action_products where 1=1";
		List<Object> params = new ArrayList<>();
		if(product.getId()!=null){
			sql+=" and id = ?";
			params.add(product.getId());
		}if(product.getName()!=null){
			sql+=" and name like ?";
			params.add("%"+product.getName()+"%");
		}
		if(product.getStatus()!=null){
			sql+=" and status = ?";
			params.add(product.getStatus());
		}
		if(product.getProduct_id()!=null){
			sql+=" and product_id = ?";
			params.add(product.getProduct_id());
		}
		if(product.getParts_id()!=null){
			sql+=" and parts_id = ?";
			params.add(product.getParts_id());
		}
		 try {
			return queryRunner.query(sql, new ColumnListHandler<Long>("num"), params.toArray()).get(0).intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	
	}



	@Override
	public List<ActionProduct> findProducts(ActionProduct product, int startIndex, int pageSize) {
		String sql = "select " +this.alias+ " from action_products where 1=1";
		List<Object> params = new ArrayList();
		if(product.getId()!= null){
			sql+=" and id = ?";
			params.add(product.getId());
		}if(product.getName()!= null){
			sql+=" and name like  ?";
			params.add("%"+product.getName()+"%");
		}
		if(product.getStatus()!= null){
			sql+=" and status = ?";
			params.add(product.getStatus());
		}
		if(product.getProduct_id()!= null){
			sql+=" and product_id = ?";
			params.add(product.getProduct_id());
		}
		if(product.getParts_id()!= null){
			sql+=" and parts_id = ?";
			params.add(product.getParts_id());
		}
		sql+=" limit ?,?";
		params.add(startIndex);
		params.add(pageSize);
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionProduct>(ActionProduct.class), params.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	@Override
	public int updateProduct(ActionProduct product) {
		// TODO Auto-generated method stub
		String sql = "update action_products set updated = ?";
		List<Object> Params = new  ArrayList<>();
		Params.add(product.getUpdated());
		if (!StringUtils.isEmpty(product.getName())) {
			sql+=" ,name = ?";
			Params.add(product.getName());
		}
		if (product.getProduct_id()!=null) {
			sql+=" ,product_id = ?";
			Params.add(product.getProduct_id());
		}
		if (product.getParts_id()!=null) {
			sql+=" ,parts_id = ?";
			Params.add(product.getParts_id());
		}
		if (product.getPrice()!=null) {
			sql+=" ,price = ?";
			Params.add(product.getPrice());
		}
		if (product.getStock()!=null) {
			sql+=" ,stock = ?";
			Params.add(product.getStock());
		}
		if (product.getParts_id()!=null) {
			sql+=" ,parts_id = ?";
			Params.add(product.getParts_id());
		}
		if (!StringUtils.isEmpty(product.getIcon_url())) {
			sql+=" ,icon_url = ?";
			Params.add(product.getIcon_url());
		}
		if (!StringUtils.isEmpty(product.getSub_images())) {
			sql+=" ,sub_images = ?";
			Params.add(product.getSub_images());
		}
		if (product.getStatus()!=null) {
			sql+=" ,status = ?";
			Params.add(product.getStatus());
		}
		if (!StringUtils.isEmpty(product.getDetail())) {
			sql+=" ,detail = ?";
			Params.add(product.getDetail());
		}
		if (!StringUtils.isEmpty(product.getSpec_param())) {
			sql+=" ,spec_param = ?";
			Params.add(product.getSpec_param());
		}
		if (product.getIs_hot()!=null) {
			sql+=" ,is_hot = ?";
			Params.add(product.getIs_hot());
		}
		sql+= " where id = ?";
		Params.add(product.getId());
		try {
			return queryRunner.update(sql,Params.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int deleteCartProduct(Integer uid) {
		// TODO Auto-generated method stub
		String sql = "delete from action_carts where user_id = ? and checked = 1 ";
		try {
			return queryRunner.update(sql,uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	
	/*
	 * 查找关注商品
	 * @author jingfh
	 * @date 2019.07.08
	 */
	@Override
	public ArrayList<ActionProduct> findLikeProducts(Integer userId) {
		String sql_01 ="select product_id from action_like  where user_id = ? and del=0";
		ArrayList<Integer> pro;
		ArrayList<ActionProduct> list = new ArrayList<ActionProduct>()  ;
		try {
			pro = (ArrayList<Integer>) queryRunner.query(sql_01, new ColumnListHandler<Integer>("product_id"),userId);
			
			String sql_02 ="select "+ this.alias +" from action_products  where id = ?";
			for(Integer proId:pro) {
				
			List<ActionProduct> rs = queryRunner.query(sql_02, new BeanListHandler<ActionProduct>(ActionProduct.class),proId);
			if(rs.size()>0) {
				list.add((ActionProduct)rs.get(0));
			}	
			
		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return list;
	}
	/*
	 * 添加关注商品
	 * @author jingfh
	 * @date 2019.07.08
	 */
	@Override
	public int addLikeProduct(Integer userId,Integer productId) {
		// TODO Auto-generated method stub
		ArrayList<ActionProduct> now_like = this.findLikeProducts(userId);
		ActionProduct now_pro = this.findProductsById(productId);
		for (ActionProduct p:now_like) {
			if(p.getId()==now_pro.getId()) {
				return 0;
			}
		}
		
		String sql = "insert into action_like(user_id,product_id) value(?,?)";
		
		Object[] params = {userId,productId};
		try {
			return queryRunner.update(sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	/*
	 * 取消关注商品
	 * @author jingfh
	 * @date 2019.07.08
	 */
	@Override
	public int cancleLikeProduct(Integer userId,Integer productId) {
		// TODO Auto-generated method stub
		String sql = "update action_like set del=1 where user_id=? and product_id = ?" ;
		
		Object[] params = {userId,productId};
		try {
			return queryRunner.update(sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	/*
	 * 清空关注商品
	 * @author jingfh
	 * @date 2019.07.08
	 */
	@Override
	public int deleteLikeByUserId(Integer userid) {
		// TODO Auto-generated method stub
		String sql = "delete from action_like where user_id = ?";
		try {
			return queryRunner.update(sql,userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}


}
		

