package com.sample.finance.dto;

import java.util.Date;

public class MarketCap {

	private Date date;
	private Double totalMarketCap;
	private String totalMarketCapHumanReadable;
	private int dayOftheWeek;
	
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
	public int getDayOftheWeek() {
		return dayOftheWeek;
	}
	public void setDayOftheWeek(int dayOftheWeek) {
		this.dayOftheWeek = dayOftheWeek;
	}
	
	@Override
	public String toString() {
		return "MarketCap [date=" + date + ", totalMarketCap=" + totalMarketCap
				+ ", totalMarketCapHumanReadable="
				+ totalMarketCapHumanReadable + ", dayOftheWeek="
				+ dayOftheWeek + "]";
	}
	

	
}
