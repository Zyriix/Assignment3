package com.bigfans.pricingservice.api;

import com.bigfans.framework.Applications;
import com.bigfans.framework.CurrentUser;
import com.bigfans.framework.annotations.NeedLogin;
import com.bigfans.framework.web.BaseController;
import com.bigfans.framework.web.RestResponse;
import com.bigfans.pricingservice.model.Coupon;
import com.bigfans.pricingservice.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lichong
 * @create 2018-03-26 下午9:55
 **/
@RestController
public class CouponApi extends BaseController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/coupon/acquire")
    @NeedLogin
    public RestResponse acquire(
            @RequestParam("couponId") String couponId
    ) throws Exception{
        CurrentUser currentUser = Applications.getCurrentUser();
        couponService.checkAcquirable(currentUser.getUid() , couponId , 1);
        return RestResponse.ok();
    }

    @GetMapping("/coupons/{id}")
    public RestResponse get(@PathVariable("id") String couponId) throws Exception{
        Coupon coupon = couponService.load(couponId);
        return RestResponse.ok(coupon);
    }

}
