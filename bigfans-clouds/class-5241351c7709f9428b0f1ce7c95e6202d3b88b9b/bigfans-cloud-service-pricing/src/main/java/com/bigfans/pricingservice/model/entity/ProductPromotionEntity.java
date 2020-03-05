package com.bigfans.pricingservice.model.entity;

import com.bigfans.framework.model.AbstractModel;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 
 * @Description:商品促销
 * @author lichong 2015年6月6日上午11:29:21
 *
 */
@Table(name = "Product_Promotion")
public class ProductPromotionEntity extends AbstractModel {

	private static final long serialVersionUID = -6112016318532673405L;

	public String getModule() {
		return "ProductPromotion";
	}

	@Column(name = "product_id")
	protected String productId;
	@Column(name = "promotion_id")
	protected String promotionId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

}
