package com.sample.finance.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class StockUtil {
	
	private static final String GOOGLE_BASE_URL = "https://www.google.com/finance?q={0}%3A";
	//https://www.google.com/finance?q=NYSE%3ACC
		
	private static final String DECIMAL_PATTERN = "#0.##";
	private static final String DEFAULT_START_DATE = "01/01/1980";
	
	public static final String DOW30_INDEX = "DOW30";
	public static final String SANDP500_INDEX = "SANDP500";
	public static final String USA = "USA";
	public static final String INDIA = "INDIA";
	
	public static String getGoogleBaseUrl() {
		return GOOGLE_BASE_URL;
	}
	
	public static DateFormat getTimeFormatter() {
		return new SimpleDateFormat("MM/dd/yyyyh:mma");
	}
	
	public static DateFormat getDateFormatter() {
		return new SimpleDateFormat("MM/dd/yyyy");
	}
	
	
	public static double stringToDouble(String str){

		double value = 0;
		try {
			if(str.equalsIgnoreCase(Constants.NA)){
				return value;
			}
			return Double.valueOf(str);
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			return value;
		} 
	}
	
	public static double stringToDouble_KMBT(String str){
		
		double value = 0;
		try {
			if(str.equalsIgnoreCase(Constants.NA)){
				value = 0;;
			}
			else if(str.endsWith(Constants.K)){
				str = str.replaceAll(Constants.K, Constants.EMPTY_STRING);
				value = Double.valueOf(str) * Constants.THOUSAND;
			}
			else if(str.endsWith(Constants.M)){
				str = str.replaceAll(Constants.M, Constants.EMPTY_STRING);
				value = Double.valueOf(str) * Constants.MILLION;
			}
			else if(str.endsWith(Constants.B)){
				str = str.replaceAll(Constants.B, Constants.EMPTY_STRING);
				value = Double.valueOf(str) * Constants.BILLION;
			}else if(str.endsWith(Constants.T)){
				str = str.replaceAll(Constants.T, Constants.EMPTY_STRING);
				value = Double.valueOf(str) * Constants.TRILLION;
			}
			else{
				value = Double.valueOf(str);
			}
			return value;
			
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			return value;
		} 
	}
	
	public static double stringToDouble_Range(String str, int index){
		
		double value = 0;
		String firstToken = null;
		String secondToken = null;
		try {
			if(str.contains(Constants.NA)){
				return value;
			}
			StringTokenizer st = new StringTokenizer(str, Constants.HYPHEN);
			firstToken = st.nextToken();
			secondToken = st.nextToken();
			if(index == 1){
				value = Double.valueOf(firstToken);
			}
			else{
				value = Double.valueOf(secondToken);
			}
			return value;
			
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			return value;
		} 
	}
	
	public static double stringToDouble_PER(String str){
		
		double value = 0;
		try {
			if(str.equalsIgnoreCase(Constants.NA)){
				return value;
			}
			if(str.endsWith(Constants.PERCENTAGE)){
				str = str.replaceAll(Constants.PERCENTAGE, Constants.EMPTY_STRING);
				value = Double.valueOf(str);
			}
			return value;
			
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			return value;
		} 
	}
	
	public static Date StringToDate(String str){

		Date date = null;
		try {
			if (str != null && str.equalsIgnoreCase(Constants.NA)) {
				DateFormat dateFormatterNA = new SimpleDateFormat(Constants.DATE_FORMAT_COMING_FROM_DATAPROVIDER_FOR_STOCKDATA);
				date = dateFormatterNA.parse(DEFAULT_START_DATE);
				return date;
			}
			DateFormat dateFormatter = new SimpleDateFormat(Constants.DATE_FORMAT_COMING_FROM_DATAPROVIDER_FOR_STOCKDATA);
			date = dateFormatter.parse(str);	
		} catch (Exception e) {
			//e.printStackTrace();
			return date;
		}
		return date;
	}
	
	public static String MarketCapHumanReadable (double value){
		
        if (value > Constants.THOUSAND && value <= Constants.MILLION){
        	return StockUtil.format(value/Constants.THOUSAND) + Constants.K;
        }if (value > Constants.MILLION && value <= Constants.BILLION){
        	return StockUtil.format(value/Constants.MILLION) + Constants.M;
        }if (value > Constants.BILLION && value <= Constants.TRILLION){
        	return StockUtil.format(value/Constants.BILLION) + Constants.B;
        }if(value > Constants.TRILLION){
        	return StockUtil.format(value/Constants.TRILLION) + Constants.T;
        }       
        return value+"";
    }
	
	/**
	 * As of now  below characters are defined as Special Characters
	 * 	1. Single Quotes 
	 *  2. Double Quotes
	 */
	public static String removeSpecialCharacters(String line){
		line = line.replace(Constants.DOUBLE_QUOTE, Constants.EMPTY_STRING);
		line = line.replace(Constants.SINGLE_QUOTE, Constants.EMPTY_STRING);
		return line;
	}
	
	/**
	 * replace N/A  with 0 (Zero) else return the double value of String
	 * @param str
	 * @return
	 */
	public static double stringToDouble_NA(String str){

		double value = 0;
		try {
			if(str.equalsIgnoreCase(Constants.NA)){
				return value;
			}
			return Double.valueOf(str);
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			return value;
		} 
	}
	
	public static double stringToDouble_NA_KMB(String str){
		
		double value = 0;
		try {
			if(str.equalsIgnoreCase(Constants.NA)){
				return value;
			}
			else if(str.endsWith(Constants.K)){
				str = str.replaceAll(Constants.K, Constants.EMPTY_STRING);
				value = Double.valueOf(str) * Constants.THOUSAND;
			}
			else if(str.endsWith(Constants.M)){
				str = str.replaceAll(Constants.M, Constants.EMPTY_STRING);
				value = Double.valueOf(str) * Constants.MILLION;
			}
			else if(str.endsWith(Constants.B)){
				str = str.replaceAll(Constants.B, Constants.EMPTY_STRING);
				value = Double.valueOf(str) * Constants.BILLION;
			}
			else{
				value = Double.valueOf(str);
			}
			return value;
			
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			return value;
		} 
	}
	
	public static double stringToDouble_NA_NA(String str, int index){
		
		double value = 0;
		String firstToken = null;
		String secondToken = null;
		try {
			if(str.equalsIgnoreCase(Constants.NA_NA)){
				return value;
			}
			StringTokenizer st = new StringTokenizer(str, Constants.HYPHEN);
			firstToken = st.nextToken();
			secondToken = st.nextToken();
			if(index == 1){
				value = Double.valueOf(firstToken);
			}
			else{
				value = Double.valueOf(secondToken);
			}
			return value;
			
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			return value;
		} 
	}
	
	public static Date getTradeTimeStamp(String str, String time){
		
		Date date = null;
		if (str != null && str.equalsIgnoreCase(Constants.NA)) {
			return null;
		}
		try {
			if(time != null){
				if(time.equalsIgnoreCase(Constants.NA)){
					return null;
				}
				else{
					date = getTimeFormatter().parse(str+time);
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return date;
		}
		
		return date;
	}
	
	private static String format(double value){
		DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_PATTERN);
		return decimalFormat.format(value);
	}
	
}
