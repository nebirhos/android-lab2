package com.retis.forecast;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper{
	static final String TAG = "DbHelper";
	static final String DB_NAME = "forecasts.db";
	static final int DB_VERSION = 6;
	static final String TABLE ="forecasts";
	static final String C_ID = BaseColumns._ID;
	static final String C_CITY = "city";
	static final String C_SOURCE = "source";
	static final String C_DATE = "date";
	static final String C_TIME = "time";
	static final String C_FORECAST = "forecast";
	static final String C_EXTFORECAST = "extforecast";
	Context context;

	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table " + TABLE + " (" + C_ID + " int primary key, "
				+ C_CITY + " text, " + C_SOURCE + " text, " + C_DATE + " text, "
				+ C_TIME + " text, " + C_FORECAST + " text, " + C_EXTFORECAST +
				" text)";
		
		db.execSQL(sql);
		
		Log.d(TAG, "onCreated sql: " + sql);
	}

	// Called whenever newVersion != oldVersion
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists " + TABLE);
		Log.d(TAG, "onUpdated");
		onCreate(db);
	}
}
