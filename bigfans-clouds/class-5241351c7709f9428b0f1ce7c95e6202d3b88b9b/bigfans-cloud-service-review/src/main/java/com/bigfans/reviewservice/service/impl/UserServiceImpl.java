package com.bigfans.reviewservice.service.impl;

import com.bigfans.framework.dao.BaseServiceImpl;
import com.bigfans.reviewservice.dao.UserDAO;
import com.bigfans.reviewservice.model.User;
import com.bigfans.reviewservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务类
 * @author lichong
 *
 */
@Service(UserServiceImpl.BEAN_NAME)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	
	public static final String BEAN_NAME = "userService";
	
	private UserDAO userDAO;

	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
		super(userDAO);
		this.userDAO = userDAO;
	}

	@Override
	public boolean accountExist(String account) throws Exception {
		return userDAO.countByAccount(account) != 0;
	}
}
