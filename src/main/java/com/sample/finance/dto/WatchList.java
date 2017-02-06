package com.sample.finance.dto;

import java.io.Serializable;
import java.util.Date;

public class WatchList implements Serializable {
	
	private static final long serialVersionUID = -7695406596897853214L;
	
	private String ticker;
	private int count;
	private Date creationDate;
	
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Override
	public String toString() {
		return "WatchList [ticker=" + ticker + ", count=" + count
				+ ", creationDate=" + creationDate + "]";
	}

}
