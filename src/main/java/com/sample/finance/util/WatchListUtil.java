package com.sample.finance.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sample.finance.dto.Stock;

public class WatchListUtil {

	private static final int MILLION = 1000000;
	
	public static String getHtml(List<Stock> stockList) {
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		
		String DATE_FORMAT = "MM_dd_yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String today = sdf.format(now);
		
		NumberFormat formatter = new DecimalFormat("#.#");

		StringBuffer sb = new StringBuffer();
		
		try {
			
			
			/*WatchListDO wldo = new WatchListDO();
			List<String>  todayList = wldo.getAll();
			*/
			
			/*
			symbol,
			comapnyname, 
			marketcap/1000000 Marketcap_Million, 
			totalvolume/1000000 volume_Million, 
			lasttrade, 
			gainorloss, 
			percentgainorloss, 
			dayshigh, 
			dayslow,
			fiftytwoweekhigh,
			fiftytwoweeklow
			TradeDate*/


			
			
			if(stockList != null){
				Iterator<Stock> iterator = stockList.iterator();
				sb.append("<table border=\"1\">");
				
				sb.append("<tr>");
				
				sb.append("<td>");
				sb.append("No.");
				sb.append("</td>");
				
				sb.append("<td>");
				sb.append("Ticker");
				sb.append("</td>");
				
				sb.append("<td>");
				sb.append("Company Name");
				sb.append("</td>");
									 
				sb.append("<td>");
				sb.append("MarketCap(MILLIONS)");
				sb.append("</td>");

				sb.append("<td>");
				sb.append("Volume(MILLIONS)");
				sb.append("</td>");
				
				sb.append("<td>");
				sb.append("LastTrade");
				sb.append("</td>");
									 
				sb.append("<td>");
				sb.append("GainOrLoss");
				sb.append("</td>");

				sb.append("<td>");
				sb.append("GainOrLoss %");
				sb.append("</td>");

				sb.append("<td>");
				sb.append("DaysHigh");
				sb.append("</td>");
									 
				sb.append("<td>");
				sb.append("DaysLow");
				sb.append("</td>");

				sb.append("<td>");
				sb.append("52 WeekHigh");
				sb.append("</td>");

				sb.append("<td>");
				sb.append("52 WeekLow");
				sb.append("</td>");
				
				sb.append("<td>");
				sb.append("Trade Date");
				sb.append("</td>");
				
				sb.append("</tr>");
				int i =0;
				while(iterator.hasNext()){
					Stock stock = iterator.next();
					sb.append("<tr>");
					
					sb.append("<td>");
					sb.append(String.valueOf(++i));
					sb.append("</td>");
					
					sb.append("<td>");
					sb.append("<a href=\"http://www.google.com/finance?q=" + stock.getTicker() + "\">"+ stock.getTicker() +"</a>");
					sb.append("</td>");
					
					sb.append("<td>");
					sb.append("<a href=\"http://www.google.com/finance?q=" + stock.getTicker() + "\">"+ stock.getCompanyName() +"</a>");
					sb.append("</td>");
										 
					sb.append("<td>");
					sb.append(String.valueOf(formatter.format(stock.getMarketCap()/MILLION))+"M");
					sb.append("</td>");

					sb.append("<td>");
					sb.append(String.valueOf(formatter.format(stock.getTotalVolume()/MILLION))+"M");
					sb.append("</td>");
					
					sb.append("<td>");
					sb.append("$" + String.valueOf(stock.getLastTrade()));
					sb.append("</td>");
										 
					sb.append("<td>");
					sb.append("$" + String.valueOf(stock.getGainOrLoss()));
					sb.append("</td>");

					sb.append("<td>");
					sb.append(String.valueOf(stock.getPercentGainOrLoss())+ "%");
					sb.append("</td>");

					sb.append("<td>");
					sb.append("$" + String.valueOf(stock.getDaysHigh()));
					sb.append("</td>");
										 
					sb.append("<td>");
					sb.append("$" + String.valueOf(stock.getDaysLow()));
					sb.append("</td>");

					sb.append("<td>");
					sb.append("$" + String.valueOf(stock.getFiftyTwoWeekHigh()));
					sb.append("</td>");

					sb.append("<td>");
					sb.append("$" + String.valueOf(stock.getFiftyTwoWeekLow()));
					sb.append("</td>");
					
					sb.append("<td>");
					sb.append(sdf.format(stock.getTradeDate()));
					sb.append("</td>");

					sb.append("</tr>");
				}
				sb.append("</table>");
			}
			System.out.println("The data has been written");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
}
