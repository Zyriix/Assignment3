package com.bigfans.pricingservice.service;

import com.bigfans.model.dto.cart.CartPricingDto;
import com.bigfans.model.dto.cart.CartPricingResultDto;

public interface CartPricingService {

    CartPricingResultDto calculate(CartPricingDto cartPricingDto) throws Exception;

}
