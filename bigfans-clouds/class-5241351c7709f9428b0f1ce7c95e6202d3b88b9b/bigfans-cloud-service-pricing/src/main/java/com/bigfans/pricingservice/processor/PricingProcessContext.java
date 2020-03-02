package com.bigfans.pricingservice.processor;

import com.bigfans.pricingservice.strategy.activity.ActivityStrategy;
import com.bigfans.pricingservice.strategy.promotion.PromotionStrategy;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class PricingProcessContext {

    private BigDecimal freight = BigDecimal.ZERO;
    private boolean freeFreight;
    private boolean isCouponUsed;
    private BigDecimal couponDeduction = BigDecimal.ZERO;
    private boolean isPointUsed;
    private BigDecimal pointDeduction = BigDecimal.ZERO;
    private boolean isBalanceUsed;
    private BigDecimal balanceDeduction = BigDecimal.ZERO;
    private Integer prodTotalQuantity;
    private BigDecimal prodTotalPrice = BigDecimal.ZERO;

    private Float usedPoints;

    // 促销策略
    private Map<String, List<PromotionStrategy>> promotionStrategyMap = new HashMap<>();
    // 活动策略
    private Map<String, List<ActivityStrategy>> activityStrategyMap = new HashMap<>();

    private List<PricingItem> pricingItems = new ArrayList<>();
    // 已经优惠过的商品
    private List<PricingItem> discountedItems = new ArrayList<>();

    public Integer getProdTotalQuantity(){
        int count = 0;
        for(PricingItem item : pricingItems){
            count += item.getQuantity();
        }
        return count;
    }

    public void addPricingItem(PricingItem pricingItem){
        this.pricingItems.add(pricingItem);
    }

    public void addPromotionStrategy(String prodId , PromotionStrategy strategy){
        List<PromotionStrategy> list = this.promotionStrategyMap.get(prodId);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(strategy);
        this.promotionStrategyMap.put(prodId , list);
    }

    public void addPromotionStrategies(String prodId , List<PromotionStrategy> strategy){
        List<PromotionStrategy> list = this.promotionStrategyMap.get(prodId);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.addAll(strategy);
        this.promotionStrategyMap.put(prodId , list);
    }

    public void addActivityStrategy(String prodId , ActivityStrategy strategy){
        List<ActivityStrategy> list = this.activityStrategyMap.get(prodId);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(strategy);
        this.activityStrategyMap.put(prodId , list);
    }

    public void addActivityStrategies(String prodId , List<ActivityStrategy> strategy){
        List<ActivityStrategy> list = this.activityStrategyMap.get(prodId);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.addAll(strategy);
        this.activityStrategyMap.put(prodId , list);
    }
}
