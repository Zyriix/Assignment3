package com.bigfans.pricingservice.model;

import com.bigfans.framework.utils.DateUtils;
import com.bigfans.pricingservice.model.entity.CouponEntity;
import lombok.Data;

import java.util.Date;

/**
 * 优惠劵
 * 
 * @author lichong
 *
 */
@Data
public class Coupon extends CouponEntity {

	private static final long serialVersionUID = 245734476246885480L;
	
	public boolean isExpired() {
		return DateUtils.compare(this.endTime, new Date()) >= 0;
	}

}
