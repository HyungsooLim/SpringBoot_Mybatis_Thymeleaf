package com.hs.s1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hs.s1.interceptor.FirstInterceptor;

@Configuration
public class FirstInterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private FirstInterceptor firstInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addInterceptors(registry);
		registry.addInterceptor(firstInterceptor)
				.addPathPatterns("/notice/**")
				.excludePathPatterns("/notice/select")
				.order(1);
	}
}
