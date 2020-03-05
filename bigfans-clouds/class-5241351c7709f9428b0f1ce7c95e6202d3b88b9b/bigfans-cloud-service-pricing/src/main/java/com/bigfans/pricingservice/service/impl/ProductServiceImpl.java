package com.bigfans.pricingservice.service.impl;

import com.bigfans.framework.dao.BaseServiceImpl;
import com.bigfans.pricingservice.dao.ProductDAO;
import com.bigfans.pricingservice.model.Product;
import com.bigfans.pricingservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品服务类
 * 
 * @author lichong
 *
 */
@Service(ProductServiceImpl.BEAN_NAME)
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {
	
	public static final String BEAN_NAME = "productService";
	
	private ProductDAO productDAO;
	
	@Autowired
	public ProductServiceImpl(ProductDAO productDAO) {
		super(productDAO);
		this.productDAO = productDAO;
	}


	@Override
	public Product getById(String pid) throws Exception {
		return productDAO.getById(pid);
	}
}
