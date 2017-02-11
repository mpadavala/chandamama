package com.sample.finance.scheduler;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sample.finance.service.StocksService;
import com.sample.finance.util.StockUtil;

@Service
@EnableScheduling
public class StockDataScheduler {

	private static final Logger logger = Logger.getLogger(StockDataScheduler.class.getName());
	
	@Autowired
	private StocksService stocksService;
	
	//@Scheduled(cron = "	0 0/1 * 1/1 * *")
	public void cronTask() {
		logger.info("Sample Cron Task : " + StockUtil.getTimeFormatter().format(new Date()));
		stocksService.testService();
	}
	
	@Scheduled(cron = "0 0 14 * * MON-FRI")
	public void loadStockData() {
		
		try {
			logger.info("Loading Stock Data : " + StockUtil.getTimeFormatter().format(new Date()));
			stocksService.loadStockData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
