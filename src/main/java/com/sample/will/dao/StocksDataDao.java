package com.sample.will.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.sample.will.dto.MarketCap;
import com.sample.will.dto.Stock;
import com.sample.will.dto.StockDetailsResult;
import com.sample.will.util.Constants;
import com.sample.will.util.StockUtil;

@Component
public class StocksDataDao extends BaseDao{

	private static final Logger logger = Logger.getLogger(StocksDataDao.class.getName());
	
	private static String INSERT_STOCK_DATA = "INSERT INTO STOCK_DATA VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static String MARKET_GAINERS = "SELECT S.TICKER, T.EXCHANGE, S.LASTTRADE, S.TRADEDATE, S.GAINORLOSS, S.OPENEDAT, S.DAYSHIGH, S.DAYSLOW, S.TOTALVOLUME, "
			+ "S.MARKETCAP, S.PREVIOUSCLOSE, S.PERCENTGAINORLOSS, S.FIFTYTWOWEEKLOW, S.FIFTYTWOWEEKHIGH, S.EPS, S.PE, S.COMPANYNAME, S.CREATIONDATE "
			+ "FROM STOCK_DATA S, TICKERS T WHERE T.TICKER=S.TICKER AND DATE_FORMAT(TRADEDATE, ''%m-%d-%Y'') = ? AND GAINORLOSS > 0 AND MARKETCAP > ? AND TOTALVOLUME > ? ORDER BY {0} {1} LIMIT ?;";	
	private static String MARKET_LOOSERS = "SELECT S.TICKER, T.EXCHANGE, S.LASTTRADE, S.TRADEDATE, S.GAINORLOSS, S.OPENEDAT, S.DAYSHIGH, S.DAYSLOW, S.TOTALVOLUME, "
			+ "S.MARKETCAP, S.PREVIOUSCLOSE, S.PERCENTGAINORLOSS, S.FIFTYTWOWEEKLOW, S.FIFTYTWOWEEKHIGH, S.EPS, S.PE, S.COMPANYNAME, S.CREATIONDATE "
			+ "FROM STOCK_DATA S, TICKERS T WHERE T.TICKER=S.TICKER AND DATE_FORMAT(TRADEDATE, ''%m-%d-%Y'') = ? AND GAINORLOSS < 0 AND MARKETCAP > ? AND TOTALVOLUME > ? ORDER BY {0} {1} LIMIT ?";
	private static String MAKETCAP_BY_DATE = "SELECT TRADEDATE, SUM(MARKETCAP), COUNT(*) FROM STOCK_DATA GROUP BY TRADEDATE ORDER BY TRADEDATE DESC LIMIT ?";
	
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
	private static final String CREATIONDATE = "CREATIONDATE";

	
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
		
		List<MarketCap> resultSet = getJdbcTemplate().query(MAKETCAP_BY_DATE, new Object[]{howmany}, new RowMapper<MarketCap>(){

			public MarketCap mapRow(ResultSet rs, int rowNum) throws SQLException {
				MarketCap marketCap = new MarketCap();
				marketCap.setDate(rs.getDate(1));
				marketCap.setTotalMarketCap(rs.getDouble(2));
				marketCap.setNumberOfStocksConsidered(rs.getInt(3));
				marketCap.setTotalMarketCapHumanReadable(StockUtil.MarketCapHumanReadable(marketCap.getTotalMarketCap()));
				marketCap.setDayOftheWeek(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(marketCap.getDate().getTime()));
				return marketCap;
			}
		});
		
		return resultSet;
	}
	
	private class StockDataRowMapper implements RowMapper<Stock>{
		
		public Stock mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Stock stock = new Stock();
			stock.setTicker(resultSet.getString(TICKER));
			stock.setExchange(resultSet.getString("EXCHANGE"));
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
			stock.setCreationDate(resultSet.getDate(CREATIONDATE));
			
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
