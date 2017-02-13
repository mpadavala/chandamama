package com.sample.finance.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.finance.dao.TickersDao;
import com.sample.finance.dto.Sector;
import com.sample.finance.dto.SubSector;
import com.sample.finance.dto.Ticker;
import com.sample.finance.fileimport.TickersLoader;

@Component
public class TickersService {
	
	private static final Logger logger = Logger.getLogger(TickersService.class.getName());

	@Autowired
	private TickersDao tickersDao;

	
	public void setTickersDao(TickersDao tickersDao) {
		this.tickersDao = tickersDao;
	}

	public void testService() {
		logger.info("calling test service..");
	}
	
	public List<Ticker> getAllTickers() throws Exception{
		logger.info("In gettickers ");
		return tickersDao.getAllTickers();
	}
	
	public Ticker getTicker(String ticker) throws Exception{
		return tickersDao.getTicker(ticker.trim());
	}
	
	public List<Sector> getSectors() throws Exception{
		return tickersDao.getSectors();
	}
	
	public List<SubSector> getSubsectors() throws Exception{
		return tickersDao.getSubsectors();
	}
	
	public void loadTickers() throws Exception{
		int numberOfLinesToSkip = 1;
		String baseFolderPath  = "/home/murali/dev/repositories/git/finance/src/main/resources/tickers";
		TickersLoader importTickers = new TickersLoader();
		importTickers.setTickerDao(tickersDao);
		importTickers.load(numberOfLinesToSkip, baseFolderPath);
	}
	
}
