package com.hs.s1.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestSchedule {
	
//	@Scheduled(fixedRate = 1000)
	// 단순히 String으로
	@Scheduled(fixedRateString = "1000", initialDelayString = "2000") // 단위 millisecond
	public void fixRateScheduleTest() throws Exception {
		System.out.println("fixRateSchedule");
	}
	
	@Scheduled(cron = "* * * * * *")
	public void cronTest() throws Exception {
		System.out.println("=== Cron ===");
	}

}
