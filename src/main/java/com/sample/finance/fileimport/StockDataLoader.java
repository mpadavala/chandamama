package com.sample.finance.fileimport;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.sample.finance.dao.StockDao;
import com.sample.finance.dao.TickersDao;
import com.sample.finance.dto.Stock;
import com.sample.finance.dto.Ticker;

public class StockDataLoader {
	
	private static Logger logger = Logger.getLogger(StockDataLoader.class.getName());

	@Autowired
	private TickersDao tickerDao;
	@Autowired
	private StockDao stockDataDao;
	

	public void setTickerDao(TickersDao tickerDao) {
		this.tickerDao = tickerDao;
	}
	
	public void setStockDataDao(StockDao stockDataDao) {
		this.stockDataDao = stockDataDao;
	}


	public void load() {
		logger.info("Starting Stock Data Load");
		try {
			List<Ticker> tickers = tickerDao.getAllTickers();
			logger.info("Getting Details from Yahoo!!!");
			List<Stock> stocksData = StockDataProvider.getStockDetailsFromYahoo(tickers);
			
			logger.info("Getting Details from Yahoo Done. Size : " + stocksData.size());
			
			stockDataDao.insertStockData(stocksData);
			
			//TODO:: to get the list of failed inserts and do a report based on that.
			/*
			System.out.println("Number of Successful Inserts : " + result.getSuccessfulInserts());
			System.out.println("Number of Failed Inserts : " + result.getFailedInserts().size());
			System.out.println(result.getFailedInsertsList());
			 */
			// TODO:
			// WatchList.getHtml(StockDAO.getTopGainers(new Date()));
			// WatchList.getHtml(StockDAO.getTopLoosers(new Date()));
			  
			 
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}