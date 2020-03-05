package com.bigfans.pricingservice.dao;

import com.bigfans.framework.dao.BaseDAO;
import com.bigfans.pricingservice.model.Promotion;

import java.util.List;

/**
 * 
 * @Description:促销DAO
 * @author lichong
 * 2015年6月7日下午12:27:43
 *
 */
public interface PromotionDAO extends BaseDAO<Promotion> {
	
	List<Promotion> listByProduct(String productId);

}
