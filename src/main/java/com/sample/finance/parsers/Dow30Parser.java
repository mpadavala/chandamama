package com.sample.finance.parsers;

import java.util.List;
import java.util.logging.Logger;

public class Dow30Parser extends WikiParserForTickers{
	
	private static final Logger logger = Logger.getLogger(Dow30Parser.class.getName());
	private static final String dowListUrl = "https://en.wikipedia.org/wiki/Dow_Jones_Industrial_Average";
	
	@Override
	protected String getUrl() {
		return dowListUrl;
	}
	
	public static void main(String args[]){
		List<String> tickers = new Dow30Parser().parse();
	}
}
