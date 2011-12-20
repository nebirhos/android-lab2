package com.retis.forecast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import android.content.Context;
import android.os.AsyncTask;
public class ReceiveForecasts extends AsyncTask<File, Integer, Integer> {
		protected void onProgressUpdate(Integer integers) {
		}
		
		@Override
		protected Integer doInBackground(File... params) {
			// TODO Auto-generated method stub
	        ParserHelper test = new ParserHelper(params[0]);
			return null;
		}
		
		@Override
		protected void onPostExecute(Integer x){
		//	Context asd=getApplicationContext();
		}
		
				

}
