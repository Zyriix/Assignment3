package com.bigfans.pricingservice.dao.impl;

import com.bigfans.framework.dao.MybatisDAOImpl;
import com.bigfans.framework.dao.ParameterMap;
import com.bigfans.pricingservice.dao.ProductDAO;
import com.bigfans.pricingservice.model.Product;
import com.bigfans.pricingservice.model.entity.PromotionEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository(ProductDAOImpl.BEAN_NAME)
public class ProductDAOImpl extends MybatisDAOImpl<Product> implements ProductDAO {

	public static final String BEAN_NAME = "productDAO";

	@Override
	public Product getById(String id) {
		ParameterMap params = new ParameterMap();
		params.put("id", id);
		return getSqlSession().selectOne(className + ".load",params);
	}
}
