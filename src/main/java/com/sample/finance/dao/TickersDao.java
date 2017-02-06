package com.sample.finance.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;


public class TickersDao {

	private static final String GET_TICKERS = "SELECT TICKER FROM TICKERS";
	private static final String GET_ETF_TICKERS = "SELECT TICKER FROM ETF_TICKERS";
	
	public static Set<String> getTickers()throws Exception{
		return getTickers(GET_TICKERS);
	}
	
	public static Set<String> getEtfTickers() throws Exception{
		return getTickers(GET_ETF_TICKERS);
	}
	
	private static Set<String> getTickers(String query)throws Exception{
		
		Set<String> tickers = new HashSet<String>();
		Connection connection = DBConnection.getConnection();
		Statement statement = null;
		
		if(connection != null){
			try {
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				while (rs.next()) {
					tickers.add(rs.getString(1));
				}
			} catch (Exception e) {
				e.printStackTrace();
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
}
