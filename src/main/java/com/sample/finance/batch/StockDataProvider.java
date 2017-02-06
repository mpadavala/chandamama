package com.sample.finance.batch;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sample.finance.dto.Stock;
import com.sample.finance.dto.Ticker;
import com.sample.finance.util.StockUtil;

public class StockDataProvider {

	private static Logger logger = Logger.getLogger(StockDataProvider.class.getName());
	
	private static final String FIRST_URL = "http://quote.yahoo.com/d/quotes.csv?s=";
	private static final String END_URL = "&d=t&f=sl1d1c1ohgvj1pp2wern";
	private static final int NUMBER_OF_STOCKS_PER_REQUEST = 100;

	@SuppressWarnings("unused")
	private DataSource dataSource;	
	private JdbcTemplate jdbcTemplate;

	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	public JdbcTemplate getJdbcTemplate() {
		
		if(jdbcTemplate == null){
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			DataSource ds = (DataSource) context.getBean("dataSource");
			setDataSource(ds);
		}
		return jdbcTemplate;
	}
	
	
	public static List<Stock> getStockDetailsFromYahoo(List<Ticker>tickers){
		//http://quote.yahoo.com/d/quotes.csv?s=C,ebay,java,tivo&d=t&f=sl1d1t1c1ohgvj1pp2wern
		
		if(tickers == null || tickers.size() == 0) {
			return null;
		}
		
		List<Stock> stockDetails = new ArrayList<Stock>();
		List<String> commaSeperatedTickers = getCommaSeperatedTickers(tickers);
		
		try{
			if(commaSeperatedTickers != null && commaSeperatedTickers.size() > 0){
				for(String commaSeperatedTicker : commaSeperatedTickers){
					String urlStr = FIRST_URL+commaSeperatedTicker+END_URL;
					logger.info(urlStr);
					URL url = new URL(urlStr);	
					URLConnection urlConnection = url.openConnection();
					InputStream inputStream = urlConnection.getInputStream();
					InputStreamReader reader=new InputStreamReader(inputStream,Charset.forName("UTF-8"));
		/*		
					CSVParser parser=new CSVParser(reader,CSVFormat.DEFAULT);
					List<CSVRecord> csvRecords = parser.getRecords();
					
					if(csvRecords != null){
						for(CSVRecord record : csvRecords){

							Stock stock = new Stock();
							
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
						}
			        }
			        
					parser.close();
					inputStream.close();
					*/
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return stockDetails;
	}
	
	
	private static List<String> getCommaSeperatedTickers(List<Ticker> tickers){
		
		StringBuilder sb = new StringBuilder();
		List<String> commaSeperatedTickers = null;
		if(tickers != null && tickers.size() > 0){
			commaSeperatedTickers = new ArrayList<String>();
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

