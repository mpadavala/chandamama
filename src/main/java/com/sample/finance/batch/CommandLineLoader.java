package com.sample.finance.batch;

import java.util.logging.Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sample.finance.dao.StockDao;
import com.sample.finance.dao.TickerDao;

public class CommandLineLoader {

	private static final Logger logger = Logger.getLogger(CommandLineLoader.class.getName());
	
	//private static final String basePath = "/Users/muralipadavala/dev/fileinterface/tickers";
	
	public static void main(String args[]){
		
		if(args == null){
			invalidArguments();
			return;
		}
		
		if(args[0].equalsIgnoreCase("stocks")){
			loadStocks();
		}else if(args[0].equalsIgnoreCase("tickers")){
			String rootPathOfTickers = null;
			if(args.length == 2){
				rootPathOfTickers = args[1];
			}
			loadTickers(rootPathOfTickers);
		}else{
			invalidArguments();			
		}
	}

	
	/***
	 * DONT CHANGE THE MAIN METHOD and HOW XML is LOADED - USED FOR RUNNING JAR
	 *  */
	private static void loadTickers(String rootPathOfTickers){
		// *****DONT CHANGE HOW THE XML is REFERENCED BELOW.. IT WORKS FOR RUNNING AS RUNNABLE JAR*******
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("resources/applicationContext.xml");
		TickerDao dao = (TickerDao) context.getBean("tickerDao");
		TickerFileLoader csvFileLoader = new TickerFileLoader();
		csvFileLoader.setTickerDao(dao);
		csvFileLoader.load(1, rootPathOfTickers);
	}
	

	/***
	 * DONT CHANGE THE MAIN METHOD and HOW XML is LOADED - USED FOR RUNNING JAR
	 *  */
	private static void loadStocks(){
		// *****DONT CHANGE HOW THE XML is REFERENCED BELOW.. IT WORKS FOR RUNNING AS RUNNABLE JAR*******
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("resources/applicationContext.xml");
		TickerDao tickerdao = (TickerDao) context.getBean("tickerDao");
		StockDao stockdao = (StockDao) context.getBean("stockDataDao");
		
		StockDataLoader loader = new StockDataLoader();
		
		loader.setTickerDao(tickerdao);
		loader.setStockDataDao(stockdao);
		loader.load();
	}
	
	private static void invalidArguments(){
		logger.info("Invalid Arguments : Usage below");
		logger.info("java -jar finance.jar tickers <base-path-of-folder>");
		logger.info("java -jar finance.jar stocks");
	}
}
