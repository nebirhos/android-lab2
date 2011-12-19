package com.retis.forecast;

import java.util.Timer;
import java.util.TimerTask;

import com.retis.forecast.ForecastAdapter;
import com.retis.forecast.ParserHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;


public class MainActivity extends Activity {
	ListView listForecast;
	DbHelper dbHelper;
	SQLiteDatabase db;
	Cursor cursor;
	ForecastAdapter adapter;
	ForecastListener listener;
	
	public enum Forecasts {
		SUNNY, CLOUDY, FOGGY
	}
	
	private final String[] cities = { "Pisa", "Pisa", "Bologna", "Torino" };
	private final String[] sources = { "google.com", "google.com", "google.com", "google.com" };
	private final String[] dates = { "30/11/11", "30/11/11", "01/12/11", "02/12/11" };
	private final String[] times = { "11:30", "11:30", "12:30", "18:30" };
	private final Forecasts[] forecasts = { Forecasts.SUNNY , Forecasts.CLOUDY,
			Forecasts.FOGGY, Forecasts.SUNNY };
	private final String[] extForecasts = { "blablalbla", "ijsoiajo", "apokspaosk",
			"pokspaok"};
	
	private void dbFill(SQLiteDatabase db) {
		ContentValues values = new ContentValues();
		int i = 0;
		for (String city : cities) {
			values.clear();
			values.put(DbHelper.C_CITY, city);
			values.put(DbHelper.C_DATE, dates[i]);
			values.put(DbHelper.C_SOURCE, sources[i]);
			values.put(DbHelper.C_ID, i);
			values.put(DbHelper.C_TIME, times[i]);
			values.put(DbHelper.C_FORECAST, forecasts[i].name());
			values.put(DbHelper.C_EXTFORECAST, extForecasts[i]);
			i++;
			db.insertWithOnConflict(DbHelper.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		}
		
		
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        ParserHelper test = new ParserHelper();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        StartTask();
        listForecast = (ListView) findViewById(R.id.listForecast);
        
        dbHelper = new DbHelper(this);
        dbFill(dbHelper.getWritableDatabase());
        db = dbHelper.getReadableDatabase();
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		db.close();
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		cursor = db.query(DbHelper.TABLE, null, null,
				null, null, null, DbHelper.C_DATE + " DESC");
		startManagingCursor(cursor);
		// Create the adapter
	    adapter = new ForecastAdapter(this, cursor);
	    listener = new ForecastListener(this);
	    listForecast.setAdapter(adapter);
	    listForecast.setOnItemClickListener(listener);
		
	}
	
	public void StartTask(){
		TimerTask doAsynchronousTask;
		final Handler handler = new Handler();
		Timer timer = new Timer();
		doAsynchronousTask = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.post(new Runnable() {
					public void run() {
						try {
							/* PerformBackgroundTask performBackgroundTask = new PerformBackgroundTask();
							 * PerformBackgroundTask this class is the class that extends AsynchTask 
							 * performBackgroundTask.execute();*/
						} catch (Exception e) {}
					}
				});
			}
		};
		/**/
		timer.schedule(doAsynchronousTask, 0,3600000);//execute in every 50000 ms
	}
}