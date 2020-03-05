package com.bigfans.pricingservice.processor;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemPricingResult implements PricingResult {

    private BigDecimal originalSubtotal;
    private BigDecimal subtotal;
    private BigDecimal price;
    private BigDecimal originalPrice;
    private String prodId;

    @Override
    public BigDecimal getOriginalPrice() {
        return getOriginalSubtotal();
    }

    @Override
    public BigDecimal getFinalPrice() {
        return getSubtotal();
    }
}
