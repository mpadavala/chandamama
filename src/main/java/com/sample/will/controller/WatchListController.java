package com.sample.will.controller;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.will.dao.WatchListDao;
import com.sample.will.dto.WatchList;
import com.sample.will.util.Constants;

@RestController
@RequestMapping("watchlist")
public class WatchListController {

	private static final Logger logger = Logger.getLogger(WatchListController.class.getName());
	private static final int DEFAULT_LIMIT=20;
	
	@Autowired
	private WatchListDao watchListDao;

	public void setWatchListDao(WatchListDao watchListDao) {
		this.watchListDao = watchListDao;
	}
	
	/** 
	 * This is it get the list of Watch list based on from and to dates and also you can use limit to 
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET,  produces="application/json")
	public @ResponseBody List<WatchList> getWatchList(@RequestParam(value="from", required = false)  @DateTimeFormat(pattern=Constants.DATE_FORMAT_DEFAULT) Date from,
			@RequestParam(value="to", required = false)  @DateTimeFormat(pattern=Constants.DATE_FORMAT_DEFAULT) Date to,
			@RequestParam(value = "limit", required = false) Integer limit, HttpServletRequest request) {
		
		try{
			if(limit == null){
				limit = DEFAULT_LIMIT;
			}
			
			logger.info("request url : " + request.getRequestURL());
			logger.info("from : " + from +" ; to : " + to +"; limit : " + limit);
			
			return watchListDao.getWatchList(from, to , limit);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/{ticker}", method=RequestMethod.PUT, produces="application/json")
	public @ResponseBody boolean addToWatchList(@PathVariable String ticker, 
			@RequestParam(value="date", required = false)  @DateTimeFormat(pattern=Constants.DATE_FORMAT_DEFAULT) Date date,
			HttpServletRequest request) {
		
		boolean status = false;
		try{
			if(date == null){
				date = new Date();
			}
			logger.info("request url : " + request.getRequestURL());
			logger.info("date : " + date);
			status = watchListDao.addToWatchList(ticker.trim().toUpperCase(), date);
		}catch(Exception e){
			e.printStackTrace();
			return status;
		}
		return status;
	}
	
	
	@RequestMapping(value="/{ticker}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<WatchList> getFromWatchListByTicker(@PathVariable String ticker, 
			HttpServletRequest request) {
		List<WatchList> watchList = null;
		try{
			logger.info("request url : " + request.getRequestURL());
			watchList = watchListDao.getFromWatchList(ticker.trim().toUpperCase());
		}catch(Exception e){
			e.printStackTrace();
		}
		return watchList;
			
	}

}
