package com.bigfans.pricingservice.service.impl;

import com.bigfans.framework.dao.BaseServiceImpl;
import com.bigfans.framework.utils.CollectionUtils;
import com.bigfans.pricingservice.dao.ProductPromotionDAO;
import com.bigfans.pricingservice.dao.PromotionDAO;
import com.bigfans.pricingservice.model.ProductPromotion;
import com.bigfans.pricingservice.model.Promotion;
import com.bigfans.pricingservice.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Description:促销服务实现类
 * @author lichong
 * 2015年6月7日下午12:56:12
 *
 */
@Service(PromotionServiceImpl.BEAN_NAME)
public class PromotionServiceImpl extends BaseServiceImpl<Promotion> implements PromotionService {

	public static final String BEAN_NAME = "promotionService";
	
	@Autowired
	private ProductPromotionDAO productPromotionDAO;
	
	private PromotionDAO promotionDAO;
	
	@Autowired
	public PromotionServiceImpl(PromotionDAO promotionDAO) {
		super(promotionDAO);
		this.promotionDAO = promotionDAO;
	}
	
	@Override
	@Transactional
	public void create(Promotion e) throws Exception {
		super.create(e);
		List<String> productIdList = e.getProductIdList();
		if(CollectionUtils.isNotEmpty(productIdList)){
			List<ProductPromotion> productPromotionList = new ArrayList<ProductPromotion>();
			for(String pid : productIdList){
				ProductPromotion pp = new ProductPromotion();
				pp.setProductId(pid);
				pp.setPromotionId(e.getId());
				productPromotionList.add(pp);
			}
			productPromotionDAO.batchInsert(productPromotionList);
		}
	}
	
	@Transactional(readOnly=true)
	public List<Promotion> listByProduct(String prodId) throws Exception {
		return promotionDAO.listByProduct(prodId);
	}
	
	@Override
	public List<Promotion> listAllAvailableByProduct(String prodId) throws Exception {
		List<Promotion> all = new ArrayList<Promotion>();
		List<Promotion> prodPromotions = promotionDAO.listByProduct(prodId);
		all.addAll(prodPromotions);
		return all;
	}
}
