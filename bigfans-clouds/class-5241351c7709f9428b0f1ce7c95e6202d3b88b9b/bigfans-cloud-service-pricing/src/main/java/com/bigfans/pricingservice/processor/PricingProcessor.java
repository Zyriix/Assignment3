package com.bigfans.pricingservice.processor;

public interface PricingProcessor<R extends PricingResult> {

    R process();

}
