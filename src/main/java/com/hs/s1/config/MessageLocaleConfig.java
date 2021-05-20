package com.hs.s1.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageLocaleConfig implements WebMvcConfigurer{

	@Bean // 객체 만들어 주는거
	public LocaleResolver localeResolver() {
		// 1. session 사용하는 경우
		// Server에 저장됨
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.KOREA);
		
		// 2. cookie 사용하는 경우
		// Client web browser에 저장됨
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.KOREA);
		cookieLocaleResolver.setCookieName("lang");
		
		return cookieLocaleResolver;
	}
	
	//Interceptor -------------------------------------------------------------------------
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		// parameter를 받아서 언어 구분
		// ex) URL?lang=en
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}
	// ------------------------------------------------------------------------------------
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addInterceptors(registry);
		registry.addInterceptor(localeChangeInterceptor())
				.addPathPatterns("/**");
	}
	
}
