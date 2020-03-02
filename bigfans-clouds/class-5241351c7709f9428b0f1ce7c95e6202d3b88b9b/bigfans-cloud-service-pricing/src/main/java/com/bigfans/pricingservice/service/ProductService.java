package com.bigfans.pricingservice.service;

import com.bigfans.framework.dao.BaseService;
import com.bigfans.pricingservice.model.Product;

public interface ProductService extends BaseService<Product>{
	
	Product getById(String pid) throws Exception;
	
}
