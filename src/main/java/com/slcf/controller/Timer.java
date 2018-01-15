package com.slcf.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;


import com.slcf.inter.DeptInter;

@Configuration
public class Timer {
	@Autowired
	private DeptInter dii;
	
	@Scheduled(cron="0/5 0/1 * * * ?")
	public void test() {
		System.out.println(dii.selectAll());
		System.out.println("!!!!!!!!");
	}
}
