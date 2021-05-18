package com.hs.s1.board.notice;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hs.s1.board.BoardVO;
import com.hs.s1.member.MemberVO;
import com.hs.s1.util.Pager;

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
	public ModelAndView getList(Pager pager) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<BoardVO> ar = noticeService.getList(pager);
		mv.addObject("pager", pager);
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
	
	@GetMapping("insert")
	public ModelAndView setInsert() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("VO", new BoardVO());
		mv.addObject("act", "insert");
		// result 코드
//		mv.addObject("message", "Not Administrator");
//		mv.addObject("path", "./list");
//		Object obj = session.getAttribute("member");
//		MemberVO memberVO = null;
//		String path = "redirect:/member/login";
//		//if(obj != null) {}
//		if(obj instanceof MemberVO) {
//			memberVO = (MemberVO)obj;
//			
//			if(memberVO.getUsername().equals("admin")) {
//				path="board/form";
//			}
//		}
		
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
		int result = noticeService.setInsert(boardVO, files);
		mv.setViewName("redirect:./list");
		return mv;
	}
	
	@GetMapping("update")
	public ModelAndView setUpdate(BoardVO boardVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		boardVO = noticeService.getSelect(boardVO);
		mv.addObject("VO", boardVO);
		mv.addObject("act", "update");
		mv.setViewName("board/form");
		return mv;
	}
	
	@PostMapping("update")
	public ModelAndView setUpdate(BoardVO boardVO, ModelAndView mv) throws Exception {
		int result = noticeService.setUpdate(boardVO);
		mv.setViewName("redirect:./list");
		return mv;
	}	
	
	@GetMapping("delete")
	public ModelAndView setDelete(BoardVO boardVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		int result = noticeService.setDelete(boardVO);
		mv.setViewName("redirect:./list");
		return mv;
	}
//	-----------------------------------------------------------------
//	fileDown
	@GetMapping("fileDown")
	public ModelAndView fileDown(String fileName, String ogName) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("fileName", fileName);
		mv.addObject("ogName", ogName);
		mv.addObject("filePath", "/upload/notice/");
		// view의 이름은 Bean의 이름과 일치
		mv.setViewName("fileDown");
		return mv;
	}
}
