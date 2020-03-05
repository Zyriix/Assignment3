package com.bigfans.reviewservice.dao;

import com.bigfans.framework.dao.BaseDAO;
import com.bigfans.reviewservice.model.User;

public interface UserDAO extends BaseDAO<User> {

	int countByAccount(String account);

}