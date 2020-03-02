package com.bigfans.pricingservice.strategy;

import lombok.Data;

@Data
public class PricingStrategyDescription {

    private String description;

    public PricingStrategyDescription(String description) {
        this.description = description;
    }

    public PricingStrategyDescription() {
        this("");
    }
}
