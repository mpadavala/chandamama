package com.sample.finance.fileimport;

import java.util.logging.Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sample.finance.dao.TickersDao;

public class CommandLineLoader {

	private static final Logger logger = Logger.getLogger(CommandLineLoader.class.getName());
	
	//private static final String basePath = "/Users/muralipadavala/dev/fileinterface/tickers";
	
	public static void main(String args[]){
		
		if(args == null){
			invalidArguments();
			return;
		}
		
		if(args[0].equalsIgnoreCase("stocks")){
			//loadStocks();
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
		TickersDao dao = (TickersDao) context.getBean("tickerDao");
		TickersLoader csvFileLoader = new TickersLoader();
		csvFileLoader.setTickerDao(dao);
		csvFileLoader.load(1, rootPathOfTickers);
	}
	

	/***
	 * DONT CHANGE THE MAIN METHOD and HOW XML is LOADED - USED FOR RUNNING JAR
	 *  */
	/*
	private static void loadStocks(){
		// *****DONT CHANGE HOW THE XML is REFERENCED BELOW.. IT WORKS FOR RUNNING AS RUNNABLE JAR*******
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("resources/applicationContext.xml");
		TickersDao tickerdao = (TickersDao) context.getBean("tickerDao");
		StockDao stockdao = (StockDao) context.getBean("stockDataDao");
		
		StockDataLoader loader = new StockDataLoader();
		
		loader.setTickerDao(tickerdao);
		loader.setStockDataDao(stockdao);
		loader.load();
	}
	
	*/
	
	private static void invalidArguments(){
		logger.info("Invalid Arguments : Usage below");
		logger.info("java -jar finance.jar tickers <base-path-of-folder>");
		logger.info("java -jar finance.jar stocks");
	}
}
