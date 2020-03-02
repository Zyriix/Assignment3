package com.bigfans.pricingservice.strategy.promotion;

import com.bigfans.pricingservice.processor.PricingItem;
import com.bigfans.pricingservice.model.Promotion;
import com.bigfans.pricingservice.strategy.PricingStrategy;
import com.bigfans.pricingservice.strategy.PricingStrategyDescription;

/**
 * 
 * @Title: 
 * @Description: 
 * @author lichong 
 * @date 2016年4月23日 下午4:37:57 
 * @version V1.0
 */
public abstract class PromotionStrategy implements PricingStrategy {

	protected Promotion promotion;
	protected PricingItem pricingItem;
	protected boolean accepted;
	protected PricingStrategyDescription description;
	
	public PromotionStrategy(Promotion promotion , PricingItem pricingItem) {
		this.promotion = promotion;
		this.pricingItem = pricingItem;
	}

	@Override
	public PricingStrategyDescription getDescription() {
		return description;
	}
}
