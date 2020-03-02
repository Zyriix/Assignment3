package com.bigfans.reviewservice.model.entity;

import com.bigfans.framework.model.AbstractModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 
 * @Description:评论
 * @author lichong 2014年12月7日下午2:37:59
 *
 */
@Data
@Table(name="Comment")
public class CommentEntity extends AbstractModel {

	private static final long serialVersionUID = -9206256356431402751L;

	public static final int LEVEL_HP = 2;
	public static final int LEVEL_ZP = 1;
	public static final int LEVEL_CP = 0;
	public static final String LEVEL_HP_KEY = "hp";
	public static final String LEVEL_ZP_KEY = "zp";
	public static final String LEVEL_CP_KEY = "cp";

	@Column(name = "pg_id")
	protected String pgId;
	@Column(name = "prod_id")
	protected String prodId;
	@Column(name = "order_id")
	protected String orderId;
	@Column(name = "order_item_id")
	protected String orderItemId;
	@Column(name = "user_id")
	protected String userId;
	@Column(name = "user_nickname")
	protected String userNickname;
	@Column(name = "rate")
	protected Integer rate; // 评分
	@Column(name = "content")
	protected String content;
	@Column(name = "reply_id")
	protected String replyId;
	@Column(name = "is_anonymous")
	protected Boolean isAnonymous;

	public String getModule() {
		return "Comment";
	}

}
