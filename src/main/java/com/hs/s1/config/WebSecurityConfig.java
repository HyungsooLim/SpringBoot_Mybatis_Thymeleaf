package com.hs.s1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	// 패스워드 암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// Security를 무시하는 경로 설정
		web.ignoring()
			.antMatchers("/resources/**")
			.antMatchers("/images/**")
			.antMatchers("/css/**")
			.antMatchers("/js/**")
			.antMatchers("/vendor/**")
			.antMatchers("/favicon/**")
			;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// URL에 따른 로그인, 권한 설정
		http
			// 권한 에러 발생(403)
			.exceptionHandling()
				.accessDeniedPage(null)		// error page 경로
				.accessDeniedHandler(null)	// error 처리 class
			.and()
			//csrf라는 토큰을 사용 X -> cross origin?
			.cors().and()
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/notice/list", "/notice/select").permitAll()
				.antMatchers("/notice/**").hasRole("ADMIN")
				.antMatchers("/qna/list").permitAll()
				.antMatchers("/qna/**").hasAnyRole("ADMIN", "MEMBER")
				.antMatchers("/member/join").permitAll()
				.antMatchers("/member/**").hasAnyRole("ADMIN", "MEMBER")
				.anyRequest().authenticated()
			.and()
			// 로그인 폼에 관한 메서드
			// 폼 따로 없으면 default login form 으로 이동
			.formLogin()
				.loginPage("/member/login")
				// 로그인 성공시 요청 보낼 URL 설정
				.defaultSuccessUrl("/member/memberLoginResult")
				// 로그인 실패 처리
				.failureUrl("/member/memberLoginFail")
					.permitAll()
			.and()
			.logout()
				.logoutUrl("/member/logout")
				.logoutSuccessUrl("/")
				// session invalidate
				.invalidateHttpSession(true)
				// 쿠키 지우기
				.deleteCookies("JSESSIONID")
				.permitAll()
			;
	}

}
