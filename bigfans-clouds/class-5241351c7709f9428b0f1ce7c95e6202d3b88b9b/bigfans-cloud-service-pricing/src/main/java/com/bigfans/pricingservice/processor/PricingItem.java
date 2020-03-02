package com.bigfans.pricingservice.processor;

import com.bigfans.framework.utils.ArithUtils;
import com.bigfans.pricingservice.model.Activity;
import com.bigfans.pricingservice.model.Promotion;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Description: 
 * @author lichong
 * @date Apr 21, 2016 4:35:23 AM 
 *
 */
@Data
public class PricingItem {

	private String prodId;
	private String category;
	private BigDecimal originalPrice;
	private BigDecimal originalSubTotal;
	private BigDecimal price;
	private BigDecimal subTotal;
	private Integer quantity;

	private List<Promotion> promotions = new ArrayList<>();
	private List<Activity> activities = new ArrayList<>();

	public BigDecimal getSubTotal(){
		return subTotal == null ? getOriginalSubTotal() : subTotal;
	}
	
	public BigDecimal getOriginalSubTotal() {
		if(originalSubTotal == null){
			originalSubTotal = ArithUtils.mul(originalPrice , quantity);
		}
		return originalSubTotal;
	}
}
