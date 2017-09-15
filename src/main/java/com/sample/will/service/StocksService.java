package com.sample.will.service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.will.dao.StocksDataDao;
import com.sample.will.dao.TickersDao;
import com.sample.will.dto.MarketCap;
import com.sample.will.dto.Stock;
import com.sample.will.dto.Ticker;
import com.sample.will.fileimport.StocksDataLoader;

@Component
public class StocksService {
	private static final Logger logger = Logger.getLogger(StocksService.class.getName());

	@Autowired
	private StocksDataDao stocksDataDao;

	@Autowired
	private TickersDao tickersDao;

	public void setStocksDataDao(StocksDataDao stocksDataDao) {
		this.stocksDataDao = stocksDataDao;
	}

	public void setTickersDao(TickersDao tickersDao) {
		this.tickersDao = tickersDao;
	}

	public void testService() {
		logger.info("calling test service..");
	}
	
	public List<Stock> getMarketGainers(long marketCap, long totalVolume,
			String orderByColumn, String sortOrder, Date date, Integer limit) throws Exception{
		return stocksDataDao.getMaketGainers(date, marketCap, totalVolume, orderByColumn, sortOrder, limit);
	}
	
	public List<Stock> getMarketLoosers( long marketCap,  long totalVolume,
			String orderByColumn, String sortOrder, Date date, Integer limit) throws Exception{
		return stocksDataDao.getMarketLoosers(date, marketCap, totalVolume, orderByColumn, sortOrder, limit);
	}
	
	public List<MarketCap> getMarketCapByDate(Integer limit) throws Exception {
		return stocksDataDao.getMarketCapByDate(limit);
	}

	public void loadStockData() throws Exception {
		logger.info("Starting Stock Data Load");
		List<Ticker> tickers = tickersDao.getAllTickers();
		logger.info("Getting Details from Yahoo!!!");
		StocksDataLoader stocksDataLoader = new StocksDataLoader();
		List<Stock> stocksData = stocksDataLoader.getStockData(tickers);
		logger.info("Getting Details from Yahoo Done. Size : " + stocksData.size());
		stocksDataDao.insertStockData(stocksData);
	}
}
