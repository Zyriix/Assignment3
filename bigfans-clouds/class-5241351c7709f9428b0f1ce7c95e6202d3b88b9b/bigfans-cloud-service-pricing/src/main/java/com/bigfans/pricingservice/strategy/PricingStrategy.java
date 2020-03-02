package com.bigfans.pricingservice.strategy;

import com.bigfans.pricingservice.processor.PricingProcessContext;

public interface PricingStrategy {

    boolean accept(PricingProcessContext context);

    PricingStrategyDescription getDescription();

}
