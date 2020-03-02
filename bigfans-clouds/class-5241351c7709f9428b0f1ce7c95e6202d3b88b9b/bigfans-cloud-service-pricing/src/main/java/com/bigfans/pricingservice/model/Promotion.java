package com.bigfans.pricingservice.model;

import com.bigfans.pricingservice.model.entity.PromotionEntity;

import java.util.List;

/**
 * 
 * @Description:促销
 * @author lichong 2015年6月6日下午8:59:15
 *
 */
public class Promotion extends PromotionEntity implements PmtActivity {

	private static final long serialVersionUID = -8986026952151935006L;
	
	private List<String> productIdList;
	private boolean accepted;
	private String message;

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getProductIdList() {
		return productIdList;
	}

	public void setProductIdList(List<String> productIdList) {
		this.productIdList = productIdList;
	}
	
}
