package com.sample.finance.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.finance.dto.Sector;
import com.sample.finance.dto.SubSector;
import com.sample.finance.dto.Ticker;
import com.sample.finance.parsers.SandP500Parser;
import com.sample.finance.service.TickersService;

@RestController
@RequestMapping("tickers")
public class TickersController {
	
	private static final Logger logger = Logger.getLogger(TickersController.class.getName());
	
	@Autowired
	private TickersService tickersService;
	
	@Autowired
	private SandP500Parser sandP500Parser;
	
	@RequestMapping(value="/all", method = RequestMethod.GET, produces="application/json")
	public List<Ticker> getTickers(){
		try {
			logger.info("In getAllTickers");
			return tickersService.getAllTickers();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/{ticker}", method = RequestMethod.GET, produces="application/json")
	public Ticker getTicker(@PathVariable String ticker){
		try {
			logger.info("In getTicker");
			return tickersService.getTicker(ticker);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/sectors", method = RequestMethod.GET, produces="application/json")
	public List<Sector> getSectors(){
		try {
			logger.info("In getSectors");
			return tickersService.getSectors();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/subsectors", method = RequestMethod.GET, produces="application/json")
	public List<SubSector> getSubsectors(){
		
		try {
			logger.info("In getSubsectors");
			return tickersService.getSubsectors();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/loadtickers", method = RequestMethod.GET, produces="application/json")
	public void loadTickers(){
		try {
			logger.info("In loadTickers");
			tickersService.loadTickers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/loadsandp", method = RequestMethod.GET, produces="application/json")
	public void loadSAndPtickers(){
		try {
			logger.info("In load S&P Tickers");
			sandP500Parser.loadSAndPData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
