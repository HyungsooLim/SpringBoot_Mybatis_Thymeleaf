package com.hs.s1.interceptor;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hs.s1.board.BoardVO;
import com.hs.s1.member.MemberVO;

@Component
public class WriterInterceptor implements HandlerInterceptor {
	
	// Controller 종료 후
	// 작성자를 출력
	// 로그인 유저네임 출력
	
	//WriterInterceptorConfig
	// /qna/update
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
		
		// 0. method 형식
		String method = request.getMethod();
		
		if(method.equals("GET")) {
			this.updateCheck(request, modelAndView);
		}
		
	}
	
	private void updateCheck(HttpServletRequest request, ModelAndView modelAndView) {
		System.out.println("=== postHandle start ===");
		String message = "";
		String path = "";
		// 1. 로그인 유저
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		if(memberVO != null) {			
			System.out.println("username : "+memberVO.getUsername());
		// 2. 작성자
			BoardVO boardVO = (BoardVO)modelAndView.getModel().get("VO");
			System.out.println("writer : "+boardVO.getWriter());
			
		// 3. 유저와 작성자가 일치하지 않으면
		// common/result 로 이동
		// 메세지 띄우고 list로 이동
			if(!memberVO.getUsername().equals(boardVO.getWriter())) {
				message = "작성자가 아닙니다";
				path = "./list";
				modelAndView.setViewName("common/result");
				modelAndView.addObject("message", message);
				modelAndView.addObject("path", path);
			}
		} else {
			message = "로그인이 필요합니다";
			path = "../member/login";
			modelAndView.setViewName("common/result");
			modelAndView.addObject("message", message);
			modelAndView.addObject("path", path);
		}
		
		// 4. 로그인 하지 않았으면
		// common/result 로 이동
		// 메세지 띄우고 member/login 으로 이동		
		
		
		
		System.out.println("=== postHandle end ===");
	}

}
