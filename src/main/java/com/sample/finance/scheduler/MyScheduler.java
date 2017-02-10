package com.sample.finance.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sample.finance.service.StocksService;

import java.util.Date;

@Service
@EnableScheduling
public class MyScheduler {

	@Autowired
	private StocksService stocksService;
	
	
	@Scheduled(cron = "	0 0/1 * 1/1 * *")
	public void cronTask() {
		System.out.println(new Date() + " This runs in a cron schedule");
		stocksService.testService();
	}
	
}
