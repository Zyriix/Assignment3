package com.bigfans.pricingservice.dao.impl;

import com.bigfans.framework.dao.MybatisDAOImpl;
import com.bigfans.framework.dao.ParameterMap;
import com.bigfans.pricingservice.dao.ActivityDAO;
import com.bigfans.pricingservice.model.Activity;
import com.bigfans.pricingservice.model.entity.PromotionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 
 * @Description:活动Mapper
 * @author lichong
 * 2015年6月7日下午12:27:43
 *
 */
@Repository(ActivityDAOImpl.BEAN_NAME)
public class ActivityDAOImpl extends MybatisDAOImpl<Activity> implements ActivityDAO {

	public static final String BEAN_NAME = "activityDAO";

	@Override
	public List<Activity> listByProduct(String productId) {
		ParameterMap params = new ParameterMap();
		params.put("productId", productId);
		params.put("scope", PromotionEntity.SCOPE_PRODUCT);
		return getSqlSession().selectList(className + ".list",params);
	}
}
