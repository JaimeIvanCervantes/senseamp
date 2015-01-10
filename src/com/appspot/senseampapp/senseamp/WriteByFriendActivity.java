package com.appspot.senseampapp.senseamp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class WriteByFriendActivity extends Activity {
	private Vibrator vib;
	private int mode = 0; // 0 for Ready, 1 for Paused, 2 for Playing
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Remove action bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// Initialization
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write_by_friend);
		
	}

	public void onClickHome(View v) {
		// Call vibration pattern
		long[] pattern = {0, 50};
		vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		vib.vibrate(pattern, -1);
		
		// Start write by me activity
        Intent i = new Intent(WriteByFriendActivity.this, HomeActivity.class);
        startActivity(i);

        // close this activity
        finish();
	}
	
	public void onClickPlayPause(View v) {
		ImageButton playPauseButton = (ImageButton)findViewById(R.id.playPause);
		long[] pattern = {0, 50, 150, 50};
		long[] pausePattern = {0,50,150,50};
		String stringToPlay;
		EditText inputText;
		
		if (mode == 0 || mode == 1) {
			// Change mode to Playing
			mode = 2;
			
			// Change image resource to play
			playPauseButton.setImageResource(R.drawable.pause);	
			
			// Get pattern from edit text
			inputText = (EditText) findViewById(R.id.textToPlay);
			stringToPlay = inputText.getText().toString();
		
			// Get instance of Vibrator from current Context
			vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			pattern = String2Vibrations.getVibrations(stringToPlay);
			vib.vibrate(pattern, -1);
				
		} else if (mode == 2) {
			// Change mode to Paused
			mode = 1;
			
			// Cancel vibration
			vib.cancel();
			vib.vibrate(pausePattern, -1);
			
			// Change image resource to play
			playPauseButton.setImageResource(R.drawable.play);			
			
			
		}
	}
	

	@Override
	public void onPause() {
       super.onPause();
       vib.cancel();
       	
    }
	
	@Override
    public void onStop() {
       super.onStop();
       vib.cancel();	       
    }
    
    public void onDestroy() {
       super.onDestroy();
       vib.cancel();	       
    }	
}
