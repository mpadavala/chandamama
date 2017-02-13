package com.sample.finance.parsers;

import java.util.List;
import java.util.logging.Logger;

import com.sample.finance.util.StockUtil;

public class Dow30Parser extends WikiParserForTickers{
	
	private static final Logger logger = Logger.getLogger(Dow30Parser.class.getName());
	private static final String dowListUrl = "https://en.wikipedia.org/wiki/Dow_Jones_Industrial_Average";
	
	@Override
	protected String getUrl() {
		return dowListUrl;
	}
	
	@Override
	protected String getIndexName(){
		return StockUtil.DOW30_INDEX;
	}
	
	@Override
	protected String getCountry() {
		return StockUtil.USA;
	}

	public String loadDow30Data(){
		load(parse());
		return "Done";
	}

	
}
