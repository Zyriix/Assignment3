package com.bigfans.pricingservice.dao;

import com.bigfans.framework.dao.BaseDAO;
import com.bigfans.pricingservice.model.Coupon;

import java.util.List;

/**
 * 
 * @Description:
 * @author lichong
 * 2015年7月10日上午9:31:49
 *
 */
public interface CouponDAO extends BaseDAO<Coupon> {

	List<Coupon> listByProduct(String productId);
	
	List<Coupon> listByCategory(String categoryId);
	
	List<Coupon> listByUser(String userId);
	
	Coupon getByUser(String userId, String couponId);
	
	Coupon get(String couponId, String userId);
	
	int increaseAmount(String couponId, Integer num);
	
	int decreaseAmount(String couponId, Integer num);
}
