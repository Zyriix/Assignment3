package com.bigfans.pricingservice.strategy.promotion;

import com.bigfans.framework.utils.ArithUtils;
import com.bigfans.pricingservice.processor.PricingItem;
import com.bigfans.pricingservice.processor.PricingProcessContext;
import com.bigfans.pricingservice.model.Promotion;

import java.math.BigDecimal;

/**
 * 
 * @Description: 满金额免运费
 * @author lichong
 * @date Apr 21, 2016 5:28:33 AM
 *
 */
public class EnoughFreeFreightStrategy extends PromotionStrategy {

	private BigDecimal conditionPrice;

	public EnoughFreeFreightStrategy(Promotion promotion, PricingItem activityProduct) {
		super(promotion , activityProduct);
		this.conditionPrice = new BigDecimal(promotion.getCondition());
	}

	@Override
	public boolean accept(PricingProcessContext context) {
		if (conditionPrice.compareTo(ArithUtils.mul(pricingItem.getOriginalPrice(), pricingItem.getQuantity())) > 1) {
			context.setFreeFreight(true);
			return true;
		}
		return false;
	}
}
