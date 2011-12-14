package com.retis.forecast;

import com.retis.forecast.MainActivity.Forecasts;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

public class ForecastAdapter extends SimpleCursorAdapter {
  static final String[] FROM = { DbHelper.C_CITY, DbHelper.C_DATE,
      DbHelper.C_FORECAST, DbHelper.C_SOURCE, DbHelper.C_TIME, DbHelper.C_EXTFORECAST };
  static final int[] TO = { R.id.textCity, R.id.textDate, R.id.textForecast,
	  R.id.textSource, R.id.textTime, R.id.textExtended };
  
 
  // Constructor
  public ForecastAdapter(Context context, Cursor c) {
    super(context, R.layout.row, c, FROM, TO);
  }

  // This is where the actual binding of a cursor to view happens
  @Override
  public void bindView(View row, Context context, Cursor cursor) {
    super.bindView(row, context, cursor);

    // Manually bind forecast to its imageView
    String forecast = cursor.getString(cursor
        .getColumnIndex(DbHelper.C_FORECAST));
    ImageView imageForecast = (ImageView) row.findViewById(R.id.imageForecast);
    switch (Forecasts.valueOf(forecast)) {
	case SUNNY:
		imageForecast.setImageResource(R.drawable.sunny);
		break;
	case CLOUDY:
		imageForecast.setImageResource(R.drawable.cloudy);
		break;
	case FOGGY:
		imageForecast.setImageResource(R.drawable.fog);
		break;

	default:
		break;
	}
  }
  
}
