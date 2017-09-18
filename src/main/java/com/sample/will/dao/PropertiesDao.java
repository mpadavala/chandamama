package com.sample.will.dao;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

@Component
public class PropertiesDao extends BaseDao{

	private static final Logger logger = Logger.getLogger(PropertiesDao.class.getName());

	private static final String GET_KEY  = "SELECT APP_VALUE FROM PROPERTIES WHERE APP_KEY=?";
	private static final String INSERT_KEY  = "INSERT INTO PROPERTIES (APP_KEY, APP_VALUE) VALUES(?, ?);";
	private static final String UPDATE_KEY  = "UPDATE PROPERTIES SET APP_VALUE=? WHERE APP_KEY=?";
	
	public String get(String key){
		return getJdbcTemplate().queryForObject(GET_KEY, new Object[]{key}, String.class);
	}

	public void insert(String key, String  value){
		getJdbcTemplate().update(INSERT_KEY, new Object[]{key, value});
	}

	public void update(String key, String  value){
		getJdbcTemplate().update(UPDATE_KEY, new Object[]{value, key});
	}
	
}
