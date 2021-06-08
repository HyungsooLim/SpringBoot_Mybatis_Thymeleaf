package com.hs.s1.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hs.s1.member.MemberVO;

@Component
public class AdminInterceptor implements HandlerInterceptor {

	/**
	 * Controller 진입 전 admin 판별
	 * 아니면
	 * 1. 로그인 폼으로 redirect
	 * 2. 권한이 없음 alert, index로 이동
	 * */
	
	// /notice/insert, update, delete
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("=== preHandle 시작 ===");
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		Boolean result = false;
		if(memberVO != null && memberVO.getUsername().equals("admin")) {
			result = true;
		}else {
			// 1. redirect login
			response.sendRedirect("/member/login");
			// 2. forward
//			request.setAttribute("message", "Access Denied");
//			request.setAttribute("path", "/member/login");
//			RequestDispatcher view = request.getRequestDispatcher("../common/result.html");
//			view.forward(request, response);
		}
		System.out.println("=== preHandle 종료 ===");
		
		

		
		
		return result;
	}
	
	
	
}
