package com.bigfans.pricingservice.dao;

import com.bigfans.framework.dao.BaseDAO;
import com.bigfans.pricingservice.model.Activity;

import java.util.List;

/**
 * 
 * @Description:活动DAO
 * @author lichong
 * 2015年6月7日下午12:27:43
 *
 */
public interface ActivityDAO extends BaseDAO<Activity> {
	
	List<Activity> listByProduct(String productId);

}
