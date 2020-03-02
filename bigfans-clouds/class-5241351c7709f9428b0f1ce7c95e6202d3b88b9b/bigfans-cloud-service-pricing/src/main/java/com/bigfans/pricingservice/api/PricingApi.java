package com.bigfans.pricingservice.api;

import com.bigfans.framework.web.RestResponse;
import com.bigfans.model.dto.cart.CartPricingResultDto;
import com.bigfans.model.dto.cart.CartPricingDto;
import com.bigfans.model.dto.order.OrderPricingDto;
import com.bigfans.model.dto.order.OrderPricingResultDto;
import com.bigfans.pricingservice.service.CartPricingService;
import com.bigfans.pricingservice.service.OrderPricingService;
import com.bigfans.pricingservice.service.ProductService;
import com.bigfans.pricingservice.service.impl.CartPricingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lichong
 * @create 2018-02-15 下午2:54
 **/
@RestController
public class PricingApi {

    @Autowired
    private CartPricingService cartPricingService;
    @Autowired
    private OrderPricingService orderPricingService;

    @Autowired
    private ProductService productService;

    @PostMapping("/calculateCart")
    public RestResponse calculateCart(@RequestBody CartPricingDto cartPricingDto) throws Exception{
        CartPricingResultDto resultDto = cartPricingService.calculate(cartPricingDto);
        return RestResponse.ok(resultDto);
    }

    @PostMapping("/calculateOrder")
    public RestResponse calculateOrder(@RequestBody OrderPricingDto orderPricingDto) throws Exception{
        OrderPricingResultDto orderPricingResultDto = orderPricingService.pricingOrder(orderPricingDto);
        return RestResponse.ok(orderPricingResultDto);
    }

    @GetMapping("/product/{prodId}")
    public RestResponse getProductPrice(@PathVariable(value = "prodId") String prodId){

        return RestResponse.ok();
    }

    @PostMapping("/products")
    public RestResponse listProductsPrice(@RequestBody List<String> prodIds){

        return RestResponse.ok();
    }
}
