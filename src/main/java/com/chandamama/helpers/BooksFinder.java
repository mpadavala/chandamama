package com.chandamama.helpers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.chandamama.enums.LanguageEnum;
import com.chandamama.util.Util;

public class BooksFinder {

	public void getBooksToDownload(String localFolder, LanguageEnum language) {

		LocalFilesProvider localFilesProvider = new LocalFilesProvider(localFolder);

		List<String> localFilesList = localFilesProvider.getLocalFiles();
		
		System.out.println("Local Files located at : " + localFolder);
		System.out.println("============================================");
		Util.printList(localFilesList);

		RemoteFilesProvider rfp = new RemoteFilesProvider();

		List<String> htmlPages = rfp.getRemotePages(language.getLanguage());
		System.out.println("Remote Pages : ");
		System.out.println("============================================");
		Util.printList(htmlPages);

		Map<String, String> pdfFilesMap = rfp.getPdfFiles(htmlPages);
		System.out.println("Remote PdfFiles : ");
		System.out.println("============================================");
		Util.printMap(pdfFilesMap);

		removeAlreadyDownloadedFiles(localFilesList, pdfFilesMap);
		
		System.out.println("Remote PdfFiles to download : ");
		System.out.println("============================================");
		Util.printMap(pdfFilesMap);
		
		Downloader bookDownloader = new Downloader();
		
		try {
			bookDownloader.downloadPdfs(pdfFilesMap, localFolder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void removeAlreadyDownloadedFiles(List<String> list, Map<String, String> map){
		System.out.println("============================================");
		
		for(String item : list){
			map.remove(item);
			System.out.println("Removed book : " + item);
		}
	}

}
