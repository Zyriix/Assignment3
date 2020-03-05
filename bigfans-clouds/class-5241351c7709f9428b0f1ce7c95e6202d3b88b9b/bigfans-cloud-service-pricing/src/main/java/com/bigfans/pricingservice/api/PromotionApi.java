package com.bigfans.pricingservice.api;

import com.bigfans.framework.web.RestResponse;
import com.bigfans.pricingservice.model.Promotion;
import com.bigfans.pricingservice.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromotionApi {
	
	@Autowired
	private PromotionService promotionService;

	@PostMapping(value = "/promotion/product")
	public RestResponse create(@RequestBody Promotion promotion) throws Exception{
		promotion.setScope(Promotion.SCOPE_PRODUCT);
		promotionService.create(promotion);
		RestResponse resp = new RestResponse();
		return resp;
	}
	
}
