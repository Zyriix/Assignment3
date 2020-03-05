package com.bigfans.pricingservice.dao.impl;

import com.bigfans.framework.dao.BeanDecorator;
import com.bigfans.framework.dao.MybatisDAOImpl;
import com.bigfans.pricingservice.dao.ProductPromotionDAO;
import com.bigfans.pricingservice.model.ProductPromotion;
import org.springframework.stereotype.Repository;

import java.util.List;



/**
 * 
 * @Title: 
 * @Description: 商品和促销关联表DAO
 * @author lichong 
 * @date 2015年12月21日 下午9:55:11 
 * @version V1.0
 */
@Repository(ProductPromotionDAOImpl.BEAN_NAME)
public class ProductPromotionDAOImpl extends MybatisDAOImpl<ProductPromotion> implements ProductPromotionDAO {

	public static final String BEAN_NAME = "productPromotionDAO";
	
	@Override
	public int batchInsert(List<ProductPromotion> objList) {
		return new BeanDecorator(objList).batchInsert();
	}
	
}
