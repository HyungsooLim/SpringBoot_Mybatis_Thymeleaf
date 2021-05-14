package com.hs.s1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/**")
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@GetMapping("join")
	public String setJoin() throws Exception {
		return "member/memberJoin";
	}
	
	@PostMapping("join")
	public ModelAndView setInsert(MemberVO memberVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		int result = memberService.setInsert(memberVO);
		mv.setViewName("member/memberJoin");
		return mv;
	}
	
	@GetMapping("login")
	public String getLogin() throws Exception {
		return "member/memberLogin";
	}
}
