package com.sample.finance.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.sample.finance.util.Constants;

@Component
public class StockIndexesDao extends BaseDao{

	private static final Logger logger = Logger.getLogger(StockIndexesDao.class.getName());

	private static final String INSERT_STOCK_INDEX  = "INSERT INTO STOCK_INDEXES(TICKER, INDEXNAME, COUNTRY, STATUS, CREATIONDATE) VALUES(?, ?, ?, ?,?)";
	private static final String UPDATE_INDEXEND_DATE  = "UPDATE STOCK_INDEXES SET  DATE_FORMAT(INDEXENDDATE, '%m-%d-%Y')  = ?  WEHRE STATUS = 'ACTIVE'";
	
	private static final String TICKER = "TICKER";
	private static final String INDEXNAME = "INDEXNAME";
	private static final String COUNTRY = "COUNTRY";
	private static final String STATUS = "STATUS";
	private static final String CREATIONDATE = "CREATIONDATE";
	private static final String INDEXENDDATE = "INDEXENDDATE";
	
	
	public void insert(List<String> tickers, String indexName, String country){
		
		//String date = (new SimpleDateFormat(Constants.DATE_FORMAT_DEFAULT)).format(new Date());
		
		for(String ticker : tickers){
			getJdbcTemplate().update(INSERT_STOCK_INDEX, new Object[]{ticker, indexName, country, "ACTIVE", new Date()});
		}
	}
	
}
