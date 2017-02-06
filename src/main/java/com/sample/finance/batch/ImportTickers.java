
package com.sample.finance.batch;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;

import com.sample.finance.dao.DBConnection;

public class ImportTickers {
	
	public static void load(String filesLocationBasePath){

		System.out.println("FolderPath : " + filesLocationBasePath);
		File folder = new File(filesLocationBasePath);
		File[] files = folder.listFiles();
		for(File file : files){
			if(file.isFile()){
				try{
					System.out.println(file.getAbsoluteFile());
					FileInputStream fstream = new FileInputStream(file);
					DataInputStream in = new DataInputStream(fstream);
			        BufferedReader br = new BufferedReader(new InputStreamReader(in));
			        String strLine;
			        int lineNumber=0;
			        int skippedLines=0;
			        Connection conn = DBConnection.getConnection();
					if(conn == null){
						throw new Exception("Problem Getting a Connection");
					}
			        Statement statement = conn.createStatement();
			        while ((strLine = br.readLine()) != null)   {	
			        	strLine = strLine.trim();
		        		if(strLine.length() > 0 ){
				        	lineNumber++;
			        		String query = "insert into tickers(ticker, stockexchange)values(" + strLine + ");";
			        		statement.addBatch(query);
			        		if(lineNumber %100 == 0){
			        			System.out.println(lineNumber);
			        			statement.executeBatch();
			        		}		
		        		}else{
		        			skippedLines++;
		        		}
		        		
			        }
			        statement.executeBatch();
			        in.close();	
			        System.out.println ("Inserted Lines : " + lineNumber);
			        System.out.println ("Skipped Lines : " + skippedLines);
			        try{
						if(statement != null){
							statement.close();
						}
						if(conn != null){
							conn.close();
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String args[]){
		
		String filesLocationBasePath = "/Users/muralipadavala/Downloads/files";
		load(filesLocationBasePath);
	}
}
