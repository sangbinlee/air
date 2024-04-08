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
		String size = request.getParameter("size");
		if (page == null) {
			page = "1";
		}
		if (size == null) {
			size = "10";// pageSize
		}

		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(size));
		log.info("######## preHandle[PageInterceptor] page={}, size={}", page, size);

//		return true;
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("######## afterCompletion[PageInterceptor]");
	}
}
