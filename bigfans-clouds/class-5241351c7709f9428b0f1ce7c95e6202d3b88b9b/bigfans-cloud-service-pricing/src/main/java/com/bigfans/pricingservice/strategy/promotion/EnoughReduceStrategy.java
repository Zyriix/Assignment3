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
public class EnoughReduceStrategy extends PromotionStrategy{
	
	private BigDecimal enough;
	private BigDecimal reduce;

	public EnoughReduceStrategy(Promotion promotion , PricingItem pricingItem) {
		super(promotion , pricingItem);
		this.enough = new BigDecimal(promotion.getCondition());
		this.reduce = new BigDecimal(promotion.getAction());
	}

	@Override
	public boolean accept(PricingProcessContext context) {
		BigDecimal subTotal = pricingItem.getSubTotal();
		if(subTotal.compareTo(enough) >= 0){
			promotion.setMessage("满"+enough+"减"+reduce + "(已减)");
			accepted = true;
			BigDecimal discountedSubTotal = ArithUtils.sub(subTotal , reduce);
			pricingItem.setSubTotal(discountedSubTotal);
		}
		promotion.setAccepted(accepted);
//		context.addPromotion(pricingItem.getProdId() , promotion);
		return accepted;
	}
}
