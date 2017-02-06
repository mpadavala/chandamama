package com.sample.finance.batch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import com.sample.finance.dao.DBConnection;
import com.sample.finance.util.StockUtil;

public class StockDetailsProvider {
	
	
	private static final String COMMA = ",";
	private static final String COMMA_WITH_SINGLE_QUOTES = "','";
	private static final int NUMBER_OF_CSV_VALUES_PER_STOCK = 16;
	private static final String FIRST_URL = "http://quote.yahoo.com/d/quotes.csv?s=";
	private static final String END_URL = "&d=t&f=sl1d1t1c1ohgvj1pp2wern";
	
	
	public static void getStockDetailsFromYahoo(String commaSeperatedSymbols){
		//http://quote.yahoo.com/d/quotes.csv?s=C,ebay,java,tivo&d=t&f=sl1d1t1c1ohgvj1pp2wern
		
		if(commaSeperatedSymbols == null) return;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String creationDate = sdf.format(new Date());
		String urlStr = FIRST_URL+commaSeperatedSymbols+END_URL;
		try{
			URL url = new URL(urlStr);	
			URLConnection urlConn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			String line;
			Connection conn = DBConnection.getConnection();
			if(conn == null){
				return; //throw exception
			}
			Statement statement = conn.createStatement();
			
			while ((line = in.readLine()) != null) {
				StringBuffer value = new StringBuffer();
				line = StockUtil.removeSpecialCharacters(line);
				StringTokenizer st = new StringTokenizer(line,COMMA);
				int numValues=0;
				while(st.hasMoreTokens() && numValues <NUMBER_OF_CSV_VALUES_PER_STOCK){
					String token = st.nextToken();
					value.append(token);
					value.append(COMMA_WITH_SINGLE_QUOTES);
					numValues++;
				}
				if(numValues<NUMBER_OF_CSV_VALUES_PER_STOCK){
					for(int i=0; i<NUMBER_OF_CSV_VALUES_PER_STOCK-numValues; i++){
						value.append(COMMA_WITH_SINGLE_QUOTES);
					}
				}
				int endIndex = value.length()-COMMA_WITH_SINGLE_QUOTES.length();
				String insertQuery = "insert into STOCK_DETAILS_STR values('"+value.toString().substring(0, endIndex)+"','"+creationDate+"')";
				statement.addBatch(insertQuery);
			}
			statement.executeBatch();
			
			try{
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
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
