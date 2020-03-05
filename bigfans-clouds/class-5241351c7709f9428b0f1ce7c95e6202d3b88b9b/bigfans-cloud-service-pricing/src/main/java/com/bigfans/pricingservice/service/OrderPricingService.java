package com.bigfans.pricingservice.service;

import com.bigfans.model.dto.order.OrderPricingDto;
import com.bigfans.model.dto.order.OrderPricingResultDto;

public interface OrderPricingService {

    OrderPricingResultDto pricingOrder(OrderPricingDto pricingDto) throws Exception;
}
