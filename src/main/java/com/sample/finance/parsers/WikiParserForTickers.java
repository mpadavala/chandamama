package com.sample.finance.parsers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public abstract class WikiParserForTickers {

	String urlString;
	
	protected abstract String getUrl();
	
	protected List<String> parse(){

		List<String> tickers = new ArrayList<String>();
		urlString = getUrl();
		try{
			URL url = new URL(urlString);
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
}
