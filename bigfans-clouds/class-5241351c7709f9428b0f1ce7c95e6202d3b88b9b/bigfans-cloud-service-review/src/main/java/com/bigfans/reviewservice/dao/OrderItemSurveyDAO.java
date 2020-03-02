package com.bigfans.reviewservice.dao;

import com.bigfans.framework.dao.BaseDAO;
import com.bigfans.reviewservice.model.OrderItemSurvey;

import java.util.List;

/**
 * 
 * @Description:评论
 * @author lichong
 * 2014年12月7日下午3:59:29
 *
 */
public interface OrderItemSurveyDAO extends BaseDAO<OrderItemSurvey> {

    int deleteByOrderItem(String userId , String orderItemId);

    List<OrderItemSurvey> listByOrder(String userId , String orderId);

    OrderItemSurvey getByOrderItem(String userId , String orderItemId);

}
