package com.appspot.senseampapp.senseamp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Context;
import android.os.Vibrator;
import android.content.Intent;

public class HomeActivity extends Activity {
	private String2Vibrations str2vib = new String2Vibrations();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	public void onClickWriteByMe(View v) {
		// Start write by me activity
        Intent i = new Intent(HomeActivity.this, SenseActivity.class);
        startActivity(i);		
		
		// Call vibration pattern
		long[] pattern = {0, 50, 150, 50, 150, 50, 150, 50};
		Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		vib.vibrate(pattern, -1);
	}	
	
	public void onClickWriteByFriend(View v) {
		// Call vibration pattern
		long[] pattern = {0, 50, 150, 50, 150, 50};
		Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		vib.vibrate(pattern, -1);
		
		// Start write by friend activity
        Intent i = new Intent(HomeActivity.this, WriteByFriendActivity.class);
        startActivity(i);

        // close this activity
        finish();
	}	
	
	public void onClickPersonalize(View v) {
		// Call vibration pattern
		long[] pattern = {0, 50};
		Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		vib.vibrate(pattern, -1);
	}	
	
	public void onClickBooks(View v) {
		// Call vibration pattern
		long[] pattern = {0, 50, 150, 50};
		Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		vib.vibrate(pattern, -1);
	}	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
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
