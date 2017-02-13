package com.sample.finance.parsers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sample.finance.dao.StockIndexesDao;

public abstract class WikiParserForTickers {

	protected abstract String getUrl();
	protected abstract String getIndexName();
	protected abstract String getCountry();
	
	@Autowired
	StockIndexesDao stockIndexesDao;
	
	public void setStockIndexesDao(StockIndexesDao stockIndexesDao) {
		this.stockIndexesDao = stockIndexesDao;
	}
	
	protected List<String> parse(){

		List<String> tickers = new ArrayList<String>();
		try{
			URL url = new URL(getUrl());
			URLConnection urlConn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				if((line.contains("://www.nyse.com") || line.contains("://www.nasdaq.com")) && line.startsWith("<td>")){
					line = line.replace("</a></td>", "");
					line = line.substring(line.lastIndexOf(">")+1);
					tickers.add(line);
					//logger.info(line);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		return tickers;
	}
	
	protected void load(List<String> tickers){
		
		if(tickers != null){
			stockIndexesDao.insert(tickers, getIndexName(), getCountry());
		}
		
		
	}
}
