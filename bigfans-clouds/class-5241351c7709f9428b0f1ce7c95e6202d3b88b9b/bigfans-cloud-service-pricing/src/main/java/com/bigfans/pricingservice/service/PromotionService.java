package com.bigfans.pricingservice.service;

import com.bigfans.framework.dao.BaseService;
import com.bigfans.pricingservice.model.Promotion;

import java.util.List;

/**
 * 
 * @Description:促销服务类
 * @author lichong
 * 2015年6月7日下午12:42:18
 *
 */
public interface PromotionService extends BaseService<Promotion> {

	/**
	 * 查询商品关联的促销
	 * @param prodId
	 * @return
	 * @throws Exception
	 */
	List<Promotion> listByProduct(String prodId) throws Exception;
	
	/**
	 * 获取当前商品所有可使用的促销信息
	 * @param prodId
	 * @return
	 * @throws Exception
	 */
	List<Promotion> listAllAvailableByProduct(String prodId) throws Exception;
}
