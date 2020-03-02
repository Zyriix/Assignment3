package com.bigfans.pricingservice.model.entity;

import com.bigfans.framework.model.AbstractModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @Description:促销
 * @author lichong 2015年6月6日上午11:29:21
 *
 */
@Data
@Table(name = "Promotion")
public class PromotionEntity extends AbstractModel {

	private static final long serialVersionUID = -6112016318532673405L;

	public String getModule() {
		return "Promotion";
	}

	public static final String SCOPE_PRODUCT = "P";
	public static final String SCOPE_ORDER = "O";

	/* discount 打折优惠 */
	public static final String TYPE_P_DIS = "P_DIS";
	/* enough reduce 满额减 */
	public static final String TYPE_P_ER = "P_ER";
	/* enough amount reduce 满件减 */
	public static final String TYPE_P_EAR = "P_EAR";
	/* deducted price 减价优惠 */
	public static final String TYPE_P_DP = "P_DP";
	/* fixed price 固定价格 */
	public static final String TYPE_P_FP = "P_FP";

	@Column(name = "name")
	protected String name;
	// product or order
	@Column(name = "scope")
	protected String scope;
	@Column(name = "type")
	protected String type;
	@Column(name = "code")
	protected String code;
	@Column(name = "start_time")
	protected Date startTime;
	@Column(name = "end_time")
	protected Date endTime;
	@Column(name = "condition")
	protected String condition;
	@Column(name = "action")
	protected String action;
	@Column(name = "limit_count")
	protected Integer limitCount;
	@Column(name = "limit_buy")
	protected Boolean limitBuy;

}
