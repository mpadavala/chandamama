package com.sample.will.parsers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.will.dao.IndexDataDao;

@Component
public class WikiParserForTickers {

	@Autowired
	IndexDataDao indexDataDao;
	
	public void setIndexDataDao(IndexDataDao indexDataDao) {
		this.indexDataDao = indexDataDao;
	}
	
	public List<String> parse(String urlStr){

		List<String> tickers = new ArrayList<String>();
		try{
			URL url = new URL(urlStr);
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
	
	public void load(List<String> tickers, String indexName, String country){
		
		if(tickers != null){
			indexDataDao.insert(tickers, indexName, country);
		}
	}
}
