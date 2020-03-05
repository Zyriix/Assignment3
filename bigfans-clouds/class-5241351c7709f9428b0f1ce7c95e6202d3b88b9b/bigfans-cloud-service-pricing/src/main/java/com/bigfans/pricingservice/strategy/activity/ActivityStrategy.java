package com.bigfans.pricingservice.strategy.activity;

import com.bigfans.pricingservice.processor.PricingItem;
import com.bigfans.pricingservice.model.Activity;
import com.bigfans.pricingservice.strategy.PricingStrategy;
import com.bigfans.pricingservice.strategy.PricingStrategyDescription;

public abstract class ActivityStrategy implements PricingStrategy {

	protected Activity activity;
	protected PricingItem pricingItem;
	protected boolean accepted;
	protected PricingStrategyDescription description;

	public ActivityStrategy(Activity activity , PricingItem pricingItem) {
		this.activity = activity;
		this.pricingItem = pricingItem;
	}
	@Override
	public PricingStrategyDescription getDescription() {
		return description;
	}
}
