package com.bigfans.pricingservice.processor;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderPricingResult implements PricingResult {

    private BigDecimal freight = BigDecimal.ZERO;
    private Integer prodTotalQuantity;
    private BigDecimal couponDeduction = BigDecimal.ZERO;
    private BigDecimal pointDeduction = BigDecimal.ZERO;
    private BigDecimal balanceDeduction = BigDecimal.ZERO;
    private BigDecimal totalDeduction = BigDecimal.ZERO;

    private List<PricingItem> pricingItems = new ArrayList<>();

    @Override
    public BigDecimal getOriginalPrice() {
        BigDecimal originalPrice = new BigDecimal(0);
        for(PricingItem pricingItem : pricingItems){
            originalPrice = originalPrice.add(pricingItem.getOriginalSubTotal());
        }
        return originalPrice;
    }

    @Override
    public BigDecimal getFinalPrice() {
        BigDecimal finalPrice = new BigDecimal(0);
        for(PricingItem pricingItem : pricingItems){
            finalPrice = finalPrice.add(pricingItem.getSubTotal());
        }
        finalPrice.add(freight);
        return finalPrice;
    }
}
