package com.retis.forecast;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class ForecastListener implements OnItemClickListener {
	Context mcontext;
	
	public ForecastListener (Context context) {
		mcontext = context;
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		//Toast.makeText(mcontext, "You clicked " + arg2, Toast.LENGTH_LONG).show();
		TextView info = (TextView) arg1.findViewById(R.id.textExtended);
		int visible = info.getVisibility();
		Log.d("ForecastListener", "visibility = " + visible);
		if (visible != View.VISIBLE) {
			info.setVisibility(View.VISIBLE);
		}
			
		else {
			info.setVisibility(View.GONE);
		}
	}

}
