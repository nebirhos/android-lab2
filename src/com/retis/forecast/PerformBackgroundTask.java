package com.retis.forecast;

import android.os.AsyncTask;

public class PerformBackgroundTask extends AsyncTask<Integer, Integer, Integer>

{

	@Override
	protected Integer doInBackground(Integer... arg0) {
		// TODO Auto-generated method stub
		/* Intent serviceIntent = new Intent(PerformBackgroundTask.this,ExtraInfos.class);
			startService(serviceIntent);*/  
		 int check_point = 1;
		   publishProgress(check_point);
		   return check_point;
	 
	
	}


		protected void onProgressUpdate(Integer integers) {
		  if(integers == 1) {
			 	
		    
		}
	}

}
