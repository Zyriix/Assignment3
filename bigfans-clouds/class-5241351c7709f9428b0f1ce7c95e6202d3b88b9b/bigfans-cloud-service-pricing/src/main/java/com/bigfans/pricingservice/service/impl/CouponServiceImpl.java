package com.bigfans.pricingservice.service.impl;

import com.bigfans.framework.dao.BaseServiceImpl;
import com.bigfans.framework.event.ApplicationEventBus;
import com.bigfans.model.event.coupon.CouponCreatedEvent;
import com.bigfans.model.event.coupon.CouponRevertEvent;
import com.bigfans.pricingservice.dao.CouponDAO;
import com.bigfans.pricingservice.model.Coupon;
import com.bigfans.pricingservice.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 * @Description:优惠券服务类
 * @author lichong
 * 2015年7月10日上午9:14:53
 *
 */
@Service(CouponServiceImpl.BEAN_NAME)
public class CouponServiceImpl extends BaseServiceImpl<Coupon> implements CouponService {

	public static final String BEAN_NAME = "couponService";
	
	@Autowired
	private ApplicationEventBus applicationEventBus;

	private CouponDAO couponDAO;
	
	@Autowired
	public CouponServiceImpl(CouponDAO couponDAO) {
		super(couponDAO);
		this.couponDAO = couponDAO;
	}

	@Override
	@Transactional
	public void create(Coupon e) throws Exception {
		super.create(e);
		applicationEventBus.publishEvent(new CouponCreatedEvent(e.getId()));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Coupon> listByProduct(String prodId) throws Exception {
		return couponDAO.listByProduct(prodId);
	}
	
	@Transactional(readOnly = true)
	public List<Coupon> listMyCoupons(String userId) throws Exception {
		return couponDAO.listByUser(userId);
	}

	@Transactional(readOnly = true)
	public List<Coupon> listByCategory(String categoryId) throws Exception {
		return couponDAO.listByCategory(categoryId);
	}

	@Transactional
	public int revert(String couponId,String userId) throws Exception {
		Coupon coupon = couponDAO.get(couponId, null);
		if(!coupon.isExpired()){
			couponDAO.increaseAmount(couponId, 1);
		}
		applicationEventBus.publishEvent(new CouponRevertEvent());
		return 0;
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean checkAcquirable(String userId, String couponId, Integer amount) throws Exception {
		boolean hasRecord = false;
		Coupon coupon = couponDAO.get(couponId, null);
		Integer limitAmount = coupon.getLimitAmount();
		// 超过最大持有量
		if(limitAmount != null && amount > limitAmount){
			throw new Exception("领取数量超过限制");
		}
		Coupon userCoupon = couponDAO.getByUser(userId, couponId);
		if(userCoupon == null){
			hasRecord = true;
		}
//		if(limitAmount != null && userCoupon.getTotalHold() + amount > limitAmount){
//			throw new Exception("您不能领取更多的该优惠劵");
//		}
		return hasRecord;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Coupon> listAvailableByProduct(String prodId) throws Exception {
		return null;
	}
}
