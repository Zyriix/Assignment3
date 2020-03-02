package com.bigfans.pricingservice.strategy.coupon;

import com.bigfans.pricingservice.model.Coupon;
import com.bigfans.pricingservice.strategy.PricingStrategy;
import com.bigfans.pricingservice.strategy.PricingStrategyDescription;

/**
 * 
 * @Title: 
 * @Description: 
 * @author lichong 
 * @date 2016年4月25日 上午10:20:38 
 * @version V1.0
 */
public abstract class CouponStrategy implements PricingStrategy {

	protected Coupon coupon;
	protected boolean accepted;
	protected PricingStrategyDescription description;
	
	public CouponStrategy(Coupon coupon) {
		this.coupon = coupon;
	}

	@Override
	public PricingStrategyDescription getDescription() {
		return description;
	}
}
