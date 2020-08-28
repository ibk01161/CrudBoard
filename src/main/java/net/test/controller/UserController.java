package net.test.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.test.domain.UserVO;
import net.test.dto.LoginDTO;
import net.test.service.UserService;

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
		
		model.addAttribute("UserVO", vo);
	}

}
