package com.chandamama.app;

import com.chandamama.enums.LanguageEnum;
import com.chandamama.helpers.BooksFinder;

public class ChandamamaApp {

	
	public static void main(String[] args) {
		
		LanguageEnum languageEnum = null;
		
		if(args.length !=2){
			printUsage();
		}else{
			String language = args[1];
			languageEnum = LanguageEnum.getEnum(language.toLowerCase());
		}
		
		if(languageEnum == null ||  args[0].length()==0){
			printUsage();
		}else{
			String localFolder =  args[0];
			System.out.println("Local Folder : " + localFolder);
			System.out.println("Language     : " + languageEnum.getLanguage());
			 BooksFinder booksFinder = new  BooksFinder();
			 booksFinder.getBooksToDownload(localFolder, languageEnum);
		}
		
	}
	
	private static void printUsage(){
		System.out.println("Usage : " );
		System.out.println("First Argument : is the local folder where you want to download the files");
		System.out.println("Second Argument : is the Language of the books to download");
		System.out.println("Example  : /Users/abc/chandamama-english english");
		System.exit(0);
	}
}
