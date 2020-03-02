package com.bigfans.pricingservice.processor;

import com.bigfans.pricingservice.model.Activity;
import com.bigfans.pricingservice.model.Promotion;
import com.bigfans.pricingservice.strategy.PricingStrategyFactory;
import com.bigfans.pricingservice.strategy.activity.ActivityStrategy;
import com.bigfans.pricingservice.strategy.coupon.CouponStrategy;
import com.bigfans.pricingservice.strategy.points.PointsStrategy;
import com.bigfans.pricingservice.strategy.promotion.PromotionStrategy;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class OrderPricingProcessor extends AbstractPricingProcessor<OrderPricingResult> {

    private CouponStrategy couponStrategy;
    private PointsStrategy pointsStrategy;

    public OrderPricingResult _process() {
        // 1、计算商品活动
        Map<String, List<ActivityStrategy>> activityStrategyMap = context.getActivityStrategyMap();
        for (Map.Entry<String, List<ActivityStrategy>> entry : activityStrategyMap.entrySet()) {
            List<ActivityStrategy> strategies = entry.getValue();
            for (ActivityStrategy strategy : strategies) {
                strategy.accept(context);
            }
        }
        // 2. 计算商品优惠
        Map<String, List<PromotionStrategy>> promotionStrategyMap = context.getPromotionStrategyMap();
        for (Map.Entry<String, List<PromotionStrategy>> entry : promotionStrategyMap.entrySet()) {
            List<PromotionStrategy> strategies = entry.getValue();
            for (PromotionStrategy strategy : strategies) {
                strategy.accept(context);
            }
        }
        // 3. 优惠券
        if (couponStrategy != null) {
            couponStrategy.accept(context);
        }
        // 4. 积分
        if (pointsStrategy != null) {
            pointsStrategy.accept(context);
        }

        OrderPricingResult result = new OrderPricingResult();
        result.setFreight(context.getFreight());
        result.setProdTotalQuantity(context.getProdTotalQuantity());
        result.setCouponDeduction(context.getCouponDeduction());
        result.setPointDeduction(context.getPointDeduction());
        result.setBalanceDeduction(context.getBalanceDeduction());
        result.setPricingItems(context.getPricingItems());

        return result;
    }
}
