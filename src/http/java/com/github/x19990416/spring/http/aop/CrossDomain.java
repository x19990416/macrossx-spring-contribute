package com.github.x19990416.spring.http.aop;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 支持js跨域
 * @author starseeker.limin@gmail.com(X-Forever)
 *
 */
public class CrossDomain {
	@Autowired
	private HttpServletResponse response;
	@Value(value="#{applicationProperties['macrossx.http.aop.crossdomain.allow.origin']}")
	private String allowOrigin;
	public Object arround(ProceedingJoinPoint pjp) throws Throwable {
		// 调用核心逻辑
		if (response != null) {
			response.setHeader("Access-Control-Allow-Origin", allowOrigin);
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		}
		return pjp.proceed();
	}
}