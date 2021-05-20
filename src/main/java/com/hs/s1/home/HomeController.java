package com.hs.s1.home;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hs.s1.board.BoardVO;

/**
 * ===================================
 * 			Thymeleaf Project
 * ===================================
 */

@Controller
public class HomeController {
	@GetMapping("/")
	public String home(Model model, HttpSession session) throws Exception {
		//model.addAttribute("message", "Thymeleaf Project");
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(1L);
		boardVO.setTitle("title1");
		boardVO.setWriter("writer1");
		
		model.addAttribute("user", "HS");
		model.addAttribute("msg", "ㅋㅋㅋ");
		
		return "index";
	}
}
