package com.retis.forecast;

import com.retis.forecast.ForecastAdapter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListAdapter;
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
	private final String[] sources = { "meteo.it", "meteo.it", "meteo.it", "meteo.it" };
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
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
		
		/*String city, forecast, date, time;
		while (cursor.moveToNext()) {
			city = cursor.getString(cursor.getColumnIndex(DbHelper.C_CITY));
			forecast = cursor.getString(cursor.getColumnIndex(DbHelper.C_FORECAST));
			date = cursor.getString(cursor.getColumnIndex(DbHelper.C_DATE));
			time = cursor.getString(cursor.getColumnIndex(DbHelper.C_TIME));
			String output = String.format("%s [%s %s]: %s\n", city, date, time, forecast);
			textForecast.append(output);
		}*/
		
		// Create the adapter
	    adapter = new ForecastAdapter(this, cursor);
	    listener = new ForecastListener(this);
	    listForecast.setAdapter(adapter);
	    listForecast.setOnItemClickListener(listener);
		
	}
	
	
}