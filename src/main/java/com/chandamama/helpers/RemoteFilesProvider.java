package com.chandamama.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteFilesProvider {

	private static final String baseURL = "http://chandamama.in";
	public static final String ENGLISH = "english";
	public static final String TELUGU = "telugu";

	private static final String firstStr = "<a href=";
	private static final String secondStr = ".html";
	private static final String pdfStr = ".pdf";
	private static final String QUOTE = "\"";
	private static final String SLASH = "/";

	public List<String> getRemotePages(String language) {

		List<String> htmlPages = new ArrayList<String>();
		try {
			String strURL = baseURL + File.separator + language;
			URL url = new URL(strURL);
			URLConnection urlConn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				if (line.contains(firstStr) && line.contains(secondStr)) {
					String parsed = parseToGetHtml(line);
					String htmlPage = strURL + File.separator + parsed;
					htmlPages.add(htmlPage);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htmlPages;
	}

	public Map<String, String> getPdfFiles(List<String> htmlPageList) {

		Map<String, String> pdfFiles = new HashMap<String, String>();

		for (String htmlPage : htmlPageList) {
			try {
				URL url = new URL(htmlPage);
				URLConnection urlConn = url.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					if (line.contains(pdfStr)) {
						String parsedPdf = parseToGetPdfFile(line);
						String pdfFile = baseURL + parsedPdf;
						String key = parseToGetPdfKey(pdfFile);
						pdfFiles.put(key, pdfFile);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pdfFiles;
	}

	public String parseToGetHtml(String line) {
		String parsed[] = (line.split(QUOTE))[1].split("/");
		return parsed[2];
	}

	public String parseToGetPdfFile(String line) {
		return (line.split(QUOTE))[1];
	}

	public String parseToGetPdfKey(String line) {
		int start = line.lastIndexOf(SLASH);
		// System.out.println(line.substring(start+1));
		return line.substring(start + 1);
	}
}
