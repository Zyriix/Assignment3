package com.bigfans.pricingservice.strategy.coupon;

import com.bigfans.pricingservice.processor.PricingProcessContext;
import com.bigfans.pricingservice.model.Coupon;

import java.math.BigDecimal;

/**
 * 
 * @Description: cut price
 * @author lichong
 * @date Apr 25, 2016 4:26:44 AM
 *
 */
public class CouponCPStrategy extends CouponStrategy {

	private Integer actionPrice;
	private BigDecimal conditionPrice;

	public CouponCPStrategy(Coupon coupon) {
		super(coupon);
		this.conditionPrice = coupon.getCondition();
		this.actionPrice = coupon.getMoney();
	}

	@Override
	public boolean accept(PricingProcessContext context) {
		if (context.getProdTotalPrice().compareTo(conditionPrice) >= 0) {
			context.setCouponDeduction(new BigDecimal(actionPrice));
			context.setCouponUsed(true);
			accepted = true;
		}
		return accepted;
	}
}
