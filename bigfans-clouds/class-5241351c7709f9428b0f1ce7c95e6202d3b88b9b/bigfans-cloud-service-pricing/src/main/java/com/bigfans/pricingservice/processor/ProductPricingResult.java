package com.bigfans.pricingservice.processor;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductPricingResult implements PricingResult {

    private List<PricingItem> pricingItems = new ArrayList<>();

    @Override
    public BigDecimal getOriginalPrice() {
        return null;
    }

    @Override
    public BigDecimal getFinalPrice() {
        return null;
    }
}
