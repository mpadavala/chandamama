package com.sample.finance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sample.finance.dto.Stock;
import com.sample.finance.dto.StockDetailsResult;
import com.sample.finance.util.StockUtil;

public class StockDetailsDao {

	private static final String GET_TICKERS = "SELECT TICKER FROM TICKERS";
	private static final String GET_STOCK_DETAILS = "SELECT * FROM STOCK_DETAILS_STR";
	private static final String TRUNCATE_STOCK_DETAILS_STR = "TRUNCATE TABLE STOCK_DETAILS_STR";
	private static String SCRUBBED_INSERT_QUERY= "INSERT INTO STOCK_DETAILS VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static String MAKET_GAINERS = "select * from stock_details where TRADEDATE = ? and gainorloss > 0" 
											+ " and marketcap > ? and totalvolume > ? order by";
	
	private static String MARKET_LOOSERS = "select * from stock_details where TRADEDATE = ? and gainorloss < 0"
											+ " and marketcap > ? and totalvolume > ? order by";
	
	private static final int NUMBER_OF_STOCKS_PER_REQUEST = 100;
	
	private static final String SYMBOL = "SYMBOL";
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
	private static final String FIFTYTWOWEEKRANGE = "FIFTYTWOWEEKRANGE";
	private static final String EPS = "EPS";
	private static final String PE = "PE";
	private static final String COMAPNYNAME = "COMAPNYNAME";
	private static final String CREATION_DATE = "CREATION_DATE";
	
	public static List<Stock> getMaketGainers(Date date, long marketCap, long totalvolume, String orderByColumn, String sortOrder, int howmany)throws Exception{
		return getFromDB(MAKET_GAINERS, date, marketCap, totalvolume, orderByColumn, sortOrder, howmany);
	}
	
	public static List<Stock> getMarketLoosers(Date date, long marketCap, long totalvolume, String orderByColumn, String sortOrder, int howmany)throws Exception{
		return getFromDB(MARKET_LOOSERS, date, marketCap, totalvolume, orderByColumn, sortOrder, howmany);
	}

