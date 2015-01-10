package com.appspot.senseampapp.senseamp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.Window;
 
public class SplashActivity extends Activity {
 
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		// Remove action bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);		    	
    	
		// Activity initialization
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
    	// Vibrate
    	Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    	v.vibrate(SPLASH_TIME_OUT);
        
        new Handler().postDelayed(new Runnable() {
 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {            	
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
 
}