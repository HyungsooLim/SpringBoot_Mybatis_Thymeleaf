package com.hs.s1.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hs.s1.board.BoardFileVO;
import com.hs.s1.board.BoardVO;
import com.hs.s1.util.Pager;

@Controller
@RequestMapping("/qna/**")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard() throws Exception {
		return "qna";
	}

	@GetMapping("list")
	public ModelAndView getList(Pager pager) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<BoardVO> ar = qnaService.getList(pager);
		mv.addObject("pager", pager);
		mv.addObject("list", ar);
		mv.setViewName("board/list");
		return mv;
	}
	
	@GetMapping("select")
	public ModelAndView getSelect(BoardVO boardVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		boardVO = qnaService.getSelect(boardVO);
		mv.addObject("VO", boardVO);
		mv.setViewName("board/select");
		return mv;
	}
	
	@GetMapping("insert")
	public ModelAndView setInsert() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("VO", new BoardVO());
		mv.addObject("act", "insert");
		mv.setViewName("board/form");
		return mv;
	}
	
	@PostMapping("insert")
	public ModelAndView setInsert(BoardVO boardVO, MultipartFile[] files) throws Exception {
		// 찍어보면 실제 배열+1 길이
		// 빈거 하나 추가되어있음
//		System.out.println(files.length);
//		for(MultipartFile file:files) {
//			System.out.println(file.getOriginalFilename());
//		}
		ModelAndView mv = new ModelAndView();
		int result = qnaService.setInsert(boardVO, files);
		mv.setViewName("redirect:./list");
		return mv;
	}
	
	@GetMapping("update")
	public ModelAndView setUpdate(BoardVO boardVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		boardVO = qnaService.getSelect(boardVO);
		mv.addObject("VO", boardVO);
		mv.addObject("act", "update");
		mv.setViewName("board/form");
		return mv;
	}
	
	@PostMapping("update")
	public ModelAndView setUpdate(BoardVO boardVO, ModelAndView mv) throws Exception {
		int result = qnaService.setUpdate(boardVO);
		mv.setViewName("redirect:./list");
		return mv;
	}	
	
	@GetMapping("delete")
	public ModelAndView setDelete(BoardVO boardVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		int result = qnaService.setDelete(boardVO);
		mv.setViewName("redirect:./list");
		return mv;
	}
	
	@GetMapping("reply")
	public ModelAndView setReplyInsert(BoardVO boardVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("VO", boardVO);
		mv.addObject("act", "reply");
		mv.setViewName("board/form");
		return mv;
	}
	
	@PostMapping("reply")
	public ModelAndView setReplyInsert(BoardVO boardVO, MultipartFile[] files) throws Exception {
		ModelAndView mv = new ModelAndView();
		int result = qnaService.setReplyInsert(boardVO, files);
		mv.setViewName("redirect:./list");
		return mv;
	}
}