	public static List<Stock> getFromDB(String query, Date date, long marketCap, long totalvolume, String orderByColumn, String sortOrder, int howmany)throws Exception{
		
		Connection connection = null;
		PreparedStatement psmt = null;
		List<Stock> stocks = null;
		try{
			connection = DBConnection.getConnection();
			psmt = connection.prepareStatement(query + " "+orderByColumn+ " " + sortOrder);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			psmt.setDate(1, sqlDate);
			psmt.setLong(2, marketCap);
			psmt.setLong(3, totalvolume);
			ResultSet rs = psmt.executeQuery();
			stocks = convertToStockList(rs, howmany);
			System.out.println(query);
			System.out.println(sqlDate + ", "+marketCap+", "+totalvolume+", "+ orderByColumn+ " " + sortOrder);
			
		} catch (Exception e) {
			e.printStackTrace();
			//System.exit(0);
		}try{
			if(psmt != null){
				psmt.close();
			}
			if(connection != null){
				connection.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return stocks;
		
	}

	private static List<Stock> convertToStockList(ResultSet rs, int howmany)throws Exception{
		
		List<Stock> stocksList = null;
		int counter = 0;
		if(rs != null){
			stocksList = new ArrayList<Stock>();
			while (rs.next()) {
				
				if(counter++ == howmany){
					return stocksList;
				}
				
				Stock stock = new Stock();
				stock.setTicker(rs.getString(SYMBOL));
				stock.setLastTrade(StockUtil.stringToDouble_NA(rs.getString(LASTTRADE)));
				stock.setTradeDate(StockUtil.StringToDate(rs.getString(TRADEDATE)));
				stock.setGainOrLoss(StockUtil.stringToDouble_NA(rs.getString(GAINORLOSS)));
				stock.setOpenedAt(StockUtil.stringToDouble_NA(rs.getString(OPENEDAT)));
				stock.setDaysHigh(StockUtil.stringToDouble_NA(rs.getString(DAYSHIGH)));
				stock.setDaysLow(StockUtil.stringToDouble_NA(rs.getString(DAYSLOW)));
				stock.setTotalVolume(StockUtil.stringToDouble_NA(rs.getString(TOTALVOLUME)));
				stock.setMarketCap(StockUtil.stringToDouble_NA_KMB(rs.getString(MARKETCAP)));
				stock.setPreviousClose(StockUtil.stringToDouble_NA(rs.getString(PREVIOUSCLOSE)));
				stock.setPercentGainOrLoss(StockUtil.stringToDouble_PER(rs.getString(PERCENTGAINORLOSS)));
				
				//TODO:: to set these values properly
				//stock.setFiftyTwoWeekLow(StockUtil.stringToDouble_NA_NA(rs.getString(FIFTYTWOWEEKRANGE), 1));
				//stock.setFiftyTwoWeekHigh(StockUtil.stringToDouble_NA_NA(rs.getString(FIFTYTWOWEEKRANGE), 2));
				stock.setEPS(StockUtil.stringToDouble_NA(rs.getString(EPS)));
				stock.setPE(StockUtil.stringToDouble_NA(rs.getString(PE)));
				stock.setCompanyName(rs.getString(COMAPNYNAME));
				
				//TODO:: to format properly
				DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
				stock.setCreationDate(dateFormatter.parse(rs.getString(CREATION_DATE)));
				
				stocksList.add(stock);
			}
		}
		return stocksList;

		
	}
	
	public static List<Stock> getStockDetailsFromDB()throws Exception{
		
		
		Connection connection = null;
		PreparedStatement psmt = null;
		List<Stock> stocks = null;
		try{
			connection = DBConnection.getConnection();
			psmt = connection.prepareStatement(GET_STOCK_DETAILS);
			ResultSet rs = psmt.executeQuery();
			stocks = convertToStockList(rs, 10000000);			
			
		} catch (Exception e) {
			e.printStackTrace();
			//System.exit(0);
		}try{
			if(psmt != null){
				psmt.close();
			}
			if(connection != null){
				connection.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return stocks;
	}

	public static void truncateStockDetailsStr() throws Exception{
		
		try{
			Connection conn = DBConnection.getConnection();
			Statement statement = conn.createStatement();;
			System.out.println(TRUNCATE_STOCK_DETAILS_STR);
			statement.execute(TRUNCATE_STOCK_DETAILS_STR);
				
			if(statement != null){
				statement.close();
			}
			if(conn != null){
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public static List<String> getTickers()throws Exception{
		
		List<String> tickers = new ArrayList<String>();
		Connection connection = DBConnection.getConnection();
		Statement statement = null;
		StringBuilder sb = new StringBuilder();
		
		if(connection != null){
			try {
				statement = connection.createStatement();
				int count = 0;
				int numberOfTickers = 0;
				ResultSet rs = statement.executeQuery(GET_TICKERS);
				
				while (rs.next()) {
					numberOfTickers++;
					sb.append(rs.getString(1));
					sb.append(",");
					if(++count == NUMBER_OF_STOCKS_PER_REQUEST){
						if(sb.length() > 0){
							tickers.add(sb.toString().substring(0, sb.length()-1));
							count = 0;
							sb = new StringBuilder();
						}
					}
				}
				if(sb.length() > 0){
					tickers.add(sb.toString().substring(0, sb.length()-1));
					count = 0;
					sb = new StringBuilder();
				}
				System.out.println("Number Of Tickers fetched from DB : " + numberOfTickers);
				if(numberOfTickers == 0){
					throw new Exception("No Tickers Available in DB for  : " + GET_TICKERS);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		try{
			if(statement != null){
				statement.close();
			}
			if(connection != null){
				connection.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return tickers;
	}
	
	public static StockDetailsResult insertScrubbedStocks(List<Stock> stocksList)throws Exception{
		
		StockDetailsResult result = new StockDetailsResult();
		
		try{
			Connection conn = DBConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(SCRUBBED_INSERT_QUERY);

			for(Stock stock: stocksList){
				statement.setString(1, stock.getTicker());
				statement.setDouble(2, stock.getLastTrade());
				
				if(stock.getTradeDate() != null){	
					statement.setDate(3, new java.sql.Date(stock.getTradeDate().getTime()));
				}else{
					Calendar calendar = Calendar.getInstance();
					calendar.set(1980,01,01);
					statement.setDate(3, new java.sql.Date(calendar.getTimeInMillis()));
				}
				statement.setDouble(4, stock.getGainOrLoss());
				statement.setDouble(5, stock.getOpenedAt());
				statement.setDouble(6, stock.getDaysHigh());
				statement.setDouble(7, stock.getDaysLow());
				statement.setDouble(8, stock.getTotalVolume());
				statement.setDouble(9, stock.getMarketCap());
				statement.setDouble(10, stock.getPreviousClose());
				statement.setDouble(11, stock.getPercentGainOrLoss());
				statement.setDouble(12, stock.getFiftyTwoWeekLow());
				statement.setDouble(13, stock.getFiftyTwoWeekHigh());
				statement.setDouble(14, stock.getEPS());
				statement.setDouble(15, stock.getPE());
				statement.setString(16, stock.getCompanyName());
				statement.setDate(17, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
				
				try{
					statement.execute();
					result.addSuccessfulInserts();
				}catch(Exception e){
					result.addFailedInserts(stock, e);
				}
			}
			try{
				if(statement != null){
					statement.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(Exception e){
				throw e;		
			}
		}
		catch(Exception e){
			throw e;
		}
		return result;
	}
	
	public static Date getDate()throws Exception{
		
		Connection connection = DBConnection.getConnection();
		Statement statement = null;
		Date date = null;
		
		if(connection != null){
			try {
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("select sysdate()  from dual");
				while (rs.next()) {
					date = rs.getDate(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		try{
			if(statement != null){
				statement.close();
			}
			if(connection != null){
				connection.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
	}
	
}
