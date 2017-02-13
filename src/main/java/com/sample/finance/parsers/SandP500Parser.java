package com.sample.finance.parsers;

import java.util.logging.Logger;
import org.springframework.stereotype.Component;
import com.sample.finance.util.StockUtil;

@Component
public class SandP500Parser extends WikiParserForTickers{

	private static final String sAndPListUrl = "https://en.wikipedia.org/wiki/List_of_S%26P_500_companies";
	private static final Logger logger = Logger.getLogger(SandP500Parser.class.getName());
	

	@Override
	protected String getUrl() {
		return sAndPListUrl;
	}
	
	@Override
	protected String getIndexName(){
		return StockUtil.SANDP500_INDEX;
	}
	
	@Override
	protected String getCountry() {
		return StockUtil.USA;
	}

	public String loadSAndPData(){
		load(parse());
		return "Done";
	}
	
}
