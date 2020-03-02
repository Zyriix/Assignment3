package com.bigfans.reviewservice.dao.impl;

import com.bigfans.framework.dao.MybatisDAOImpl;
import com.bigfans.framework.dao.ParameterMap;
import com.bigfans.reviewservice.dao.UserDAO;
import com.bigfans.reviewservice.model.User;
import org.springframework.stereotype.Repository;

@Repository(UserDAOImpl.BEAN_NAME)
public class UserDAOImpl extends MybatisDAOImpl<User> implements UserDAO {

	public static final String BEAN_NAME = "userDAO";

	@Override
	public int countByAccount(String account){
		ParameterMap params = new ParameterMap();
		params.put("account", account);
		return getSqlSession().selectOne(className + ".count", params);
	}
}