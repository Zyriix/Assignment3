package com.bigfans.pricingservice.strategy;

import com.bigfans.pricingservice.processor.PricingItem;
import com.bigfans.pricingservice.model.Activity;
import com.bigfans.pricingservice.model.Coupon;
import com.bigfans.pricingservice.model.Promotion;
import com.bigfans.pricingservice.strategy.activity.ActivityStrategy;
import com.bigfans.pricingservice.strategy.coupon.CouponCPStrategy;
import com.bigfans.pricingservice.strategy.coupon.CouponFFStrategy;
import com.bigfans.pricingservice.strategy.coupon.CouponStrategy;
import com.bigfans.pricingservice.strategy.promotion.DiscountStrategy;
import com.bigfans.pricingservice.strategy.promotion.EnoughAmountReduceStrategy;
import com.bigfans.pricingservice.strategy.promotion.EnoughReduceStrategy;
import com.bigfans.pricingservice.strategy.promotion.PromotionStrategy;

public class PricingStrategyFactory {
	
	/**
	 * 优惠券活动
	 * @param coupon
	 * @return
	 */
	public static CouponStrategy createPromotion(Coupon coupon) {
		CouponStrategy strategy = null;
		String code = coupon.getType();
		switch (code) {
		case Coupon.STRATEGY_ER:   // 满减
			strategy = new CouponCPStrategy(coupon);
			break;
		case Coupon.STRATEGY_EFF:  // 满免运费
			strategy = new CouponFFStrategy(coupon);
			break;
		default:
			break;
		}
		return strategy;
	}
	
	/**
	 * 积分活动
	 * @param promotion
	 * @return
	 */
	public PricingStrategy createPromotion(Promotion promotion) {
		PricingStrategy strategy = null;
//		String code = promotion.getType();
//		switch (code) {
//		case CouponEntity.STRATEGY_ER:   // 满减
//			strategy = new CouponCPStrategy((Coupon)processor);
//			break;
//		case CouponEntity.STRATEGY_EFF:  // 满免运费
//			strategy = new CouponFFStrategy((Coupon)processor);
//			break;
//		default:
//			break;
//		}
		return strategy;
	}

	public static ActivityStrategy createActivity(Activity activity , PricingItem pricingItem){
		return null;
	}

	/**
	 * 创建商品活动
	 * @param promotion
	 * @param pricingItem
	 * @return
	 */
	public static PromotionStrategy createPromotion(Promotion promotion , PricingItem pricingItem) {
		PromotionStrategy strategy = null;
		String type = promotion.getType();
		switch (type) {
		case Promotion.TYPE_P_DIS:  // 直接折扣
			strategy = new DiscountStrategy(promotion , pricingItem);
			break;
		case Promotion.TYPE_P_ER:  // 减额减
			strategy = new EnoughReduceStrategy(promotion , pricingItem);
			break;
		case Promotion.TYPE_P_EAR:  // 减件减
			strategy = new EnoughAmountReduceStrategy(promotion , pricingItem);
			break;
		case Promotion.TYPE_P_FP:  // 固定价格出售
			break;
		default:
			break;
		}
		return strategy;
	}
}
