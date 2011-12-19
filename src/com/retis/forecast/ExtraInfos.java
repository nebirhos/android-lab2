package com.retis.forecast;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * FirstService:
 * First interaction with the Service component.
 * Be aware of callbacks execution looking at the on screen Toasts.
 * Pay attention at when the onDestroy() is called.
 *
 * @author Alberto Panizzo <alberto AT amarulasolutions DOT com>
 *
 */

public class ExtraInfos extends Service {

	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "Called Service class onCreate()",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Toast.makeText(this, "Called Service class onStart()",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "Called Service onDestroy()",
				Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		Toast.makeText(this, "Called Service onBind()",
						Toast.LENGTH_SHORT).show();
		return null;
	}

}