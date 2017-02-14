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
import com.sample.finance.parsers.WikiParserForTickers;
import com.sample.finance.util.StockUtil;

@Component
public class TickersService {
	
	private static final Logger logger = Logger.getLogger(TickersService.class.getName());

	private static final String sAndPListUrl = "https://en.wikipedia.org/wiki/List_of_S%26P_500_companies";
	private static final String dowListUrl = "https://en.wikipedia.org/wiki/Dow_Jones_Industrial_Average";
	
	@Autowired
	private TickersDao tickersDao;
	
	@Autowired
	private WikiParserForTickers wikiParserForTickers;
	
	public void setTickersDao(TickersDao tickersDao) {
		this.tickersDao = tickersDao;
	}

	public void setWikiParserForTickers(WikiParserForTickers wikiParserForTickers) {
		this.wikiParserForTickers = wikiParserForTickers;
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
		String baseFolderPath  = "/Users/muralipadavala/dev/repositories/git/finance/src/main/resources/tickers";
		TickersLoader importTickers = new TickersLoader();
		importTickers.setTickerDao(tickersDao);
		importTickers.load(numberOfLinesToSkip, baseFolderPath);
	}

	public String loadSAndP500Data(){
		List<String> tickers = wikiParserForTickers.parse(sAndPListUrl);
		wikiParserForTickers.load(tickers, StockUtil.SANDP500_INDEX, StockUtil.USA);
		return "Done";
	}
	
	public String loadDow30Data(){
		List<String> tickers = wikiParserForTickers.parse(dowListUrl);
		wikiParserForTickers.load(tickers, StockUtil.DOW30_INDEX, StockUtil.USA);
		return "Done";
	}
}
