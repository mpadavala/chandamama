package com.sample.finance.controller;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.finance.dao.StocksDataDao;
import com.sample.finance.dao.TickersDao;
import com.sample.finance.dto.MarketCap;
import com.sample.finance.dto.Stock;
import com.sample.finance.dto.Ticker;
import com.sample.finance.fileimport.StocksDataLoader;
import com.sample.finance.util.Constants;
import com.sample.finance.util.StockUtil;

@RestController
@RequestMapping("stocks")
public class StocksController{
	
	private static final Logger logger = Logger.getLogger(StocksController.class.getName());
	private static final int DEFAULT_LIMIT=10;
	
	@Autowired
	private StocksDataDao stocksDataDao;
	
	@Autowired
	private TickersDao tickersDao;
	

	@RequestMapping(value="/gainers", method = RequestMethod.GET,  produces="application/json")
	public @ResponseBody List<Stock> getMarketGainers(@RequestParam("marketcap") String marketCap, 
			@RequestParam("totalvolume") String totalVolume,
			@RequestParam("orderby") String orderByColumn,
			@RequestParam(value="date", required = false)  @DateTimeFormat(pattern=Constants.DATE_FORMAT_DEFAULT) Date date,
			@RequestParam(value = "limit", required = false) Integer limit, HttpServletRequest request) {
		
		try{
			long marketCapLong = (long)StockUtil.stringToDouble_KMBT(marketCap);
			long totalVolumeLong = (long)StockUtil.stringToDouble_KMBT(totalVolume);
			if(date == null){
				date = new Date();
			}
			if(limit == null){
				limit = DEFAULT_LIMIT;
			}
			String sortOrder = Constants.SORTORDER_DESC;
			
			logger.info("request url : " + request.getRequestURL());
			logger.info("date : " + date +"; marketCap : " + marketCapLong + "(" + marketCap + "); totalVolume : " + totalVolumeLong + 
					"(" + totalVolume + "); orderBy : " + orderByColumn + "; sortorder : " + sortOrder + "; limit : " + limit);
			
			return stocksDataDao.getMaketGainers(date, marketCapLong, totalVolumeLong, orderByColumn, sortOrder, limit);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/loosers", method = RequestMethod.GET,  produces="application/json")
	public @ResponseBody List<Stock> getMarketLoosers(@RequestParam("marketcap") String marketCap, 
			@RequestParam("totalvolume") String totalVolume,
			@RequestParam("orderby") String orderByColumn,
			@RequestParam(value="date", required = false)  @DateTimeFormat(pattern=Constants.DATE_FORMAT_DEFAULT) Date date,
			@RequestParam(value = "limit", required = false) Integer limit, HttpServletRequest request){
		
		try{
			long marketCapLong = (long)StockUtil.stringToDouble_KMBT(marketCap);
			long totalVolumeLong = (long)StockUtil.stringToDouble_KMBT(totalVolume);
			if(date == null){
				date = new Date();
			}
			if(limit == null){
				limit = DEFAULT_LIMIT;
			}
			String sortOrder = Constants.SORTORDER_ASC;
			logger.info("request url : " + request.getRequestURL());
			logger.info("date : " + date +"; marketCap : " + marketCapLong + "(" + marketCap + "); totalVolume : " + totalVolumeLong +
					"(" + totalVolume + "); orderBy : " + orderByColumn + "; sortorder : " + sortOrder + "; limit : " + limit);

			return stocksDataDao.getMarketLoosers(date, marketCapLong, totalVolumeLong, orderByColumn, sortOrder, limit);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	@RequestMapping(value="/marketcap", method = RequestMethod.GET,  produces="application/json")
	public List<MarketCap> getMarketCapByDate(@RequestParam(value = "limit", required = false) Integer limit){
		
		try{
			if(limit == null){
				limit = DEFAULT_LIMIT;
			}
			return stocksDataDao.getMarketCapByDate(limit);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/load", method = RequestMethod.GET,  produces="application/json")
		public void loadStockData() {
		
			logger.info("Starting Stock Data Load");
			try {
				List<Ticker> tickers = tickersDao.getAllTickers();
				logger.info("Getting Details from Yahoo!!!");
				StocksDataLoader stocksDataLoader = new StocksDataLoader();
				List<Stock> stocksData = stocksDataLoader.getStockData(tickers);
				
				logger.info("Getting Details from Yahoo Done. Size : " + stocksData.size());
				
				stocksDataDao.insertStockData(stocksData);
				
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
