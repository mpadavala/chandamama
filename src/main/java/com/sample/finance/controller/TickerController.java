package com.sample.finance.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.finance.dao.TickerDao;
import com.sample.finance.dto.Sector;
import com.sample.finance.dto.SubSector;
import com.sample.finance.dto.Ticker;
import com.sample.finance.fileimport.ImportTickers;

@RestController
@RequestMapping("tickers")
public class TickerController {
	
	private static final Logger logger = Logger.getLogger(TickerController.class.getName());
	
	@Autowired
	private TickerDao tickerDao;

	public void setTickerDao(TickerDao tickerDao) {
		this.tickerDao = tickerDao;
	}

	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	public List<Ticker> getTickers() throws Exception{
		logger.info("In gettickers ");
		return tickerDao.getAllTickers();
	}
	
	@RequestMapping(value="/{ticker}", method = RequestMethod.GET, produces="application/json")
	public Ticker getTicker(@PathVariable String ticker) throws Exception{
		return tickerDao.getTicker(ticker.trim());
	}
	
	@RequestMapping(value="/sectors", method = RequestMethod.GET, produces="application/json")
	public List<Sector> getSectors() throws Exception{
		return tickerDao.getSectors();
	}
	
	
	@RequestMapping(value="/subsectors", method = RequestMethod.GET, produces="application/json")
	public List<SubSector> getSubsectors() throws Exception{
		return tickerDao.getSubsectors();
	}
	@RequestMapping(value="/load", method = RequestMethod.GET, produces="application/json")
	public void loadTickers(){
		int numberOfLinesToSkip = 1;
		String baseFolderPath  = "/Users/muralipadavala/dev/fileinterface/tickers";
		ImportTickers importTickers = new ImportTickers();
		importTickers.setTickerDao(tickerDao);
		importTickers.load(numberOfLinesToSkip, baseFolderPath);
	}
	
}
