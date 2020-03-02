package com.bigfans.pricingservice.processor;

import com.bigfans.framework.utils.ArithUtils;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class CartPricingResult implements PricingResult {

    private Integer prodTotalQuantity;
    private BigDecimal prodTotalPrice = BigDecimal.ZERO;
    private BigDecimal totalDeduction = BigDecimal.ZERO;

    private List<PricingItem> pricingItems = new ArrayList<>();

    @Override
    public BigDecimal getOriginalPrice() {
        BigDecimal originalPrice = new BigDecimal(0);
        for(PricingItem itemResult : pricingItems){
            originalPrice = originalPrice.add(itemResult.getOriginalSubTotal());
        }
        return originalPrice;
    }

    @Override
    public BigDecimal getFinalPrice() {
        BigDecimal finalPrice = new BigDecimal(0);
        for(PricingItem itemResult : pricingItems){
            finalPrice = finalPrice.add(itemResult.getSubTotal());
        }
        return finalPrice;
    }

    public BigDecimal getTotalDeduction(){
        for(PricingItem item : pricingItems){
            BigDecimal itemSaved = ArithUtils.sub(item.getOriginalPrice() , item.getSubTotal());
            totalDeduction = ArithUtils.add(totalDeduction , itemSaved);
        }
        return totalDeduction;
    }

    public BigDecimal getProdTotalPrice(){
        for(PricingItem item : pricingItems){
            BigDecimal itemSaved = ArithUtils.sub(item.getOriginalPrice() , item.getSubTotal());
            prodTotalPrice = ArithUtils.add(prodTotalPrice , item.getSubTotal());
        }
        return prodTotalPrice;
    }
}
