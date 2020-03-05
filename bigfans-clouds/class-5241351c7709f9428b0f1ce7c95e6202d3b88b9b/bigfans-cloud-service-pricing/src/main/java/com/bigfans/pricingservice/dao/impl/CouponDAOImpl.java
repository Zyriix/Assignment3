package com.bigfans.pricingservice.dao.impl;

import com.bigfans.framework.dao.MybatisDAOImpl;
import com.bigfans.framework.dao.ParameterMap;
import com.bigfans.pricingservice.dao.CouponDAO;
import com.bigfans.pricingservice.model.Coupon;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 
 * @Description:
 * @author lichong
 * 2015年7月10日上午9:31:49
 *
 */
@Repository(CouponDAOImpl.BEAN_NAME)
public class CouponDAOImpl extends MybatisDAOImpl<Coupon> implements CouponDAO {

	public static final String BEAN_NAME = "couponDAO";

	@Override
	public List<Coupon> listByProduct(String productId) {
		ParameterMap params = new ParameterMap();
		params.put("prodId", productId);
		return getSqlSession().selectList(className + ".list", params);
	}

	@Override
	public List<Coupon> listByUser(String userId) {
		ParameterMap params = new ParameterMap();
		params.put("userId", userId);
		return getSqlSession().selectList(className + ".list", params);
	}
	
	@Override
	public List<Coupon> listByCategory(String categoryId) {
		ParameterMap params = new ParameterMap();
		params.put("categoryId", categoryId);
		return getSqlSession().selectList(className + ".list", params);
	}
	
	@Override
	public Coupon get(String couponId, String userId) {
		ParameterMap params = new ParameterMap();
		params.put("userId", userId);
		params.put("couponId", couponId);
		List<Coupon> result = getSqlSession().selectList(className + ".list", params);
		if(result == null || result.isEmpty()){
			return null;
		}
		return result.get(0);
	}
	
	@Override
	public int increaseAmount(String couponId, Integer num) {
		ParameterMap params = new ParameterMap();
		params.put("couponId", couponId);
		params.put("num", num);
		params.put("action", "increase");
		return getSqlSession().update(className + ".updateAmount", params);
	}
	
	@Override
	public int decreaseAmount(String couponId, Integer num) {
		ParameterMap params = new ParameterMap();
		params.put("couponId", couponId);
		params.put("num", num);
		params.put("action", "decrease");
		return getSqlSession().update(className + ".updateAmount", params);
	}
	
	@Override
	public Coupon getByUser(String userId, String couponId) {
		ParameterMap params = new ParameterMap();
		params.put("userId", userId);
		params.put("couponId", couponId);
		List<Coupon> result = getSqlSession().selectList(className + ".list", params);
		if(result == null || result.isEmpty()){
			return null;
		}
		return result.get(0);
	}
}
