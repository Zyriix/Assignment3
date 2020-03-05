package com.bigfans.reviewservice.api;

import com.bigfans.framework.Applications;
import com.bigfans.framework.CurrentUser;
import com.bigfans.framework.annotations.NeedLogin;
import com.bigfans.framework.web.BaseController;
import com.bigfans.framework.web.RestResponse;
import com.bigfans.reviewservice.model.OrderItemSurvey;
import com.bigfans.reviewservice.service.OrderItemSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lichong
 * @create 2018-04-14 上午8:26
 **/
@RestController
public class OrderItemSurveyApi extends BaseController{

    @Autowired
    private OrderItemSurveyService orderItemSurveyService;

    @GetMapping(value = "/orderItemSurveys")
    @NeedLogin
    public RestResponse listByOrder(@RequestParam(value = "orderId") String orderId) throws Exception{
        CurrentUser currentUser = Applications.getCurrentUser();
        List<OrderItemSurvey> itemSurveys = orderItemSurveyService.listByOrder(currentUser.getUid() , orderId);
        return RestResponse.ok(itemSurveys);
    }

}
