package com.bigfans.reviewservice.service;

import com.bigfans.framework.dao.BaseService;
import com.bigfans.framework.model.PageBean;
import com.bigfans.reviewservice.model.Comment;
import com.bigfans.reviewservice.model.CommentScore;

/**
 * 
 * @Description:商品评价服务接口
 * @author lichong 2014年12月27日上午9:15:29
 *
 */
public interface CommentService extends BaseService<Comment> {

	PageBean<Comment> pageByProduct(String prodId, Long start, Long pagesize) throws Exception;
	
	PageBean<Comment> pageByPg(String pgId, Long start, Long pagesize) throws Exception;

	CommentScore getScore(String prodId) throws Exception;

	Integer countByPg(String pgId) throws Exception;

	Integer countByProd(String pgId, String prodId) throws Exception;
	
	String getFormatedCountByPg(String pgId) throws Exception;
}
