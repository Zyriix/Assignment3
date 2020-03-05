package com.bigfans.pricingservice.processor;

import com.bigfans.pricingservice.model.Activity;
import com.bigfans.pricingservice.model.Promotion;
import com.bigfans.pricingservice.strategy.PricingStrategyFactory;
import com.bigfans.pricingservice.strategy.activity.ActivityStrategy;
import com.bigfans.pricingservice.strategy.promotion.PromotionStrategy;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPricingProcessor<R extends PricingResult> implements PricingProcessor<R> {

    protected PricingProcessContext context;
    protected List<PricingItem> items = new ArrayList<>();

    public void addPricingItem(PricingItem item) {
        this.items.add(item);
    }

    public R process() {
        // 创建context
        this.context = createContext();
        return this._process();
    }

    abstract R _process();

    protected PricingProcessContext createContext() {
        PricingProcessContext context = new PricingProcessContext();
        context.setPricingItems(items);
        for (PricingItem item : items) {
            List<Promotion> promotionList = item.getPromotions();
            if (promotionList != null) {
                for (Promotion promotion : promotionList) {
                    PromotionStrategy promotionStrategy = PricingStrategyFactory.createPromotion(promotion, item);
                    if (promotionStrategy != null) {
                        context.addPromotionStrategy(item.getProdId(), promotionStrategy);
                    }
                }
            }
            List<Activity> activityList = item.getActivities();
            if (activityList != null) {
                for (Activity activity : activityList) {
                    ActivityStrategy activityStrategy = PricingStrategyFactory.createActivity(activity, item);
                    if (activityStrategy != null) {
                        context.addActivityStrategy(item.getProdId(), activityStrategy);
                    }
                }
            }
        }
        return context;
    }


}
