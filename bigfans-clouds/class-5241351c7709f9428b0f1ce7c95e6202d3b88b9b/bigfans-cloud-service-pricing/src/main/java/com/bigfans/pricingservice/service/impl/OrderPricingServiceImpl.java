package com.bigfans.pricingservice.service.impl;

import com.bigfans.framework.utils.CollectionUtils;
import com.bigfans.model.dto.order.*;
import com.bigfans.pricingservice.model.Coupon;
import com.bigfans.pricingservice.model.Product;
import com.bigfans.pricingservice.model.Promotion;
import com.bigfans.pricingservice.processor.OrderPricingProcessor;
import com.bigfans.pricingservice.processor.OrderPricingResult;
import com.bigfans.pricingservice.processor.PricingItem;
import com.bigfans.pricingservice.service.CouponService;
import com.bigfans.pricingservice.service.OrderPricingService;
import com.bigfans.pricingservice.service.ProductService;
import com.bigfans.pricingservice.service.PromotionService;
import com.bigfans.pricingservice.strategy.PricingStrategyFactory;
import com.bigfans.pricingservice.strategy.coupon.CouponStrategy;
import com.bigfans.pricingservice.strategy.points.PointsCPStrategy;
import com.bigfans.pricingservice.strategy.points.PointsStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(OrderPricingServiceImpl.BEAN_NAME)
public class OrderPricingServiceImpl implements OrderPricingService {

    public static final String BEAN_NAME = "orderPricingService";

    @Autowired
    private PromotionService promotionService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private ProductService productService;

    public OrderPricingResultDto pricingOrder(OrderPricingDto pricingDto) throws Exception {
        OrderPricingProcessor processor = new OrderPricingProcessor();
        // 添加商品活动信息
        List<OrderItemPricingDto> items = pricingDto.getItems();
        for (OrderItemPricingDto itemPricingDto : items) {
            String prodId = itemPricingDto.getProdId();
            Integer quantity = itemPricingDto.getQuantity();
            Product product = productService.getById(prodId);
            PricingItem pricingItem = new PricingItem();
            pricingItem.setProdId(prodId);
            pricingItem.setCategory(product.getCategoryId());
            pricingItem.setQuantity(quantity);
            pricingItem.setPrice(product.getPrice());
            pricingItem.setOriginalPrice(product.getPrice());

            List<Promotion> promotionList = promotionService.listByProduct(prodId);
            pricingItem.setPromotions(promotionList);
            processor.addPricingItem(pricingItem);
        }
        // 优惠券
        if (pricingDto.getUsedCouponId() != null) {
            Coupon coupon = couponService.load(pricingDto.getUsedCouponId());
            CouponStrategy couponStrategy = PricingStrategyFactory.createPromotion(coupon);
            processor.setCouponStrategy(couponStrategy);
        }
        // 积分
        if (pricingDto.getUsedPoints() != null) {
            PointsStrategy pointsStrategy = new PointsCPStrategy(pricingDto.getUsedPoints());
            processor.setPointsStrategy(pointsStrategy);
        }
        // 开始计算
        OrderPricingResult pricingResult = processor.process();

        OrderPricingResultDto resultDto = new OrderPricingResultDto();
        resultDto.setFreight(pricingResult.getFreight());
        resultDto.setOriginalTotalPrice(pricingResult.getOriginalPrice());
        resultDto.setTotalPrice(pricingResult.getFinalPrice());
        resultDto.setProdTotalQuantity(pricingResult.getProdTotalQuantity());
        resultDto.setCouponDeductionTotal(pricingResult.getCouponDeduction());
        resultDto.setPointDeductionTotal(pricingResult.getPointDeduction());
        resultDto.setBalanceDeductionTotal(pricingResult.getBalanceDeduction());

        List<PricingItem> pricingItems = pricingResult.getPricingItems();
        for (PricingItem itemResult : pricingItems) {
            String prodId = itemResult.getProdId();
            OrderItemPricingResultDto itemDto = new OrderItemPricingResultDto();
            itemDto.setProdId(prodId);
            itemDto.setOriginalPrice(itemResult.getOriginalPrice());
            itemDto.setOriginalSubTotal(itemResult.getOriginalSubTotal());
            itemDto.setPrice(itemResult.getPrice());
            itemDto.setSubTotal(itemResult.getSubTotal());

            // 优惠信息
            List<Promotion> promotions = itemResult.getPromotions();
            if(CollectionUtils.isNotEmpty(promotions)){
                for(Promotion pmt : promotions){
                    if(!pmt.isAccepted()){
                        continue;
                    }
                    OrderItemPromotionDto promotionDto = new OrderItemPromotionDto();
                    promotionDto.setProdId(prodId);
                    promotionDto.setPromotionId(pmt.getId());
                    promotionDto.setPromotionName(pmt.getName());
                    resultDto.addPromotion(prodId , promotionDto);
                }
            }
            resultDto.addItemResult(itemDto);
        }
        return resultDto;
    }
}
