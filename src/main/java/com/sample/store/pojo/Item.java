package com.sample.store.pojo;

import java.util.Set;

import com.sample.store.enums.ItemState;
import com.sample.store.enums.Size;

public class Item {

	private long itemId;
	private String title;
	private String description;
	private Set<Category> categories;
	private Price price;
	private ItemState itemSate;
	
	private Set<String> images;
	private Set<Size> sizes;
	private Set<String> colors;

	//For which age groups, this item is related
	private Set<String> ageGroups;

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public ItemState getItemSate() {
		return itemSate;
	}

	public void setItemSate(ItemState itemSate) {
		this.itemSate = itemSate;
	}

	public Set<String> getImages() {
		return images;
	}

	public void setImages(Set<String> images) {
		this.images = images;
	}

	public Set<Size> getSizes() {
		return sizes;
	}

	public void setSizes(Set<Size> sizes) {
		this.sizes = sizes;
	}

	public Set<String> getColors() {
		return colors;
	}

	public void setColors(Set<String> colors) {
		this.colors = colors;
	}

	public Set<String> getAgeGroups() {
		return ageGroups;
	}

	public void setAgeGroups(Set<String> ageGroups) {
		this.ageGroups = ageGroups;
	}

	
	
}
