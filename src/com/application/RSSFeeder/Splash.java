package com.application.RSSFeeder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class Splash extends Activity {
	protected boolean _active = true;
	protected int _splashTime = 3000;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Molot.otf");
        TextView tv = (TextView) findViewById(R.id.textView1);
        tv.setTypeface(tf);
  	    startService(new Intent(Splash.this,RSSFeederService.class));

        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                        sleep(100);
                        if(_active) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
                  
                    startActivity(new Intent(Splash.this,Act1.class));
                    interrupt();
                }
            }
        };
        splashTread.start();
        
    }
}