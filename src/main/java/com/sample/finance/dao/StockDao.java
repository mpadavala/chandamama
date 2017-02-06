package com.sample.finance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.sample.finance.dto.MarketCap;
import com.sample.finance.dto.Stock;
import com.sample.finance.dto.StockDetailsResult;
import com.sample.finance.util.Constants;
import com.sample.finance.util.StockUtil;

@Component
public class StockDao {

	private static final Logger logger = Logger.getLogger(StockDao.class.getName());
	
	private static String INSERT_STOCK_DATA = "INSERT INTO STOCK_DATA VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static String MARKET_GAINERS = "SELECT * FROM STOCK_DATA WHERE DATE_FORMAT(TRADEDATE, ''%m-%d-%Y'') = ? AND GAINORLOSS > 0 AND MARKETCAP > ? AND TOTALVOLUME > ? ORDER BY {0} {1} LIMIT ?;";	
	private static String MARKET_LOOSERS = "SELECT * FROM STOCK_DATA WHERE DATE_FORMAT(TRADEDATE, ''%m-%d-%Y'') = ? AND GAINORLOSS < 0 AND MARKETCAP > ? AND TOTALVOLUME > ? ORDER BY {0} {1} LIMIT ?";
	private static String MAKETCAP_BY_DATE = "SELECT TRADEDATE, SUM(MARKETCAP) FROM STOCK_DATA GROUP BY TRADEDATE ORDER BY TRADEDATE DESC LIMIT ?";
	
	
	private static final String TICKER = "TICKER";
	private static final String LASTTRADE = "LASTTRADE";
	private static final String TRADEDATE = "TRADEDATE";
	private static final String GAINORLOSS = "GAINORLOSS";
	private static final String OPENEDAT = "OPENEDAT";
	private static final String DAYSHIGH = "DAYSHIGH";
	private static final String DAYSLOW = "DAYSLOW";
	private static final String TOTALVOLUME = "TOTALVOLUME";
	private static final String MARKETCAP = "MARKETCAP";
	private static final String PREVIOUSCLOSE = "PREVIOUSCLOSE";
	private static final String PERCENTGAINORLOSS = "PERCENTGAINORLOSS";
	private static final String FIFTYTWOWEEKHIGH = "FIFTYTWOWEEKHIGH";
	private static final String FIFTYTWOWEEKLOW = "FIFTYTWOWEEKLOW";
	private static final String EPS = "EPS";
	private static final String PE = "PE";
	private static final String COMPANYNAME = "COMPANYNAME";
	private static final String CREATION_DATE = "CREATION_DATE";
	
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
	
