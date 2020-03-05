package com.bigfans.pricingservice.strategy.activity;

import com.bigfans.pricingservice.processor.PricingItem;
import com.bigfans.pricingservice.processor.PricingProcessContext;
import com.bigfans.pricingservice.model.Activity;

public class PriceOffActivityStrategy extends ActivityStrategy {

    public PriceOffActivityStrategy(Activity activity, PricingItem pricingItem) {
        super(activity, pricingItem);
    }

    @Override
    public boolean accept(PricingProcessContext context) {
        return false;
    }
}
