package net.test.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {		
		return "home";
	}
	
	@GetMapping("/test")
	public void ajaxTest() {
		
	}
	
	@GetMapping("/doA")
	public String doA(Locale locale, Model model) {
		
		System.out.println("doA.....................");
		
		return "interceptor_test";
	}
	
	@GetMapping("/doB")
	public String doB(Locale locale, Model model) {
		
		System.out.println("doB.....................");
		
		model.addAttribute("result","DDB RESULT");
		
		return "interceptor_test";
	}
	
	
}
