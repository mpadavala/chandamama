package com.chandamama.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Downloader {

	//private final ExecutorService executor = Executors.newCachedThreadPool();
	private final ExecutorService executor = Executors.newFixedThreadPool(5);
	
	public void downloadPdfs(Map<String, String> map, String localFolder) throws IOException {
		
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
		    Entry<String, String> entry = iter.next();
		    String key = entry.getKey();
		    String destinationFile = localFolder + File.separator + key;
		    String targetUrl = entry.getValue();
		    download(targetUrl, destinationFile);
		}
	}

	private void download(final String targetUrl, String destinationFile) throws IOException {
		
		URL url = new URL(targetUrl);
		File destination = new File(destinationFile);
		this.executor.submit(new DownloadCallable(url, destination));
	}

	private static final class DownloadCallable implements Callable<File> {

		private final URL targetUrl;
		private final File destination;

		public DownloadCallable(final URL targetUrl, final File destination) {
			this.targetUrl = targetUrl;
			Objects.requireNonNull(targetUrl);

			this.destination = destination;
			Objects.requireNonNull(destination);
		}

		@Override
		public File call() throws IOException {
			
			System.out.println("Downloading : " + this.destination);
			final URLConnection request = this.targetUrl.openConnection();
			try (final InputStream inputStream = request.getInputStream();
					final FileOutputStream outputStream = new FileOutputStream(this.destination);) {

				final byte[] data = new byte[4096];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(data)) != -1) {
					outputStream.write(data, 0, bytesRead);
				}
				outputStream.close();
				inputStream.close();
			}
			
			System.out.println("Done Downloading : " + this.destination);
			
			return this.destination;
		}
	}
}