package com.bigfans.pricingservice.processor;

import java.util.ArrayList;
import java.util.List;

public class ProductPricingProcessor extends AbstractPricingProcessor<ProductPricingResult> {

    @Override
    public ProductPricingResult _process() {
        ProductPricingResult result = new ProductPricingResult();
        result.setPricingItems(context.getPricingItems());
        return result;
    }
}
