package net.test.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionAdvice {
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	/*@ExceptionHandler(Exception.class)
	public String common(Exception e) {
		logger.info(e.toString());
		return "error_common";
	}*/
	
	@ExceptionHandler(Exception.class)
	private ModelAndView errorModelAndView(Exception ex) {
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("/error_common");
		modelandview.addObject("exception", ex);
		
		return modelandview;
	}

}
