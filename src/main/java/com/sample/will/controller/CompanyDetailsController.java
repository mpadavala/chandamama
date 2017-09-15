package com.sample.will.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompanyDetailsController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			//URL url = new URL("http://www.google.com/finance?q=NYSE:WMT");
			URL url = new URL("http://www.google.com/finance?q=NASDAQ:JCP");
			String startingStr = "rows:[{id:";
			String endingStr = "],visible_cols:";
			
			URLConnection urlConn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			String line;
			boolean flag = false;
			while ((line = in.readLine()) != null) {
				if(line.startsWith("google.finance.data = ")){
					String relatedCompanies = line.substring(line.indexOf(startingStr), line.indexOf(endingStr));
					
					Pattern p = Pattern.compile("id:*values:");
					Matcher m = p.matcher(relatedCompanies);
					String str = m.replaceAll("");

					System.out.println(relatedCompanies);
					System.out.println(str);
				}
				if(line.equals("<div class=companySummary>")){
					flag = true;
					line = in.readLine(); 
				}
				if(flag){
					System.out.println(line);
					flag=false;
				}				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
