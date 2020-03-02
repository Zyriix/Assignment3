package com.bigfans.pricingservice.service;

import com.bigfans.framework.dao.BaseService;
import com.bigfans.pricingservice.model.Coupon;

import java.util.List;

/**
 * 
 * @Description:
 * @author lichong
 * 2015年7月10日上午9:14:39
 *
 */
public interface CouponService extends BaseService<Coupon> {
	
	List<Coupon> listByProduct(String prodId) throws Exception;
	
	List<Coupon> listByCategory(String categoryId) throws Exception;

	List<Coupon> listAvailableByProduct(String prodId) throws Exception;
	
	boolean checkAcquirable(String userId, String couponId, Integer amount) throws Exception;
}
