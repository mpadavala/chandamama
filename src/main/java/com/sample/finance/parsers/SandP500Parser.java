package com.sample.finance.parsers;

import java.util.List;
import java.util.logging.Logger;

public class SandP500Parser extends WikiParserForTickers{

	private static final String sAndPListUrl = "https://en.wikipedia.org/wiki/List_of_S%26P_500_companies";
	private static final Logger logger = Logger.getLogger(SandP500Parser.class.getName());
	

	@Override
	protected String getUrl() {
		return sAndPListUrl;
	}
	
	public static void main(String args[]){
		List<String> tickers = new SandP500Parser().parse();
	}

	
}
