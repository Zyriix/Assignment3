package com.bigfans.pricingservice.service;

import com.bigfans.framework.dao.BaseService;
import com.bigfans.pricingservice.model.Activity;

import java.util.List;

/**
 * 
 * @Description:活动服务类
 * @author lichong
 * 2015年6月7日下午12:42:18
 *
 */
public interface ActivityService extends BaseService<Activity> {

	/**
	 * 查询商品关联的促销
	 * @param prodId
	 * @return
	 * @throws Exception
	 */
	List<Activity> listByProduct(String prodId) throws Exception;
	
}
