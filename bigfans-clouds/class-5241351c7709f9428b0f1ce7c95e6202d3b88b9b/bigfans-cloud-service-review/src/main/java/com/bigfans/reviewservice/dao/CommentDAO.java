package com.bigfans.reviewservice.dao;

import com.bigfans.framework.dao.BaseDAO;
import com.bigfans.reviewservice.model.Comment;
import com.bigfans.reviewservice.model.CommentScore;

import java.util.List;
import java.util.Map;

/**
 * 
 * @Description:评论
 * @author lichong
 * 2014年12月7日下午3:59:29
 *
 */
public interface CommentDAO extends BaseDAO<Comment> {
	
	String SCORE_HP = "hpCount";
	String SCORE_HP_PERCENT = "hpPercent";
	String SCORE_ZP = "zpCount";
	String SCORE_CP = "cpCount";
	String SCORE_ALL = "allCount";
	String SCORE_AVG = "avgRate";

	List<Comment> listByProduct(String prodId, Long start, Long pagesize);

	List<Comment> listByPg(String pgId, Long start, Long pagesize);
	
	CommentScore getScore(String prodId);
	
	Integer count(String pgId, String prodId);
}
