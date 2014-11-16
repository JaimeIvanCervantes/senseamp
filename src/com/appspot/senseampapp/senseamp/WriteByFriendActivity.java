package com.appspot.senseampapp.senseamp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Context;
import android.os.Vibrator;
import android.content.Intent;
import android.widget.EditText;

public class WriteByFriendActivity extends Activity {
	private String2Vibrations str2vib = new String2Vibrations();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write_by_friend);
	}

	public void onClickHome(View v) {
		// Call vibration pattern
		long[] pattern = {0, 50};
		Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		vib.vibrate(pattern, -1);
		
		// Start write by me activity
        Intent i = new Intent(WriteByFriendActivity.this, HomeActivity.class);
        startActivity(i);

        // close this activity
        finish();
	}
	
	public void onClickPlay(View v) {
		long[] pattern = {0, 50, 150, 50};
		String stringToPlay;
		EditText inputText;
		Vibrator vib;
		
		// Call vibration pattern (conflicts with string to play)
		//vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		//vib.vibrate(pattern, -1);
		
		// Get pattern from edit text
		inputText = (EditText) findViewById(R.id.textToPlay);
		stringToPlay = inputText.getText().toString();
		
		// Get instance of Vibrator from current Context
		vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		pattern = str2vib.getVibrations(stringToPlay);
		vib.vibrate(pattern, -1);		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.write_by_friend, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
