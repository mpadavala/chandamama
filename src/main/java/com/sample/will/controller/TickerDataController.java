package com.sample.will.controller;

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

import com.sample.will.dto.Data;
import com.sample.will.dto.MarketCap;
import com.sample.will.dto.Stock;
import com.sample.will.service.TickerDataService;
import com.sample.will.util.Constants;
import com.sample.will.util.StockUtil;

@RestController
@RequestMapping("stocks")
public class TickerDataController{
	
	private static final Logger logger = Logger.getLogger(TickerDataController.class.getName());
	private static final int DEFAULT_LIMIT=10;
	
	@Autowired
	private TickerDataService stocksService;

	@RequestMapping(value="/gainers", method = RequestMethod.GET,  produces="application/json")
	public @ResponseBody Data getMarketGainers(@RequestParam("marketcap") String marketCap, 
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
			
			List<Stock> list = stocksService.getMarketGainers(marketCapLong, totalVolumeLong, orderByColumn, sortOrder, date, limit);
			return new Data(list);
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

			return stocksService.getMarketLoosers(marketCapLong, totalVolumeLong, orderByColumn, sortOrder, date, limit);
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
			return stocksService.getMarketCapByDate(limit);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/load", method = RequestMethod.GET,  produces="application/json")
		public void loadStockData() {
			try {
				stocksService.loadStockData();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	
}
