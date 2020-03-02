package com.bigfans.pricingservice.service;

import com.bigfans.model.pricing.product.ProductPricingDto;
import com.bigfans.model.pricing.product.ProductPricingResultDto;

public interface ProductPricingService {

    ProductPricingResultDto pricingProducts(ProductPricingDto pricingDto) throws Exception;

}
