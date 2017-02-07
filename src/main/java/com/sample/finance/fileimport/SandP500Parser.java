package com.sample.finance.fileimport;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class SandP500Parser {

	private static final String urlStr = "https://en.wikipedia.org/wiki/List_of_S%26P_500_companies";
	
	private static void load(){
		int counter = 0;
		try{
			URL url = new URL(urlStr);
			URLConnection urlConn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			String line;
			int flag = 0;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
				if(flag > 0){
					++flag;
					if(flag == 4){
						line = line.replace("<td>", "");
						line = line.replace("</td>", "");
						System.out.println("\"" + line + "\"");
						flag = 0;
					}
				}
				if(line.contains("http://www.nyse.com") || line.contains("http://www.nasdaq.com")){
					line = line.replace("</a></td>", "");
					line = line.substring(line.lastIndexOf(">")+1);
					System.out.print("\"" +line + "\" ,");
					++flag;
					++counter;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		System.out.println("Number of Stocks fetched : " + counter);
	}
	
	public static void main(String args[]){
		load();
	}
}
