package com.bigfans.pricingservice.strategy.coupon;

import com.bigfans.pricingservice.processor.PricingProcessContext;
import com.bigfans.pricingservice.model.Coupon;

import java.math.BigDecimal;

/**
 * 
 * @Description: free freight
 * @author lichong
 * @date Apr 25, 2016 4:26:44 AM
 *
 */
public class CouponFFStrategy extends CouponStrategy {

	private BigDecimal conditionPrice;

	public CouponFFStrategy(Coupon coupon) {
		super(coupon);
		this.conditionPrice = coupon.getCondition();
	}

	@Override
	public boolean accept(PricingProcessContext context) {
		if (context.getProdTotalPrice().compareTo(conditionPrice) >= 0) {
			context.setFreeFreight(true);
			accepted = true;
		}
		return accepted;
	}
}
