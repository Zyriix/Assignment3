package com.bigfans.pricingservice.service.impl;

import com.bigfans.framework.utils.CollectionUtils;
import com.bigfans.model.dto.cart.*;
import com.bigfans.pricingservice.model.Coupon;
import com.bigfans.pricingservice.model.Product;
import com.bigfans.pricingservice.model.Promotion;
import com.bigfans.pricingservice.processor.CartPricingProcessor;
import com.bigfans.pricingservice.processor.CartPricingResult;
import com.bigfans.pricingservice.processor.PricingItem;
import com.bigfans.pricingservice.service.CartPricingService;
import com.bigfans.pricingservice.service.CouponService;
import com.bigfans.pricingservice.service.ProductService;
import com.bigfans.pricingservice.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lichong
 * @create 2018-02-15 下午4:38
 **/
@Service
public class CartPricingServiceImpl implements CartPricingService{

    @Autowired
    private PromotionService promotionService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private ProductService productService;

    public CartPricingResultDto calculate(CartPricingDto cartPricingDto) throws Exception{
        CartPricingProcessor processor = new CartPricingProcessor();
        List<CartItemDto> calculateItems = cartPricingDto.getCalculateItems();
        for(CartItemDto calculateItem : calculateItems) {
            String prodId = calculateItem.getProdId();
            Integer quantity = calculateItem.getQuantity();
            Product product = productService.getById(prodId);
            PricingItem pricingItem = new PricingItem();
            pricingItem.setProdId(prodId);
            pricingItem.setCategory(product.getCategoryId());
            pricingItem.setQuantity(quantity);
            pricingItem.setOriginalPrice(product.getPrice());
            pricingItem.setPrice(product.getPrice());

            List<Promotion> promotionList = promotionService.listByProduct(prodId);
            pricingItem.setPromotions(promotionList);
            processor.addPricingItem(pricingItem);
        }

        // 计算优惠
        CartPricingResult pricingResult = processor.process();

        CartPricingResultDto resultDto = new CartPricingResultDto();
        resultDto.setOriginalTotalPrice(pricingResult.getOriginalPrice());
        resultDto.setTotalPrice(pricingResult.getFinalPrice());

        List<PricingItem> pricingItems = pricingResult.getPricingItems();
        for (PricingItem itemResult : pricingItems) {
            String prodId = itemResult.getProdId();
            CartItemPricingResultDto itemDto = new CartItemPricingResultDto();
            itemDto.setProdId(prodId);
            itemDto.setOriginalPrice(itemResult.getOriginalPrice());
            itemDto.setOriginalSubTotal(itemResult.getOriginalSubTotal());
            itemDto.setPrice(itemResult.getPrice());
            itemDto.setSubtotal(itemResult.getSubTotal());

            // 优惠信息
            List<Promotion> promotions = itemResult.getPromotions();
            if(CollectionUtils.isNotEmpty(promotions)){
                for(Promotion pmt : promotions){
                    CartItemPromotionDto promotionDto = new CartItemPromotionDto();
                    promotionDto.setProdId(prodId);
                    promotionDto.setPromotionId(pmt.getId());
                    promotionDto.setPromotionName(pmt.getName());
                    promotionDto.setSelectable(pmt.isAccepted());
                    resultDto.addPromotion(prodId , promotionDto);
                }
            }

            // 可用的优惠劵
            List<Coupon> coupons = couponService.listAvailableByProduct(prodId);
            if(CollectionUtils.isNotEmpty(coupons)){
                for(Coupon coupon : coupons){
                    CartItemCouponDto cartItemCouponDto = new CartItemCouponDto();
                    cartItemCouponDto.setCouponId(coupon.getId());
                    cartItemCouponDto.setCouponName(coupon.getName());
                    cartItemCouponDto.setProdId(prodId);
                    resultDto.addCoupon(prodId , cartItemCouponDto);
                }
            }
            resultDto.addItemResult(itemDto);
        }

        // 设置计算结果
//        Map<String, BigDecimal> discountedPriceMap = result.getDiscountedPriceMap();
//        Map<String, BigDecimal> subtotalMap = result.getDiscountedSubTotalMap();
//        if(CollectionUtils.isNotEmpty(discountedPriceMap)){
//            discountedPriceMap.forEach((k,v) -> {
//                CartItemPricingDto cartItemPricingDto = new CartItemPricingDto();
//                cartItemPricingDto.setPrice(v);
//                cartItemPricingDto.setSubTotal(subtotalMap.get(k));
//
//            });
//        }

        return resultDto;
    }

}
