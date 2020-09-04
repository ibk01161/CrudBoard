package net.test.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import net.test.domain.UserVO;
import net.test.dto.LoginDTO;
import net.test.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private UserService service;
	
	@GetMapping("/login")
	public void loginGet(@ModelAttribute("dto") LoginDTO dto) {
		
		logger.info("loginGet......................");
		
	}
	
	@PostMapping("/loginPost")
	public void loginPost(LoginDTO dto, HttpSession session, Model model) throws Exception {
		
		logger.info("loginPost......................");
		
		UserVO vo = service.login(dto);
		
		if (vo == null) {
			return;
		}
		
		model.addAttribute("userVO", vo);
		
		// 자동로그인 
		if (dto.isUseCookie()) {
			int amount = 60 * 60 * 24 * 7;
			
			Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
			service.keepLogin(vo.getUid(), session.getId(), sessionLimit);
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		logger.info("logout......................1");
		
		Object obj = session.getAttribute("login");
		
		// login 이라는 이름의 세션이 있으면 정보 삭제
		if(obj != null) {
			UserVO vo = (UserVO) obj;
			logger.info("logout......................2");
			session.removeAttribute("login");
			session.invalidate();
			
			logger.info("logout......................3");
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			// loginCookie 라는 이름의 쿠키가 있으면 쿠키 삭제(유효기간0으로 만들기), 데이터베이스 갱신
			if(loginCookie != null) {
				logger.info("logout..................4");
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				service.keepLogin(vo.getUid(), session.getId(), new Date());
				
			}
		}
		
		return "user/logout";
		
	}

}
