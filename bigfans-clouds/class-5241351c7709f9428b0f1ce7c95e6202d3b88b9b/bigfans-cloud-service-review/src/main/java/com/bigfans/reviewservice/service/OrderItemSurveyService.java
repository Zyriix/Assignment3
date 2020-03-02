package com.bigfans.reviewservice.service;

import com.bigfans.framework.dao.BaseService;
import com.bigfans.reviewservice.model.OrderItemSurvey;

import java.util.List;

/**
 * 
 * @Description:商品待评价服务接口
 * @author lichong 2014年12月27日上午9:15:29
 *
 */
public interface OrderItemSurveyService extends BaseService<OrderItemSurvey> {

	int deleteByOrderItem(String userId , String orderItemId) throws Exception;

	List<OrderItemSurvey> listByOrder(String userId , String orderId) throws Exception;

	OrderItemSurvey getByOrderItem(String userId , String orderItemId) throws Exception;

}
