package com.bigfans.pricingservice.model;

import com.bigfans.pricingservice.model.entity.ProductPromotionEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @Description:促销
 * @author lichong 2015年6月6日下午8:59:15
 *
 */
@Data
public class ProductPromotion extends ProductPromotionEntity implements PmtActivity {

	private static final long serialVersionUID = -8986026952151935006L;
	
	protected String name;
	protected String type;
	protected Integer discount;
	protected BigDecimal deductedPrice;
	protected BigDecimal fixedPrice;
	protected String code;
	protected Date starTime;
	protected Date endTime;
	protected String condition;
	protected String action;
	protected Integer limitCount;
	protected Boolean limitBuy;
	
	private boolean accepted;
	private String message;

}
