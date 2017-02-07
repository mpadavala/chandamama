package com.sample.finance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.sample.finance.dto.Sector;
import com.sample.finance.dto.SubSector;
import com.sample.finance.dto.Ticker;

@Component
public class TickersDao extends BaseDao{

	private static final Logger logger = Logger.getLogger(TickersDao.class.getName());
	
	//private static final String GET_ETF_TICKERS = "SELECT TICKER FROM ETF_TICKERS";
	private static final String INSERT_TICKER = "INSERT INTO TICKERS (TICKER, COUNTRY, NAME, IPOYEAR, SECTOR, INDUSTRY, SUMMARYURL, CREATIONDATE, MODIFIEDDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_TICKER = "UPDATE TICKERS SET NAME=?, IPOYEAR=? , SECTOR=?, INDUSTRY=?, MODIFIEDDATE=? WHERE TICKER = ?";
	private static final String GET_ALL_TICKERS = "SELECT * FROM TICKERS";
	private static final String GET_TICKER_BY_ID = "SELECT * FROM TICKERS WHERE TICKER=?";
	private static final String GET_SECTORS = "SELECT Sector, COUNT(*) Count FROM TICKERS GROUP BY SECTOR ORDER BY COUNT(*) DESC;";
	private static final String GET_SUBSECTORS = "SELECT SECTOR, INDUSTRY, COUNT(*) FROM TICKERS GROUP BY SECTOR, INDUSTRY ORDER BY SECTOR ASC, COUNT(*) DESC";
	
	
	public List<Ticker> getAllTickers()throws Exception{
		
		logger.info("Getting the List of All the tickers : " + GET_ALL_TICKERS);
		List<Ticker> tickers = getJdbcTemplate().query(GET_ALL_TICKERS,  new TickerRowMapper());
		logger.info("List of tickers avaialable : " + tickers.size());
		return tickers;
	}
	
	public Ticker getTicker(String ticker)throws Exception{
		
		ticker = ticker.toUpperCase();
		logger.info("Getting ticker Information by ticker  for ticker : " + ticker);
		logger.info("Query to fetch ticker Info : " + GET_TICKER_BY_ID);
		
		Ticker result = null;
		try {
			result = getJdbcTemplate().queryForObject(GET_TICKER_BY_ID, new Object[]{ticker},  new TickerRowMapper());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<Sector> getSectors()throws Exception{
		List<Sector> sectors = getJdbcTemplate().query(GET_SECTORS, new SectorRowMapper());
		return sectors;
	}
	
	public List<SubSector> getSubsectors(){
		List<SubSector> subsectors = getJdbcTemplate().query(GET_SUBSECTORS, new SubSectorRowMapper());
		return subsectors;
	}
	
	
	public void loadTickers(final List<Ticker> tickers) {
		
		int duplicateKeys = 0;
		if(tickers == null || tickers.size() == 0){
			logger.info("tickers size is : 0");
			return;
		}
		logger.info("tickers size is : " + tickers.size());

		Date date = new Date();
		for(Ticker ticker : tickers){
			
			try{
				getJdbcTemplate().update(INSERT_TICKER, new Object[]{
					ticker.getTicker(), 
					ticker.getCountry(),
					ticker.getCompanyName(),
					ticker.getIpoYear(),
					ticker.getSector(),
					ticker.getIndustry(),
					ticker.getSummaryUrl(),
					date,
					date
				});
			}catch(DuplicateKeyException e){
				duplicateKeys++;
				getJdbcTemplate().update(UPDATE_TICKER, new Object[]{
						ticker.getCompanyName(),
						ticker.getIpoYear(),
						ticker.getSector(),
						ticker.getIndustry(),
						date,
						ticker.getTicker()
				});
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.info("Number of duplicate tickers : " + duplicateKeys);
		logger.info("Number of tickers inserted : " + (tickers.size()-duplicateKeys));
	}
	
	private class TickerRowMapper implements RowMapper<Ticker>{
		
		public Ticker mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			
			Ticker ticker = new Ticker();
			ticker.setTicker(resultSet.getString("TICKER"));
			ticker.setCountry(resultSet.getString("COUNTRY"));
			ticker.setCompanyName(resultSet.getString("NAME"));
			ticker.setIpoYear(resultSet.getInt("IPOYEAR"));
			ticker.setSector(resultSet.getString("SECTOR"));
			ticker.setIndustry(resultSet.getString("INDUSTRY"));
			ticker.setSummaryUrl(resultSet.getString("SUMMARYURL"));
			ticker.setCreationDate(resultSet.getDate("CREATION_DATE"));
			ticker.setModifiedDate(resultSet.getDate("MODIFIED_DATE"));
			return ticker;
		}
	}
	
	private class SectorRowMapper implements RowMapper<Sector>{
		
		public Sector mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Sector sector = new Sector();
			sector.setSector(resultSet.getString(1));
			sector.setCount(resultSet.getInt(2));
			return sector;
		}
	}
	
	private class SubSectorRowMapper implements RowMapper<SubSector>{
		
		public SubSector mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			SubSector subsector = new SubSector();
			subsector.setSector(resultSet.getString(1));
			subsector.setSubSector(resultSet.getString(2));
			subsector.setCount(resultSet.getInt(3));
			return subsector;
		}
	}

}
