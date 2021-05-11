package com.hs.s1.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hs.s1.board.BoardVO;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("board")
	public String getBoard() throws Exception {
		return "notice";
	}

	@GetMapping("list")
	public ModelAndView getList() throws Exception {
		ModelAndView mv = new ModelAndView();
		List<BoardVO> ar = noticeService.getList();
		mv.addObject("list", ar);
		mv.setViewName("board/list");
		return mv;
	}
	
	@GetMapping("select")
	public ModelAndView getSelect(BoardVO boardVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		boardVO = noticeService.getSelect(boardVO);
		mv.addObject("VO", boardVO);
		mv.setViewName("board/select");
		return mv;
	}
}
