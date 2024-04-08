package com.topas.air.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("######## preHandle");

	    long startTime = System.currentTimeMillis();

	    request.setAttribute("startTime", startTime);

	    log.info("[START]  [" + request.getMethod() + "] [ URL is: " + request.getRequestURL().toString()
	            + " Body is: {}]");




		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("######## afterCompletion");


	    long startTime = (long) request.getAttribute("startTime");
	    request.removeAttribute("startTime");
	    long endTime = System.currentTimeMillis();
	    log.info("[END]  [" + request.getMethod() + "]   [ URL is:" + request.getRequestURL().toString()
	            + "]  [Execution Time: {} miliseconds]", (endTime - startTime));
	}
}
