package com.bigfans.pricingservice.service.impl;

import com.bigfans.model.pricing.product.ProductPricingDto;
import com.bigfans.model.pricing.product.ProductPricingResultDto;
import com.bigfans.pricingservice.model.Activity;
import com.bigfans.pricingservice.model.Product;
import com.bigfans.pricingservice.processor.PricingItem;
import com.bigfans.pricingservice.processor.ProductPricingProcessor;
import com.bigfans.pricingservice.processor.ProductPricingResult;
import com.bigfans.pricingservice.service.ActivityService;
import com.bigfans.pricingservice.service.ProductPricingService;
import com.bigfans.pricingservice.service.ProductService;
import com.bigfans.pricingservice.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductPricingServiceImpl implements ProductPricingService {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private PromotionService promotionService;
    @Autowired
    private ProductService productService;

    public ProductPricingResultDto pricingProducts(ProductPricingDto pricingDto) throws Exception {
        ProductPricingProcessor processor = new ProductPricingProcessor();
        // 添加商品活动信息
        List<String> prodIds = pricingDto.getProdIds();
        for (String prodId : prodIds) {
            Product product = productService.getById(prodId);
            PricingItem pricingItem = new PricingItem();
            pricingItem.setProdId(prodId);
            pricingItem.setCategory(product.getCategoryId());
            pricingItem.setQuantity(1);
            pricingItem.setOriginalPrice(product.getPrice());

            List<Activity> activityList = activityService.listByProduct(prodId);
            pricingItem.setActivities(activityList);
            processor.addPricingItem(pricingItem);
        }
        ProductPricingResult result = processor.process();

        ProductPricingResultDto resultDto = new ProductPricingResultDto();
        return resultDto;
    }
}
