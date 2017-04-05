package com.sample.finance.dto;

import java.util.List;

public class Data {

	public Data(List<Stock> data) {
		super();
		this.data = data;
	}

	private List<Stock> data;

	public List<Stock> getData() {
		return data;
	}

	public void setGainers(List<Stock> data) {
		this.data = data;
	}
	
	
}
