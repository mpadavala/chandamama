package com.sample.will.dto;

import java.util.Date;

public class MarketCap {

	private Date date;
	private Double totalMarketCap;
	private String totalMarketCapHumanReadable;
	private int numberOfStocksConsidered;
	private String dayOftheWeek;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getTotalMarketCap() {
		return totalMarketCap;
	}
	public void setTotalMarketCap(Double totalMarketCap) {
		this.totalMarketCap = totalMarketCap;
	}
	public String getTotalMarketCapHumanReadable() {
		return totalMarketCapHumanReadable;
	}
	public void setTotalMarketCapHumanReadable(String totalMarketCapHumanReadable) {
		this.totalMarketCapHumanReadable = totalMarketCapHumanReadable;
	}
	public int getNumberOfStocksConsidered() {
		return numberOfStocksConsidered;
	}
	public void setNumberOfStocksConsidered(int numberOfStocksConsidered) {
		this.numberOfStocksConsidered = numberOfStocksConsidered;
	}
	public String getDayOftheWeek() {
		return dayOftheWeek;
	}
	public void setDayOftheWeek(String dayOftheWeek) {
		this.dayOftheWeek = dayOftheWeek;
	}
	
	@Override
	public String toString() {
		return "MarketCap [date=" + date + ", totalMarketCap=" + totalMarketCap + ", totalMarketCapHumanReadable="
				+ totalMarketCapHumanReadable + ", numberOfStocksConsidered=" + numberOfStocksConsidered
				+ ", dayOftheWeek=" + dayOftheWeek + "]";
	}
	
	
	
}
