package com.bigfans.pricingservice.processor;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemPricingResult implements PricingResult {

    private BigDecimal originalSubtotal;
    private BigDecimal finalSubtotal;
    private String prodId;

    @Override
    public BigDecimal getOriginalPrice() {
        return getOriginalSubtotal();
    }

    @Override
    public BigDecimal getFinalPrice() {
        return getFinalSubtotal();
    }
}
