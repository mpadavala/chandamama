package com.sample.store.pojo;

public class Category {

	private String categoryId;
	private String categoryName;
	private String parentCategory;
	private String categoryDescryption;
	
	
	public Category(String categoryId, String categoryName, String parentCategory, String categoryDescryption) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.parentCategory = parentCategory;
		this.categoryDescryption = categoryDescryption;
	}
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}
	public String getCategoryDescryption() {
		return categoryDescryption;
	}
	public void setCategoryDescryption(String categoryDescryption) {
		this.categoryDescryption = categoryDescryption;
	}
	
	
	
}
