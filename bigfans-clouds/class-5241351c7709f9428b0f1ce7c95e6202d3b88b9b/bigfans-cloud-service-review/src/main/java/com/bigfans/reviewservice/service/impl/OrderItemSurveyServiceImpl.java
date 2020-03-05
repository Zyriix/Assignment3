package com.bigfans.reviewservice.service.impl;

import com.bigfans.framework.dao.BaseServiceImpl;
import com.bigfans.reviewservice.dao.OrderItemSurveyDAO;
import com.bigfans.reviewservice.model.OrderItemSurvey;
import com.bigfans.reviewservice.service.OrderItemSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lichong 2014年12月27日上午9:27:48
 * @Description:评论服务类
 */
@Service(OrderItemSurveyServiceImpl.BEAN_NAME)
public class OrderItemSurveyServiceImpl extends BaseServiceImpl<OrderItemSurvey> implements OrderItemSurveyService {

    public static final String BEAN_NAME = "orderItemFeedback";

    private OrderItemSurveyDAO orderItemSurveyDAO;

    @Autowired
    public OrderItemSurveyServiceImpl(OrderItemSurveyDAO orderItemSurveyDAO) {
        super(orderItemSurveyDAO);
        this.orderItemSurveyDAO = orderItemSurveyDAO;
    }

    @Override
    @Transactional
    public int deleteByOrderItem(String userId, String orderItemId) throws Exception {
        return orderItemSurveyDAO.deleteByOrderItem(userId, orderItemId);
    }

    @Override
    public List<OrderItemSurvey> listByOrder(String userId, String orderId) throws Exception {
        return orderItemSurveyDAO.listByOrder(userId, orderId);
    }

    @Override
    public OrderItemSurvey getByOrderItem(String userId, String orderItemId) throws Exception {
        return orderItemSurveyDAO.getByOrderItem(userId, orderItemId);
    }
}
