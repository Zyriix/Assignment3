package com.bigfans.pricingservice.dao.impl;

import com.bigfans.framework.dao.MybatisDAOImpl;
import com.bigfans.framework.dao.ParameterMap;
import com.bigfans.pricingservice.dao.PromotionDAO;
import com.bigfans.pricingservice.model.Promotion;
import com.bigfans.pricingservice.model.entity.PromotionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 
 * @Description:促销Mapper
 * @author lichong
 * 2015年6月7日下午12:27:43
 *
 */
@Repository(PromotionDAOImpl.BEAN_NAME)
public class PromotionDAOImpl extends MybatisDAOImpl<Promotion> implements PromotionDAO {

	public static final String BEAN_NAME = "promotionDAO";

	@Override
	public List<Promotion> listByProduct(String prodId) {
		ParameterMap params = new ParameterMap();
		params.put("prodId", prodId);
		return getSqlSession().selectList(className + ".list",params);
	}

}