	public List<Stock> getMaketGainers(Date date, long marketCap, long totalvolume, String orderByColumn, String sortOrder, int howmany)throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_DEFAULT);
		String marketGainersQuery = MessageFormat.format(MARKET_GAINERS, orderByColumn, sortOrder);
		List<Stock> gainers = getJdbcTemplate().query(marketGainersQuery, new Object[]{sdf.format(date), marketCap, totalvolume, howmany},  new StockDataRowMapper());
		return gainers;
	}
	
	public List<Stock> getMarketLoosers(Date date, long marketCap, long totalvolume, String orderByColumn, String sortOrder, int howmany)throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_DEFAULT);
		String marketLoosersQuery = MessageFormat.format(MARKET_LOOSERS, orderByColumn, sortOrder);
		List<Stock> loosers = getJdbcTemplate().query(marketLoosersQuery, new Object[]{sdf.format(date), marketCap, totalvolume, howmany},  new StockDataRowMapper());
		return loosers;
	}
	
	
	public List<MarketCap> getMarketCapByDate(int howmany)throws Exception{
		
		List<Map<Date, Double>> resultSet = getJdbcTemplate().query(MAKETCAP_BY_DATE, new Object[]{howmany}, new RowMapper<Map<Date, Double>>(){

			public Map<Date, Double> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<Date, Double> map = new HashMap<Date, Double>();
				map.put(rs.getDate(1), rs.getDouble(2));
				return map;
			}
		});
		
		Map<Date, Double> result = new TreeMap<Date, Double>(Collections.reverseOrder());
		for(Map<Date, Double> temp : resultSet){
			for (Map.Entry<Date, Double> entry : temp.entrySet()){
				result.put(entry.getKey(), entry.getValue());
			}
		}
		
		List<MarketCap> marketCapList = new ArrayList<MarketCap>();
		for(Map.Entry<Date, Double> entry : result.entrySet()){
			MarketCap marketCap = new MarketCap();
			marketCap.setDate(entry.getKey());
			Double totalMarketCap = entry.getValue();
			marketCap.setTotalMarketCap(totalMarketCap);
			marketCap.setTotalMarketCapHumanReadable(StockUtil.MarketCapHumanReadable(totalMarketCap));
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(entry.getKey());
			marketCap.setDayOftheWeek(calendar.get(Calendar.DAY_OF_WEEK));
			
			marketCapList.add(marketCap);
		}
		return marketCapList;
	}
	
	private class StockDataRowMapper implements RowMapper<Stock>{
		
		public Stock mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Stock stock = new Stock();
			stock.setTicker(resultSet.getString(TICKER));
			stock.setLastTrade(resultSet.getDouble(LASTTRADE));
			stock.setTradeDate(resultSet.getDate(TRADEDATE));
			stock.setGainOrLoss(resultSet.getDouble(GAINORLOSS));
			stock.setOpenedAt(resultSet.getDouble(OPENEDAT));
			stock.setDaysHigh(resultSet.getDouble(DAYSHIGH));
			stock.setDaysLow(resultSet.getDouble(DAYSLOW));
			stock.setTotalVolume(resultSet.getDouble(TOTALVOLUME));
			stock.setMarketCap(resultSet.getDouble(MARKETCAP));
			stock.setPreviousClose(resultSet.getDouble(PREVIOUSCLOSE));
			stock.setPercentGainOrLoss(resultSet.getDouble(PERCENTGAINORLOSS));
			stock.setFiftyTwoWeekHigh(resultSet.getDouble(FIFTYTWOWEEKHIGH));
			stock.setFiftyTwoWeekLow(resultSet.getDouble(FIFTYTWOWEEKLOW));
			stock.setEPS(resultSet.getDouble(EPS));
			stock.setPE(resultSet.getDouble(PE));
			stock.setCompanyName(resultSet.getString(COMPANYNAME));
			stock.setCreationDate(resultSet.getDate(CREATION_DATE));
			
			return stock;
		}
	}
	
	public StockDetailsResult insertStockData(List<Stock> stocks)throws Exception{
		
		StockDetailsResult result = new StockDetailsResult();
		
		int failedInserts = 0;
		
		if(stocks == null || stocks.size() == 0){
			logger.info("stocks Data size is : 0");
			return null;
		}
		
		logger.info("stocks size is : " + stocks.size());
		
		for(Stock stock : stocks){
			try{
				getJdbcTemplate().update(INSERT_STOCK_DATA, new Object[]{
						stock.getTicker(),
						stock.getLastTrade(),
						stock.getTradeDate(),
						stock.getGainOrLoss(),
						stock.getOpenedAt(),
						stock.getDaysHigh(),
						stock.getDaysLow(),
						stock.getTotalVolume(),
						stock.getMarketCap(),
						stock.getPreviousClose(),
						stock.getPercentGainOrLoss(),
						stock.getFiftyTwoWeekLow(),
						stock.getFiftyTwoWeekHigh(),
						stock.getEPS(),
						stock.getPE(),
						stock.getCompanyName(),
						stock.getCreationDate()
				});
			}catch(Exception e){
				failedInserts++;
				logger.info(stock.toString());
				e.printStackTrace();
			}
		}
		logger.info("Number of failed inserts for stock data  : " + failedInserts);
		logger.info("Number of stock data inserted : " + (stocks.size()-failedInserts));
		
		return result;
	}
}
