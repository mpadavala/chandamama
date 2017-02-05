package com.sample.store.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.store.dao.ItemDao;
import com.sample.store.enums.ItemState;
import com.sample.store.pojo.Category;
import com.sample.store.pojo.Item;
import com.sample.store.pojo.Price;


@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemDao itemDao;

	
	@RequestMapping(value="/add")
	public Item addItem(){
		
		Item item = new Item();

		item.setTitle("new item title");
		item.setDescription("this is item description");
		
		Set<Category> categories = new HashSet<>();
		Category category = new Category("100", "CategoryName", "25", "categoryDescryption");
		categories.add(category);
		
		item.setCategories(categories);
		
		Set<String> ageGroups = new HashSet<>();
		ageGroups.add("2-5");
		item.setAgeGroups(ageGroups);
		
		Price price = new Price(100.23);
		item.setPrice(price);

		item.setItemSate(ItemState.ACTIVE);
		

		Set<String> colors = new HashSet<>();
		colors.add("Red");
		
		itemDao.add(item);
		return item;
	}
	
	@RequestMapping(value="/get")
	public List<Item> getItems(){
		List<Item> listOfItems = itemDao.getItems();
		return listOfItems;
	}
	
	@RequestMapping(value="/delete/{itemId}")
	public String deleteItem(@PathVariable long itemId){
		int rowsDeleted = itemDao.delete(itemId);
		if(rowsDeleted == 0){
			return "itemId : " + itemId + " doesn't exist";
		}
		return "itemId : " + itemId + " is deleted";
	}
}
