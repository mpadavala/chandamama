package com.sample.store.enums;

public enum Size {
	
	EXTRASMALL("XS", "Extra Small"),
	SMALL("S", "Small"),
	MEDIUM("M", "Medium"),
	LARGE("L", "Large"),
	EXTRALARGE("XL", "Extra Large");

	private final String sizeCode;
	private final String description;

	private Size(String sizeCode, String description){
		this.sizeCode = sizeCode;
		this.description = description;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public String getDescription() {
		return description;
	}
	
};
