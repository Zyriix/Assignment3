package com.bigfans.pricingservice.strategy.promotion;

import com.bigfans.pricingservice.processor.PricingProcessContext;
import com.bigfans.pricingservice.processor.PricingItem;
import com.bigfans.pricingservice.model.Promotion;

/**
 * 
 * @Description:打折策略
 * @author lichong
 * 2015年3月7日下午7:24:08
 *
 */
public class DiscountStrategy extends PromotionStrategy {
	
	private Float discount;

	public DiscountStrategy(Promotion promotion, PricingItem pricingItem) {
		super(promotion , pricingItem);
		this.discount = new Float(promotion.getAction());
	}

	@Override
	public boolean accept(PricingProcessContext context) {
		promotion.setMessage("折扣:"+discount+"折");
		promotion.setAccepted(true);
		return true;
	}

}
