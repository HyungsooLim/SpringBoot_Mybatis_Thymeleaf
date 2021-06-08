package com.hs.s1.exception;

import java.sql.SQLException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView getNull(ModelAndView mv) {
		mv.addObject("message", "NullPointer Occurred");
		mv.setViewName("error/500");
		return mv;
	}
	
	@ExceptionHandler(SQLException.class)
	public ModelAndView getSql(ModelAndView mv) {
		mv.addObject("message", "SQL exception Occurred");
		mv.setViewName("error/500");
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView getException(ModelAndView mv) {
		mv.addObject("message", "Exception Occurred");
		mv.setViewName("error/500");
		return mv;
	}
	
	@ExceptionHandler(Throwable.class)
	public ModelAndView getAll(ModelAndView mv) {
		mv.addObject("message", "관리자에게 문의");
		mv.setViewName("error/500");
		return mv;
	}
	
	@ExceptionHandler(MyException.class)
	public String getMyException(Model model, Exception exception) {
		model.addAttribute("message", exception.getMessage());
		return "error/500";
	}

}
