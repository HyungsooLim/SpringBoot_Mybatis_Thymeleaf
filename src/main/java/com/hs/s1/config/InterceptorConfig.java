package com.hs.s1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hs.s1.interceptor.TestInterceptor;

@Configuration // XML root-context, servlet-context
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private TestInterceptor testInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 적용할 Interceptor bean을 등록
		registry.addInterceptor(testInterceptor)
		// 어떤 URL이 왔을때 Interceptor 발동 시킬 것인가?
		// . 붙이면 위에꺼에 이어서
		// add -> Interceptor를 적용할 URL 등록
		// exclude -> Interceptor에서 제외할 URL 등록
//		.addPathPatterns("/notice/**")
		.addPathPatterns("/qna/**")
		.excludePathPatterns("/notice/select");
		
//		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
