package com.bigfans.reviewservice.dao.impl;

import com.bigfans.framework.dao.MybatisDAOImpl;
import com.bigfans.framework.dao.ParameterMap;
import com.bigfans.reviewservice.dao.OrderItemSurveyDAO;
import com.bigfans.reviewservice.model.OrderItemSurvey;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @Description:评论
 * @author lichong 2014年12月7日下午3:59:29
 *
 */
@Repository(OrderItemSurveyDAOImpl.BEAN_NAME)
public class OrderItemSurveyDAOImpl extends MybatisDAOImpl<OrderItemSurvey> implements OrderItemSurveyDAO {

	public static final String BEAN_NAME = "orderItemSurveyDAO";

	@Override
	public int deleteByOrderItem(String userId , String orderItemId) {
		ParameterMap params = new ParameterMap();
		params.put("userId", userId);
		params.put("orderItemId", orderItemId);
		return getSqlSession().delete(className + ".delete", params);
	}

	@Override
	public List<OrderItemSurvey> listByOrder(String userId , String orderId) {
		ParameterMap params = new ParameterMap();
		params.put("userId", userId);
		params.put("orderId", orderId);
		return getSqlSession().selectList(className + ".list", params);
	}

	public OrderItemSurvey getByOrderItem(String userId , String orderItemId){
		ParameterMap params = new ParameterMap();
		params.put("userId", userId);
		params.put("orderItemId", orderItemId);
		return getSqlSession().selectOne(className + ".load", params);
	}
}
