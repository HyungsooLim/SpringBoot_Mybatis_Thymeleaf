package com.hs.s1.board.qna;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/qna/**")
public class QnaController {
	
	@ModelAttribute("board")
	public String getBoard() throws Exception {
		return "qna";
	}

	@GetMapping("list")
	public ModelAndView getList() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/list");
		return mv;
	}
}
