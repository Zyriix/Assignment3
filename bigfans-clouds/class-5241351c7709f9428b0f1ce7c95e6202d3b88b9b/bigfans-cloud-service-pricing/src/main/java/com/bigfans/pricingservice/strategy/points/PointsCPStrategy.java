package com.bigfans.pricingservice.strategy.points;

import com.bigfans.pricingservice.processor.PricingProcessContext;

public class PointsCPStrategy extends PointsStrategy {

    public PointsCPStrategy(Float points) {
        super(points);
    }

    @Override
    public boolean accept(PricingProcessContext context) {
        return false;
    }

}
