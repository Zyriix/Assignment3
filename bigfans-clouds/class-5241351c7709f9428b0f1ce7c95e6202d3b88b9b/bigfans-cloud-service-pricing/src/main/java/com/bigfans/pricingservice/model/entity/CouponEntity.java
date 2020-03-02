package com.bigfans.pricingservice.model.entity;

import com.bigfans.framework.model.AbstractModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠劵
 * 
 * @author lichong
 *
 */
@Data
@Table(name="Coupon")
public class CouponEntity extends AbstractModel {

	private static final long serialVersionUID = -3627944168742861778L;
	
	// 满减
	public static final String STRATEGY_ER = "O_ER"; // ER(enough reduce)
	// 满免运费
	public static final String STRATEGY_EFF = "O_EFF"; // EFD(enough free freight)

	@Column(name = "name")
	protected String name;
	@Column(name = "type")
	protected String type;
	@Column(name = "category_id")
	protected String categoryId;
	@Column(name = "start_time", columnDefinition = "DATETIME", nullable = false)
	protected Date startTime;
	@Column(name = "end_time", columnDefinition = "DATETIME", nullable = false)
	protected Date endTime;
	@Column(name="condition")
	protected BigDecimal condition;
	@Column(name="money")
	protected Integer money;
	@Column(name="amount")
	protected Integer amount;
	@Column(name="distributing_type")
	protected String distributingType;
	@Column(name="term_type")
	protected String termType;
	@Column(name="effective_time", columnDefinition = "DATETIME")
	protected Date effectiveTime;
	@Column(name="expiry_time", columnDefinition = "DATETIME")
	protected Date expiryTime;
	@Column(name="term_days")
	protected Integer termDays;
	/*没人最多可获取的数量,空代表不限量*/
	@Column(name="limit_amount")
	protected Integer limitAmount;

	public String getModule() {
		return "Coupon";
	}

}
