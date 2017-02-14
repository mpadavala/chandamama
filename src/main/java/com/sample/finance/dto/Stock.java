package com.sample.finance.dto;

import java.io.Serializable;
import java.util.Date;

import com.sample.finance.util.StockUtil;

public class Stock implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private String ticker;
	private String exchange;
	private String url;
	private double lastTrade;
	private Date tradeDate;
	private double gainOrLoss;
	private double openedAt;
	private double daysHigh;
	private double daysLow;
	private double totalVolume;
	private double marketCap;
	private double previousClose;
	private double percentGainOrLoss;
	private double fiftyTwoWeekLow;
	private double fiftyTwoWeekHigh;
	private double EPS;
	private double PE;
	private String companyName;
	private Date creationDate;
	
	
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
		this.url= StockUtil.getGoogleBaseUrl()+ticker;
	}
	
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getUrl() {
		return url;
	}
	public double getLastTrade() {
		return lastTrade;
	}
	public void setLastTrade(double lastTrade) {
		this.lastTrade = lastTrade;
	}
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public double getGainOrLoss() {
		return gainOrLoss;
	}
	public void setGainOrLoss(double gainOrLoss) {
		this.gainOrLoss = gainOrLoss;
	}
	public double getOpenedAt() {
		return openedAt;
	}
	public void setOpenedAt(double openedAt) {
		this.openedAt = openedAt;
	}
	public double getDaysHigh() {
		return daysHigh;
	}
	public void setDaysHigh(double daysHigh) {
		this.daysHigh = daysHigh;
	}
	public double getDaysLow() {
		return daysLow;
	}
	public void setDaysLow(double daysLow) {
		this.daysLow = daysLow;
	}
	public double getTotalVolume() {
		return totalVolume;
	}
	public void setTotalVolume(double totalVolume) {
		this.totalVolume = totalVolume;
	}
	public double getMarketCap() {
		return marketCap;
	}
	public void setMarketCap(double marketCap) {
		this.marketCap = marketCap;
	}
	public double getPreviousClose() {
		return previousClose;
	}
	public void setPreviousClose(double previousClose) {
		this.previousClose = previousClose;
	}
	public double getPercentGainOrLoss() {
		return percentGainOrLoss;
	}
	public void setPercentGainOrLoss(double percentGainOrLoss) {
		this.percentGainOrLoss = percentGainOrLoss;
	}
	public double getFiftyTwoWeekLow() {
		return fiftyTwoWeekLow;
	}
	public void setFiftyTwoWeekLow(double fiftyTwoWeekLow) {
		this.fiftyTwoWeekLow = fiftyTwoWeekLow;
	}
	public double getFiftyTwoWeekHigh() {
		return fiftyTwoWeekHigh;
	}
	public void setFiftyTwoWeekHigh(double fiftyTwoWeekHigh) {
		this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
	}
	public double getEPS() {
		return EPS;
	}
	public void setEPS(double ePS) {
		EPS = ePS;
	}
	public double getPE() {
		return PE;
	}
	public void setPE(double pE) {
		PE = pE;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stock [ticker=");
		builder.append(ticker);
		builder.append(", lastTrade=");
		builder.append(lastTrade);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", gainOrLoss=");
		builder.append(gainOrLoss);
		builder.append(", openedAt=");
		builder.append(openedAt);
		builder.append(", daysHigh=");
		builder.append(daysHigh);
		builder.append(", daysLow=");
		builder.append(daysLow);
		builder.append(", totalVolume=");
		builder.append(totalVolume);
		builder.append(", marketCap=");
		builder.append(marketCap);
		builder.append(", previousClose=");
		builder.append(previousClose);
		builder.append(", percentGainOrLoss=");
		builder.append(percentGainOrLoss);
		builder.append(", fiftyTwoWeekLow=");
		builder.append(fiftyTwoWeekLow);
		builder.append(", fiftyTwoWeekHigh=");
		builder.append(fiftyTwoWeekHigh);
		builder.append(", EPS=");
		builder.append(EPS);
		builder.append(", PE=");
		builder.append(PE);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}