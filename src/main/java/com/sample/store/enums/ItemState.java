package com.sample.store.enums;

public enum ItemState {

	ACTIVE("ACTIVE", "Active"),
	DRAFT("DRAFT", "Draft"),
	DELETED("DELETED", "Deleted");

	private final String itemStateCode;
	private final String itemStateDescription;

	private ItemState(String itemStateCode, String itemStateDescription){
		this.itemStateCode = itemStateCode;
		this.itemStateDescription = itemStateDescription;
	}

	public String getItemStateCode() {
		return itemStateCode;
	}

	public String getItemStateDescription() {
		return itemStateDescription;
	}

}
