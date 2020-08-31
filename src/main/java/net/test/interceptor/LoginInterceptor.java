package net.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final String LOGIN = "login";
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoginInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		
		// 기존 HttpSession에 남아있는 정보가 있는 경우에 정보를 삭제
		if (session.getAttribute(LOGIN) != null) {
			logger.info("clear login data before..........");
			session.removeAttribute(LOGIN);
		}
		
		return true;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		// UserController에서 'userVO'라는 이름으로 객체를 담아둔 상태이므로, 이 상태를 체크해 HttpSession에 저장
		
		HttpSession session = request.getSession();
		
		ModelMap modelMap = modelAndView.getModelMap();
		Object userVO = modelMap.get("userVO");
		
		if (userVO != null) {
			logger.info("new login success......");
			session.setAttribute(LOGIN, userVO);
			response.sendRedirect("/crud");
		}
		
		
		
	}


}