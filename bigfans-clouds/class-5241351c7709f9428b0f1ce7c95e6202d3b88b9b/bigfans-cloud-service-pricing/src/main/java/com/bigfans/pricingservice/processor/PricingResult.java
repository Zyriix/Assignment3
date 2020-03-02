package com.bigfans.pricingservice.processor;

import java.math.BigDecimal;

public interface PricingResult {

    BigDecimal getOriginalPrice();
    BigDecimal getFinalPrice();
}
