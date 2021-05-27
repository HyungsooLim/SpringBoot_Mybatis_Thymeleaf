package com.hs.s1.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

// 로그인 성공 후 권한 에러 발생했을때 실행하는 핸들러

// 1. AccessDeniedHandler Inferface 구현 
public class SecurityException implements AccessDeniedHandler {

// 2. handle() 메서드 오버라이딩
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
//		RequestDispatcher view = request.getRequestDispatcher("경로");
//		request.setAttribute("key", "value");
//		view.forward(request, response);
		
		response.sendRedirect("/member/error");
		
	}

	
}
