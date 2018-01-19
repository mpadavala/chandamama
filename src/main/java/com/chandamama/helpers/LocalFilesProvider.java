package com.chandamama.helpers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LocalFilesProvider {
	
	/**
	 * This Class Lists out only the files. It excludes any folders and hidden files
	 */
	
	private final String localFilesFolder;

	public LocalFilesProvider(String localFilesFolder) {
		super();
		this.localFilesFolder = localFilesFolder;
	}

	public List<String> getLocalFiles(){
		
		List<String> filesList = new ArrayList<String>();
		
		File folder = new File(localFilesFolder);
		File[] listOfFiles = folder.listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile() && !listOfFiles[i].getName().startsWith(".")) {
	       filesList.add(listOfFiles[i].getName());
	      }
	    }
	    return filesList;
	}

}
