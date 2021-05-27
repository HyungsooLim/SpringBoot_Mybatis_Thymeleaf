package com.hs.s1.member;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/**")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("error")
	public String error() {
		return "error/error";
	}

	@GetMapping("join")
	public String setJoin(@ModelAttribute MemberVO memberVO) throws Exception {
		return "member/memberJoin";
	}
	
	@PostMapping("join")
	public ModelAndView setJoin(@Valid MemberVO memberVO, Errors errors, MultipartFile file) throws Exception {
		ModelAndView mv = new ModelAndView();
		int result = memberService.setJoin(memberVO, file);
		mv.setViewName("redirect:/");
		
//		if(errors.hasErrors()) {
//			mv.setViewName("member/memberJoin");
//		}
		
		if(memberService.memberError(memberVO, errors)) {
			mv.setViewName("member/memberJoin");
		}
		
		return mv;
	}
	
	@GetMapping("login")
	public ModelAndView getLogin() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/memberLogin");
		return mv;
	}
	
	@GetMapping("memberLoginResult")
	public String memberLoginResult(HttpSession session, Authentication auth2) throws Exception {
		
		//session의 속성명들 꺼내오기
		Enumeration<String> en = session.getAttributeNames();
		while(en.hasMoreElements()) {
			System.out.println("Attribute Name : "+en.nextElement());
		}
		
		//로그인 시 session의 속성명 = SPRING_SECURITY_CONTEXT
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		System.out.println("obj : "+obj);
		SecurityContextImpl impl = (SecurityContextImpl)obj;
		Authentication auth = impl.getAuthentication();
		System.out.println("=============================================");
		System.out.println("Name : "+auth.getName());
		System.out.println("Detail : "+auth.getDetails());
		System.out.println("Principal : "+auth.getPrincipal());
		System.out.println("Authorities : "+auth.getAuthorities());
		System.out.println("=============================================");
		
		System.out.println("=============================================");
		System.out.println("Name : "+auth2.getName());
		System.out.println("Detail : "+auth2.getDetails());
		System.out.println("Principal : "+auth2.getPrincipal());
		System.out.println("Authorities : "+auth2.getAuthorities());
		System.out.println("=============================================");
		System.out.println("===== Login 성공 =====");
		return "redirect:/";
	}
	
	@GetMapping("memberLoginFail")
	public String memberLoginFail() throws Exception {
		System.out.println("===== Login Fail =====");
		return "redirect:/member/login";
	}
	
//	@PostMapping("login")
//	public ModelAndView getLogin(HttpSession session, MemberVO memberVO) throws Exception {
//		ModelAndView mv = new ModelAndView();
//		memberVO = memberService.getLogin(memberVO);
//		session.setAttribute("member", memberVO);
//		mv.setViewName("redirect:/");
//		return mv;
//	}
	
	@GetMapping("logout")
	public ModelAndView getLogout(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		session.invalidate();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@GetMapping("page")
	public ModelAndView getMemberPage() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/memberPage");
		return mv;
	}
}
