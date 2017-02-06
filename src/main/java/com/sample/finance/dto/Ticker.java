package com.sample.finance.dto;

import java.util.Date;

public class Ticker {

	private String ticker;
	private String country;
	private String companyName;
	private int ipoYear;
	private String sector;
	private String industry;
	private String summaryUrl;
	private Date creationDate;
	private Date modifiedDate;
	
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getIpoYear() {
		return ipoYear;
	}
	public void setIpoYear(int ipoYear) {
		this.ipoYear = ipoYear;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getSummaryUrl() {
		return summaryUrl;
	}
	public void setSummaryUrl(String summaryUrl) {
		this.summaryUrl = summaryUrl;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ticker [ticker=");
		builder.append(ticker);
		builder.append(", country=");
		builder.append(country);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", ipoYear=");
		builder.append(ipoYear);
		builder.append(", sector=");
		builder.append(sector);
		builder.append(", industry=");
		builder.append(industry);
		builder.append(", summaryUrl=");
		builder.append(summaryUrl);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", modifiedDate=");
		builder.append(modifiedDate);
		builder.append("]");
		return builder.toString();
	}
	
}
