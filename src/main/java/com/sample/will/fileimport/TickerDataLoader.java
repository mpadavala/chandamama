package com.sample.will.fileimport;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.sample.will.dto.Stock;
import com.sample.will.dto.Ticker;
import com.sample.will.util.StockUtil;

public class TickerDataLoader {

	private static Logger logger = Logger.getLogger(TickerDataLoader.class.getName());
	
	private static final String BASE_URL = "http://quote.yahoo.com/d/quotes.csv?s=";
	private static final String END_URL = "&d=t&f=sl1d1c1ohgvj1pp2wern";
	private static final int NUMBER_OF_STOCKS_PER_REQUEST = 100;
	
	
	public List<Stock> getStockData(List<Ticker>tickers){
		//http://quote.yahoo.com/d/quotes.csv?s=C,ebay,java,tivo&d=t&f=sl1d1t1c1ohgvj1pp2wern
		
		List<String> commaSeperatedTickers = getCommaSeperatedTickers(tickers);
		List<Stock> stockDetails = new ArrayList<Stock>();
		try{
			if(commaSeperatedTickers != null && commaSeperatedTickers.size() > 0){
				for(String commaSeperatedTicker : commaSeperatedTickers){
					StringBuffer yahooURL = new StringBuffer().append(BASE_URL).append(commaSeperatedTicker).append(END_URL);
					logger.info(yahooURL.toString());
					URL url = new URL(yahooURL.toString());	
					URLConnection urlConnection = url.openConnection();
					InputStream inputStream = urlConnection.getInputStream();
					InputStreamReader reader=new InputStreamReader(inputStream,Charset.forName("UTF-8"));
				
					CSVParser parser=new CSVParser(reader,CSVFormat.DEFAULT);
					List<CSVRecord> csvRecords = parser.getRecords();
					
					if(csvRecords != null){
						for(CSVRecord record : csvRecords){
							logger.info(record.toString());
							Stock stock = new Stock();
							try {
								stock.setTicker(record.get(0));
								stock.setLastTrade(StockUtil.stringToDouble(record.get(1)));
								stock.setTradeDate(StockUtil.StringToDate(record.get(2)));
								stock.setGainOrLoss(StockUtil.stringToDouble(record.get(3)));
								stock.setOpenedAt(StockUtil.stringToDouble(record.get(4)));
								stock.setDaysHigh(StockUtil.stringToDouble(record.get(5)));
								stock.setDaysLow(StockUtil.stringToDouble(record.get(6)));
								stock.setTotalVolume(StockUtil.stringToDouble(record.get(7)));
								stock.setMarketCap(StockUtil.stringToDouble_KMBT(record.get(8)));
								stock.setPreviousClose(StockUtil.stringToDouble(record.get(9)));
								stock.setPercentGainOrLoss(StockUtil.stringToDouble_PER(record.get(10)));
								stock.setFiftyTwoWeekLow(StockUtil.stringToDouble_Range(record.get(11), 1));
								stock.setFiftyTwoWeekHigh(StockUtil.stringToDouble_Range(record.get(11), 2));
								stock.setEPS(StockUtil.stringToDouble(record.get(12)));
								stock.setPE(StockUtil.stringToDouble(record.get(13)));
								stock.setCompanyName(record.get(14));
								stock.setCreationDate(new Date());
								
								stockDetails.add(stock);
							} catch (Exception e) {
								logger.info("Ticker : " + record.get(0) + " ; data is not in the expected format!!");
								e.printStackTrace();
							}
						}
			        }
			        
					parser.close();
					inputStream.close();
					
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return stockDetails;
	}
	
	
	private List<String> getCommaSeperatedTickers(List<Ticker> tickers){
		
		List<String> commaSeperatedTickers = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		if(tickers != null && tickers.size() > 0){
			for(int i=0; i< tickers.size(); i++){
				if(sb.length() > 0 && i%NUMBER_OF_STOCKS_PER_REQUEST == 0){
					commaSeperatedTickers.add(sb.toString().substring(0, sb.length()-1));
					sb = new StringBuilder();
				}
				sb.append(tickers.get(i).getTicker());
				sb.append(",");
			}
			if(sb.length() > 0){
				commaSeperatedTickers.add(sb.toString().substring(0, sb.length()-1));
			}
		}
		return commaSeperatedTickers;
	}
}

