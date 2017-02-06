package com.sample.finance.batch;

import java.util.List;

import com.sample.finance.dao.StockDetailsDao;
import com.sample.finance.dto.Stock;
import com.sample.finance.dto.StockDetailsResult;

public class StockBatch{
	
  public static void main(String[] a){
    process();
  }

  //private static Logger logger =Logger.getLogger(StockBatch.class);
  
  public static void process(){
	  
    try {
		StockDetailsDao.truncateStockDetailsStr();
		System.out.println("Truncating Staging table is Done !!!");
		
		List<String> tickers = StockDetailsDao.getTickers();
		for (String ticker : tickers) {
		  StockDetailsProvider.getStockDetailsFromYahoo(ticker);
		}
		System.out.println("Getting Details from Yahoo is Done !!!");

		List<Stock> stocksList = StockDetailsDao.getStockDetailsFromDB();
		System.out.println("Got back the Details from DB is Done !!!");

		StockDetailsResult result = StockDetailsDao.insertScrubbedStocks(stocksList);
		System.out.println("Number of Successful Inserts : " + result.getSuccessfulInserts());
		System.out.println("Number of Failed Inserts : " + result.getFailedInserts().size());
		System.out.println(result.getFailedInsertsList());
		
		//TODO:
		//WatchList.getHtml(StockDAO.getTopGainers(new Date()));
		//WatchList.getHtml(StockDAO.getTopLoosers(new Date()));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
  }

  public static void getTickersTest(){
	  
    try {
		List<String> tickers = StockDetailsDao.getTickers();
		for (String ticker : tickers){
		  System.out.println(ticker);
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}