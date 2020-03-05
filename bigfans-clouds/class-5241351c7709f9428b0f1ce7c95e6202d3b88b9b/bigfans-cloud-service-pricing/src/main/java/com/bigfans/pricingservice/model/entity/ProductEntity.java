package com.bigfans.pricingservice.model.entity;

import com.bigfans.framework.model.AbstractModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 
 * @Description:具体的具有规格的商品
 * @author lichong 
 * 2015年5月29日上午9:03:25
 *
 */
@Data
@Table(name="Product")
public class ProductEntity extends AbstractModel {

	private static final long serialVersionUID = -526970375481780870L;
	
	public String getModule() {
		return "Product";
	}

	// 货品ID
	@Column(name="pg_id")
	protected String pgId;
	@Column(name="brand_id")
	protected String brandId;
	@Column(name="category_id")
	protected String categoryId;
	// 商品号
	@Column(name="sn")
	protected String sn;
	// 商品名称
	@Column(name="name")
	protected String name;
	// 进货价格
	@Column(name="purchase_price")
	protected BigDecimal purchasePrice;
	// 当前价格
	@Column(name="price")
	protected BigDecimal price;
	// 商品重量
	@Column(name="weight")
	protected BigDecimal weight;

}
