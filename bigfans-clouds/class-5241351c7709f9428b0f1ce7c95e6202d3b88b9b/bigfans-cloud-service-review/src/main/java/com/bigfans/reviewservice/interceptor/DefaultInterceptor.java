package com.bigfans.reviewservice.interceptor;

import com.bigfans.framework.model.PageContext;
import com.bigfans.framework.web.CookieHolder;
import com.bigfans.framework.web.RequestHolder;
import com.bigfans.framework.web.ResponseHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 默认拦截器,初始化参数
 * @author lichong
 *
 */
public class DefaultInterceptor implements HandlerInterceptor {
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		RequestHolder.setHttpServletRequest(request);
		ResponseHolder.setHttpServletResponse(response);
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				CookieHolder.put(cookie.getName(), cookie);
			}
		}
		
//		String currentPage = request.getParameter(Constant.REQ_CUR_PAGE);
//		String pagesize = request.getParameter(Constant.REQ_PAGESIZE);
//		// valueOf可以利用缓存,parseLong不会利用缓存
//		Long cp = currentPage == null ? 1 : Long.valueOf(currentPage);
//		pagesize = pagesize == null ? Configuration.getProp(PropKeys.SEARCH.defaultPageSize) : pagesize;
//		PageContext.setCurrentPage(cp);
//		PageContext.setPageSize(Long.valueOf(pagesize));
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		RequestHolder.clear();
		ResponseHolder.clear();
		CookieHolder.clear();
		PageContext.clear();
	}

}
