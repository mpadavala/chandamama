package com.sample.finance.dao;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDao {

	@SuppressWarnings("unused")
	private DataSource dataSource;	
	private JdbcTemplate jdbcTemplate;

	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	protected JdbcTemplate getJdbcTemplate() {
		
		if(jdbcTemplate == null){
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			DataSource ds = (DataSource) context.getBean("dataSource");
			setDataSource(ds);
		}
		return jdbcTemplate;
	}
	
}
