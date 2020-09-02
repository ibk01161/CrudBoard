package net.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.test.service.UserService;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Autowired
	private UserService service;
	
	
	// 현재 사용자가 로그인한 상태인지를 체크하고, 컨트롤러를 호출하게 할 것인지 결정, 
	// 사용자가 로그인하지 않은 상태라면 로그인화면으로 이동
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("login") == null) {
			logger.info("current user is not login...........");
			
			saveDest(request);
			
			response.sendRedirect("/crud/user/login");
			return false;
			
		}
		
		return true;
		
	}	
	
	// URI 저장 메소드
	private void saveDest(HttpServletRequest req) {
		
		String uri = req.getRequestURI();
		String query = req.getQueryString();
		
		// query가 null일경우와 아닐경우 
		// ex : sboard/list?bno=13
		
		logger.info("uri : " + uri);
		logger.info("query : " + query);
		
		if (query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?" + query;
		}
		
		// Get Mapping 일때
		if (req.getMethod().equals("GET")) {
			logger.info("getMethod : " + req.getMethod());
			logger.info("dest : " + (uri + query));
			req.getSession().setAttribute("dest", uri+query);
		}
		
	}

}
