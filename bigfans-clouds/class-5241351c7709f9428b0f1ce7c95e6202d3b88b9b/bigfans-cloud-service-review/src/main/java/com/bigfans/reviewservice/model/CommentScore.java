package com.bigfans.reviewservice.model;

import lombok.Data;

/**
 * 
 * @Title:
 * @Description: 封装商品评分
 * @author lichong
 * @date 2016年3月18日 上午9:50:09
 * @version V1.0
 */
@Data
public class CommentScore {

	// 好评数
	private Integer hpCount;
	// 中评数
	private Integer zpCount;
	// 差评数
	private Integer cpCount;
	//评价总数
	private Integer allCount;
	// 平均分
	private Float avgRate;
	// 好评率
	private Float hpPercent;

	@Override
	public String toString() {
		return "CommentScore [hpCount=" + hpCount + ", zpCount=" + zpCount + ", cpCount=" + cpCount + ", allCount="
				+ allCount + ", avgRate=" + avgRate + ", hpPercent=" + hpPercent + "]";
	}

}
