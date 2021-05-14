package com.hs.s1.aop.transfer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Card {

	@Around("execution(* com.hs.s1.aop.transfer.Transfer.*())")
	public Object cardCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("$$$ 탑승 전 카드 체크 $$$");
		//ProceedingJoinPoint - 핵심메서드(버스, 지하철)를 객체화
		Object obj = joinPoint.proceed();
		System.out.println(obj); // <- obj에는 joinPoint 리턴된 값이 들어온다
		System.out.println("$$$ 하차 시 카드 체크 $$$");
		
		return obj;
	}
	
	@AfterReturning("execution(* com.hs.s1.board.notice.NoticeService.*Select(..))")
	public void selectCheck() {
		System.out.println("Select Query 정상 종료");
	}
}
