package com.bigfans.pricingservice.strategy.promotion;

import com.bigfans.pricingservice.processor.PricingProcessContext;
import com.bigfans.pricingservice.processor.PricingItem;
import com.bigfans.pricingservice.model.Promotion;
import com.bigfans.pricingservice.strategy.PricingStrategyDescription;


/**
 * 满件免运费
 * @Description: 
 * @author lichong
 * @date Apr 21, 2016 5:29:09 AM 
 *
 */
public class EnoughAmountFreeFreightStrategy extends PromotionStrategy {
	
	private Integer conditionAmount;
	
	public EnoughAmountFreeFreightStrategy(Promotion promotion, PricingItem pricingItem) {
		super(promotion , pricingItem);
		this.conditionAmount = Integer.valueOf(promotion.getCondition());
	}

	@Override
	public boolean accept(PricingProcessContext context) {
		if(conditionAmount <= context.getProdTotalQuantity()){
			accepted = true;
			this.description = new PricingStrategyDescription("满" + conditionAmount +"件免运费(已满足)");
			context.setFreeFreight(true);
		}
		promotion.setAccepted(accepted);
		return accepted;
	}

}
