package com.retis.forecast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;

import android.os.AsyncTask;
public class ReceiveForecasts extends AsyncTask<String, Integer, Integer> {
		protected void onProgressUpdate(Integer integers) {
		}
		
		@Override
		protected Integer doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
		protected  void Cache(String... str) throws FileNotFoundException{
			String cacheDir = str[0];
			File downloadingMediaFile = new File(cacheDir, "downloadingMedia.dat");
			FileOutputStream out = new FileOutputStream(downloadingMediaFile); 
			byte buf[] = new byte[16384];
			//URLConnection cn = new URL(mediaUrl).openConnection(); 
			//cn.connect(); 
			//InputStream stream = cn.getInputStream();
			/*do {
				int numread = stream.read(buf); 
				if (numread <= 0) break; 
				out.write(buf, 0, numread);
			// ... 
			} while (...);*/
			
			
		} 
		

}
