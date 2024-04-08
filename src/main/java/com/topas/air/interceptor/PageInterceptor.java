package com.topas.air.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import com.github.pagehelper.PageHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("######## preHandle[PageInterceptor]");

		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}

		int pageSize = 10;
		PageHelper.startPage(Integer.parseInt(page), pageSize);

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("######## afterCompletion[PageInterceptor]");
	}
}
