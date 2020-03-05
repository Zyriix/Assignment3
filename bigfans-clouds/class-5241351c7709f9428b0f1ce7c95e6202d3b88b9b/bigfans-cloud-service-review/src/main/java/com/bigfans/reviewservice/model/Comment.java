package com.bigfans.reviewservice.model;

import com.bigfans.framework.utils.DateUtils;
import com.bigfans.reviewservice.model.entity.CommentEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Description:评论
 * @author lichong 2014年12月7日下午2:37:59
 *
 */
@Data
public class Comment extends CommentEntity {

	private static final long serialVersionUID = 7386643526071415103L;
	
	private List<Comment> replyList = new ArrayList<>();

	public String getCreateDateStr(){
		return DateUtils.toStringWithFormat(createDate, "yyyy-MM-dd HH:mm:ss");
	}

}
