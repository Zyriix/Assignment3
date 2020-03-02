package com.bigfans.pricingservice.strategy.points;

import com.bigfans.pricingservice.strategy.PricingStrategy;
import com.bigfans.pricingservice.strategy.PricingStrategyDescription;

public abstract class PointsStrategy implements PricingStrategy {

    protected Float points;
    protected PricingStrategyDescription description;

    public PointsStrategy(Float points) {
        this.points = points;
    }

    @Override
    public PricingStrategyDescription getDescription() {
        return description;
    }
}
