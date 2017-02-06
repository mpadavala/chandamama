package com.sample.finance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.sample.finance.dto.WatchList;
import com.sample.finance.util.Constants;

@Component
public class WatchListDao {

	
	private static final Logger logger = Logger.getLogger(StockDao.class.getName());
	
	private static String INSERT_WATCHLIST = "INSERT INTO WATCHLIST VALUES(?,?,?)";
	private static String UPDATE_WATCHLIST = "UPDATE WATCHLIST SET COUNT=COUNT+1 WHERE TICKER = ? AND DATE_FORMAT(CREATIONDATE, '%m-%d-%Y') = DATE_FORMAT(?, '%m-%d-%Y')";
	
	private static String SELECT_WATCHLIST_BY_TICKER = "SELECT * FROM WATCHLIST WHERE TICKER = ? ORDER BY CREATIONDATE DESC";
	private static String SELECT_WATCHLIST = "SELECT TICKER, SUM(COUNT) COUNT, MAX(CREATIONDATE) CREATIONDATE FROM WATCHLIST GROUP BY TICKER ORDER BY CREATIONDATE DESC LIMIT ?";	
	private static String SELECT_WATCHLIST_TO = "SELECT TICKER, SUM(COUNT) COUNT, MAX(CREATIONDATE) CREATIONDATE FROM WATCHLIST WHERE DATE_FORMAT(CREATIONDATE, '%m-%d-%Y') >= ? GROUP BY TICKER ORDER BY CREATIONDATE DESC LIMIT ?";	
	private static String SELECT_WATCHLIST_FROM = "SELECT TICKER, SUM(COUNT) COUNT, MAX(CREATIONDATE) CREATIONDATE FROM WATCHLIST WHERE DATE_FORMAT(CREATIONDATE, '%m-%d-%Y') <= ? GROUP BY TICKER ORDER BY CREATIONDATE DESC LIMIT ?";	
	private static String SELECT_WATCHLIST_RANGE = "SELECT TICKER, SUM(COUNT) COUNT, MAX(CREATIONDATE) CREATIONDATE FROM WATCHLIST WHERE DATE_FORMAT(CREATIONDATE, '%m-%d-%Y') >= ? AND DATE_FORMAT(CREATIONDATE, '%m-%d-%Y') <= ? GROUP BY TICKER ORDER BY CREATIONDATE DESC LIMIT ?";	
	
	private static final String TICKER = "TICKER";
	private static final String COUNT = "COUNT";
	private static final String CREATIONDATE = "CREATIONDATE";
	
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
	
	public boolean addToWatchList(String ticker, Date date)throws Exception{
		//return an object with more information instead of boolean 
		
		int numberOfRowsEffected = 0;
		try{
			numberOfRowsEffected = getJdbcTemplate().update(INSERT_WATCHLIST, new Object[]{ticker, 1, date});
		}catch(Exception e){
			numberOfRowsEffected = getJdbcTemplate().update(UPDATE_WATCHLIST, new Object[]{ticker, date});
		}
		return (numberOfRowsEffected>0);
	}
	
	public List<WatchList> getFromWatchList(String ticker)throws Exception{
		return getJdbcTemplate().query(SELECT_WATCHLIST_BY_TICKER, new Object[]{ticker},  new WatchListRowMapper());
	}
	
	public List<WatchList> getWatchList(Date from, Date to, int howmany)throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_DEFAULT);
		List<WatchList> watchList = null;
		if(from == null && to == null){
			watchList = getJdbcTemplate().query(SELECT_WATCHLIST, new Object[]{howmany},  new WatchListRowMapper());
		}else if(from != null && to == null){
			watchList = getJdbcTemplate().query(SELECT_WATCHLIST_FROM, new Object[]{sdf.format(from), howmany},  new WatchListRowMapper());
		}else if (from == null && to != null){
			watchList = getJdbcTemplate().query(SELECT_WATCHLIST_TO, new Object[]{sdf.format(to), howmany},  new WatchListRowMapper());
		}else{
			watchList = getJdbcTemplate().query(SELECT_WATCHLIST_RANGE, new Object[]{sdf.format(from), sdf.format(to), howmany},  new WatchListRowMapper());
		}
		return watchList;
	}
	
	private class WatchListRowMapper implements RowMapper<WatchList>{
		
		public WatchList mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			WatchList watchList = new WatchList();
			watchList.setTicker(resultSet.getString(TICKER));
			watchList.setCount(resultSet.getInt(COUNT));
			watchList.setCreationDate(resultSet.getDate(CREATIONDATE));
			return watchList;
		}
	}
	
}
