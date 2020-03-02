package com.bigfans.reviewservice.listener;

import com.bigfans.framework.kafka.KafkaConsumerBean;
import com.bigfans.framework.kafka.KafkaListener;
import com.bigfans.framework.utils.CollectionUtils;
import com.bigfans.model.event.payment.OrderPaidEvent;
import com.bigfans.reviewservice.clients.OrderServiceClient;
import com.bigfans.reviewservice.model.OrderItemSurvey;
import com.bigfans.reviewservice.model.dto.OrderItem;
import com.bigfans.reviewservice.service.OrderItemSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lichong
 * @create 2018-03-15 上午7:25
 **/
@KafkaConsumerBean
@Component
public class PaymentListener {

    @Autowired
    private OrderServiceClient orderServiceClient;
    @Autowired
    private OrderItemSurveyService orderItemSurveyService;

    @KafkaListener
    public void on(OrderPaidEvent event) {
        try {
            String orderId = event.getOrderId();
            List<OrderItem> orderItems = orderServiceClient.getOrderItems(orderId).get();
            List<OrderItemSurvey> feedbacks = new ArrayList<>();
            for(OrderItem item : orderItems){
                OrderItemSurvey feedback = new OrderItemSurvey();
                feedback.setUserId(item.getUserId());
                feedback.setOrderId(item.getOrderId());
                feedback.setOrderItemId(item.getId());
                feedback.setPgId(item.getPgId());
                feedback.setProdId(item.getProdId());
                feedback.setProdName(item.getProdName());
                feedback.setProdImg(item.getProdImg());
                feedback.setPrice(item.getDealSubTotal());
                feedback.setCreateDate(item.getCreateDate());
                feedback.setUpdateDate(item.getUpdateDate());
                feedbacks.add(feedback);
            }
            if(CollectionUtils.isNotEmpty(feedbacks)){
                orderItemSurveyService.batchCreate(feedbacks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
