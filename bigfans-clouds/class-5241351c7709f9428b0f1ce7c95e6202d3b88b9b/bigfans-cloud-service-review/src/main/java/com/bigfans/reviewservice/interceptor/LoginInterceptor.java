package com.bigfans.reviewservice.interceptor;

import com.bigfans.Constants;
import com.bigfans.framework.Applications;
import com.bigfans.framework.CurrentUser;
import com.bigfans.framework.SessionUserFactory;
import com.bigfans.framework.annotations.NeedLogin;
import com.bigfans.framework.exception.UserNotLoginException;
import com.bigfans.framework.utils.JsonUtils;
import com.bigfans.framework.utils.JwtUtils;
import com.bigfans.framework.web.RequestHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lichong
 * @version V1.0
 * @Title:
 * @Description: 登录拦截器, 如果被请求的方法上有@NeedLogin注解,那么就会做登录验证
 * @date 2016年1月27日 上午8:48:52
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        CurrentUser currentUser = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            currentUser = this.createTempUser(request, response);
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Constants.TOKEN.KEY_NAME)) {
                    String token = cookie.getValue();
                    currentUser = SessionUserFactory.getCurrentUser(token, "bigfans");
                }
            }
        }
        // 如果是第一次访问，创建一个临时凭证
        if (currentUser == null) {
            // 创建临时的token作为用户唯一凭证
            currentUser = this.createTempUser(request, response);
        }
        Applications.setCurrentUser(currentUser);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        NeedLogin loginAnno = handlerMethod.getMethodAnnotation(NeedLogin.class);
        // 当前请求不需要登录
        if (loginAnno == null) {
            return true;
        }
        // 如果当前用户已经登录
        if (currentUser.isLoggedIn()) {
            return this.checkAccess(loginAnno, currentUser);
        } else {
            throw new UserNotLoginException();
        }
    }

    protected CurrentUser createTempUser(HttpServletRequest request, HttpServletResponse response) {
        // 创建临时的token作为用户唯一凭证
        Map<String , Object> claims = new HashMap<>();
        claims.put("account" , "temp");
        claims.put("nickname" , "tempUser");
        claims.put("ip" , RequestHolder.getRemoteIP());
        claims.put("loginTime" , new Date());
        claims.put("uid" , request.getSession().getId());
        claims.put("loggedIn" , false);
        CurrentUser currentUser = SessionUserFactory.createSessionUser(claims);

        Applications.setCurrentUser(currentUser);
        String tempToken = JwtUtils.create(JsonUtils.toJsonString(currentUser), "bigfans");
        Cookie cookie = new Cookie(Constants.TOKEN.KEY_NAME, tempToken);
        response.addCookie(cookie);
        return currentUser;
    }

    protected boolean checkAccess(NeedLogin loginAnno, CurrentUser currentUser) {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        Applications.clearCurrentUser();
    }

}
