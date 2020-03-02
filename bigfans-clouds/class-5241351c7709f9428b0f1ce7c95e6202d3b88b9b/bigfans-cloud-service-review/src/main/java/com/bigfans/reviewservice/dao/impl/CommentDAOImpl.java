package com.bigfans.reviewservice.dao.impl;

import com.bigfans.framework.dao.MybatisDAOImpl;
import com.bigfans.framework.dao.ParameterMap;
import com.bigfans.reviewservice.dao.CommentDAO;
import com.bigfans.reviewservice.model.Comment;
import com.bigfans.reviewservice.model.CommentScore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @Description:评论
 * @author lichong 2014年12月7日下午3:59:29
 *
 */
@Repository(CommentDAOImpl.BEAN_NAME)
public class CommentDAOImpl extends MybatisDAOImpl<Comment> implements CommentDAO {

	public static final String BEAN_NAME = "commentDAO";

	@Override
	public List<Comment> listByProduct(String prodId, Long start, Long pagesize) {
		ParameterMap params = new ParameterMap();
		params.put("prodId", prodId);
		params.put("pageable", true);
		params.put("start", start);
		params.put("pagesize", pagesize);
		return getSqlSession().selectList(className + ".list", params);
	}
	
	@Override
	public List<Comment> listByPg(String pgId, Long start, Long pagesize) {
		ParameterMap params = new ParameterMap();
		params.put("pgId", pgId);
		params.put("pageable", true);
		params.put("start", start);
		params.put("pagesize", pagesize);
		return getSqlSession().selectList(className + ".list", params);
	}

	@Override
	public CommentScore getScore(String pgId) {
		ParameterMap params = new ParameterMap();
		params.put("pgId", pgId);
		return getSqlSession().selectOne(className + ".getScore", params);
	}

	@Override
	public Integer count(String pgId , String prodId) {
		ParameterMap params = new ParameterMap();
		params.put("pgId", pgId);
		params.put("prodId", prodId);
		return getSqlSession().selectOne(className + ".count", params);
	}
}
