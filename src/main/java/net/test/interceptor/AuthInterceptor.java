package net.test.interceptor;

import org.slf4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthInterceptor.class);

}
