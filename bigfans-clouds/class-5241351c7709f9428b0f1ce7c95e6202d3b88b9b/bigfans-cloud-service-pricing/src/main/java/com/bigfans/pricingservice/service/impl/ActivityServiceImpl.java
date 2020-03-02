package com.bigfans.pricingservice.service.impl;

import com.bigfans.framework.dao.BaseServiceImpl;
import com.bigfans.pricingservice.dao.ActivityDAO;
import com.bigfans.pricingservice.dao.ProductPromotionDAO;
import com.bigfans.pricingservice.model.Activity;
import com.bigfans.pricingservice.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 * @Description:活动服务实现类
 * @author lichong
 * 2015年6月7日下午12:56:12
 *
 */
@Service(ActivityServiceImpl.BEAN_NAME)
public class ActivityServiceImpl extends BaseServiceImpl<Activity> implements ActivityService {

	public static final String BEAN_NAME = "activityService";

	@Autowired
	private ProductPromotionDAO productPromotionDAO;

	private ActivityDAO activityDAO;

	@Autowired
	public ActivityServiceImpl(ActivityDAO activityDAO) {
		super(activityDAO);
		this.activityDAO = activityDAO;
	}
	
	@Transactional(readOnly=true)
	public List<Activity> listByProduct(String productId) throws Exception {
		return activityDAO.listByProduct(productId);
	}
}
