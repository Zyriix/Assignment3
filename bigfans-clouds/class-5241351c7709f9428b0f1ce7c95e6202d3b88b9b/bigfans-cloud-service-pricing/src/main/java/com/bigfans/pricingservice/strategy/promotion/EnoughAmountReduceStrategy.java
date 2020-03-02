package com.bigfans.pricingservice.strategy.promotion;

import com.bigfans.framework.utils.ArithUtils;
import com.bigfans.pricingservice.processor.PricingProcessContext;
import com.bigfans.pricingservice.processor.PricingItem;
import com.bigfans.pricingservice.model.Promotion;

import java.math.BigDecimal;

/**
 * 
 * @Description:满减策略
 * @author lichong
 * 2015年3月7日下午7:05:27
 *
 */
public class EnoughAmountReduceStrategy extends PromotionStrategy {
	
	private Integer conditionQuantity ;
	private BigDecimal reduce;
	
	public EnoughAmountReduceStrategy(Promotion promotion, PricingItem pricingItem) {
		super(promotion , pricingItem);
		this.conditionQuantity = new BigDecimal(promotion.getCondition()).intValue();
		this.reduce = new BigDecimal(promotion.getAction());
	}

	@Override
	public boolean accept(PricingProcessContext context) {
		BigDecimal subtotal = pricingItem.getSubTotal();
		if(pricingItem.getQuantity().compareTo(conditionQuantity) >= 0){
			promotion.setMessage("满"+conditionQuantity+"件减"+reduce + "(已减)");
			accepted = true;
			BigDecimal discountedSubTotal = ArithUtils.sub(subtotal , reduce);
			pricingItem.setSubTotal(discountedSubTotal);
		}
		promotion.setAccepted(accepted);
		return accepted;
	}
}
