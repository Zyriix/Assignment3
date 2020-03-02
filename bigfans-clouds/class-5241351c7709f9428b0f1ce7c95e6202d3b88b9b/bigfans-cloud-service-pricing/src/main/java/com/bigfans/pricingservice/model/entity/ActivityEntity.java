package com.bigfans.pricingservice.model.entity;

import com.bigfans.framework.model.AbstractModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * 
 * @Description:活动
 * @author lichong 2015年6月6日上午11:29:21
 *
 */
@Data
@Table(name = "Activity")
public class ActivityEntity extends AbstractModel {

	private static final long serialVersionUID = -6112016318532673405L;

	public String getModule() {
		return "Activity";
	}

	@Column(name = "name")
	protected String name;
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
