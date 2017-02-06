package com.sample.finance.dto;

import java.util.HashMap;
import java.util.Map;

public class StockDetailsResult {

	private int totalStocks;
	private int successfulInserts;
	private Map<Stock, Exception> failedInserts = new HashMap<Stock, Exception>();
	
	public int getTotalStocks() {
		return totalStocks;
	}
	public void setTotalStocks(int totalStocks) {
		this.totalStocks = totalStocks;
	}
	public int getSuccessfulInserts() {
		return successfulInserts;
	}
	public void addSuccessfulInserts() {
		this.successfulInserts++;
	}
	public Map<Stock, Exception> getFailedInserts() {
		return failedInserts;
	}
	public void addFailedInserts(Stock failedInsert, Exception exception) {
		this.failedInserts.put(failedInsert, exception);
	}
	
	public String getFailedInsertsList() {
		
		StringBuffer sb = new StringBuffer();
		if(failedInserts.size() > 1){
			for(Stock stock :  failedInserts.keySet()){
				Exception exception = failedInserts.get(stock);
				sb.append(stock.getTicker()).append(" : ").append(exception.getMessage()).append("\n");
			}
		}
		return sb.toString();
	}
}
