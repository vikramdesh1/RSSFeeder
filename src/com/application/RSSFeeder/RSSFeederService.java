package com.application.RSSFeeder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class RSSFeederService extends Service {
	protected boolean _active = true;
	protected int _refreshTime = 300000;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Toast.makeText(getApplicationContext(), "RSSFeeder service started!", Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		
		String ns = Context.NOTIFICATION_SERVICE;
		final NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
		int icon = R.drawable.icon;
		CharSequence tickerText = "New posts in RSSFeeder...";
		long when = System.currentTimeMillis();
		final Notification notification = new Notification(icon, tickerText, when);
		notification.defaults |= Notification.DEFAULT_SOUND;

		Context context = getApplicationContext();
		CharSequence contentTitle = "New posts";
		CharSequence contentText = "New content is available in your feeds";
		Intent notificationIntent = new Intent(this, Act1.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		final int HELLO_ID = 1;
		
		Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(_active && (waited < _refreshTime)) 
                    {
                        sleep(100);
                        if(_active) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                    // do nothing
                } 
                finally 
                {
            		mNotificationManager.notify(HELLO_ID, notification);

                }
            }
        };
        splashTread.start();
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(getApplicationContext(), "RSSFeeder service stopped!", Toast.LENGTH_SHORT).show();

	}

	
	
}
