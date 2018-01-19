package com.chandamama.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Util {

	public static void printList(List<String> filesList){
		for(String item : filesList){
			System.out.println(item);
		}
	}
	
	public static void printMap(Map<String, String> map){
		
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
		    Entry<String, String> entry = iter.next();
		    System.out.println(entry.getKey() + " = " + entry.getValue());
		}
	}
	
}
