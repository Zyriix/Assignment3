package com.bigfans.pricingservice.dao;

import com.bigfans.framework.dao.BaseDAO;
import com.bigfans.pricingservice.model.Product;

public interface ProductDAO extends BaseDAO<Product> {

    Product getById(String id);

}
