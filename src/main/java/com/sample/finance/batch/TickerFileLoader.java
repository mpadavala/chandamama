
package com.sample.finance.batch;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;

import com.sample.finance.dao.TickerDao;
import com.sample.finance.dto.Ticker;
import com.sample.finance.util.Constants;

public class TickerFileLoader {
	
	private static final Logger logger = Logger.getLogger(TickerFileLoader.class.getName());
	
	private static final String CSV_FILE_SUFFX = ".csv";
	private static final String COUNTRY_USA = "USA";
	
	@Autowired
	private TickerDao tickerDao;

	public void setTickerDao(TickerDao tickerDao) {
		this.tickerDao = tickerDao;
	}
	
	public void load(int numberOfLinesToSkip, String rootPathOfTickers){
		
		try{
			if(rootPathOfTickers == null){
				rootPathOfTickers = Constants.TICKERS_ROOT_FOLDER_PATH;
			}
			logger.info("Tickers Root FolderPath : " + rootPathOfTickers);
			File folder = new File(rootPathOfTickers);
			
			File[] files = folder.listFiles();
			if(files == null){
				logger.info("No ticker files in the folder to load : " + rootPathOfTickers);
				logger.info("Pass the tickers folder as an argument to jar");
				return;
			}
			for(File file : files){
				logger.info(file.toString());
				if(file.isFile()){
					logger.info(file.getAbsoluteFile().toString());
					List<Ticker> tickers = new ArrayList<Ticker>();
					if(file.toString().endsWith(CSV_FILE_SUFFX)){
						InputStream instream = new FileInputStream(file);
						InputStreamReader reader=new InputStreamReader(instream,Charset.forName("UTF-8"));
			
						CSVParser parser=new CSVParser(reader,CSVFormat.DEFAULT);
						List<CSVRecord> csvRecords = parser.getRecords();
						if(csvRecords != null){
							for(CSVRecord record : csvRecords){
								if(record.getRecordNumber() <=  numberOfLinesToSkip){
									continue;
								}
								String tickerSymbol = record.get(0).trim();
								if(tickerSymbol.contains("^")){
									continue;
								}
								if(tickerSymbol.contains("/")){
									tickerSymbol = tickerSymbol.replace("/", "-");
								}
								
								Ticker ticker = new Ticker();
								ticker.setTicker(tickerSymbol);
								ticker.setCountry(COUNTRY_USA);
								ticker.setCompanyName(record.get(1));
								ticker.setIpoYear(getIpoYear(record.get(4)));
								ticker.setSector(record.get(5));
								ticker.setIndustry(record.get(6));
								ticker.setSummaryUrl(record.get(7));
								tickers.add(ticker);
							}
				        }
						parser.close();
						instream.close();
						tickerDao.loadTickers(tickers);
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
	}

	
	private int getIpoYear(String year){
		int ipoYear = 0;
		try{
			ipoYear = Integer.parseInt(year);
		}catch(NumberFormatException e){
			// its expected, no need to handle this
		}
		return ipoYear;
	}
}
