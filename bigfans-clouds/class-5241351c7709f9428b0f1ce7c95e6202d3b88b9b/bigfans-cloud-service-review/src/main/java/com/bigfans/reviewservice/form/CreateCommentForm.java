package com.bigfans.reviewservice.form;

import lombok.Data;

/**
 * 
 * @Description: 评论表单
 * @author lichong
 * @date Mar 17, 2016 2:21:17 AM
 *
 */
@Data
public class CreateCommentForm {

	private String orderId;
	private String orderItemId;
	private String productId;
	private Integer rate;
	private String content;
	private Boolean isAnonymous;

}
