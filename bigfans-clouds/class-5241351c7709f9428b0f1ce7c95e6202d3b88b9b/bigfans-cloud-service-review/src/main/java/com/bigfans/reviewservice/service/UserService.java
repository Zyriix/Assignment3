package com.bigfans.reviewservice.service;

import com.bigfans.framework.dao.BaseService;
import com.bigfans.reviewservice.model.User;

public interface UserService extends BaseService<User> {

    /**
     * 判断当前用户名是否存在
     * @param account
     * @return
     * @throws Exception
     */
    boolean accountExist(String account) throws Exception;

}
