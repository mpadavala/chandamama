package com.chandamama.enums;

import java.util.HashMap;
import java.util.Map;

public enum LanguageEnum {

	ENGLISH("english"), 
	TELUGU("telugu"), 
	HINDI("hindi"), 
	SANSKRIT("sanskrit");

	private String language;

	private LanguageEnum(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}
	
	public static LanguageEnum getEnum(String language){
		return map.get(language.trim());
	}
	
	private static Map<String, LanguageEnum> map = new HashMap<String, LanguageEnum>();
    
    static{
    	 for (LanguageEnum LanguageEnum : LanguageEnum.values()) {
             map.put(LanguageEnum.getLanguage(), LanguageEnum);
         }
    }
}