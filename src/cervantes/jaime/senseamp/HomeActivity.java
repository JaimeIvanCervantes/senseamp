package cervantes.jaime.senseamp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.content.Context;
import android.os.Vibrator;
import android.content.Intent;

public class HomeActivity extends Activity {
	private String2Vibrations str2vib = new String2Vibrations();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Remove action bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	public void onClickWriteByMe(View v) {		
		// Call vibration pattern
		long[] pattern = {0, 50, 150, 50, 150, 50}; 
		Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		vib.vibrate(pattern, -1);
		
		// Start write by me activity
        Intent i = new Intent(HomeActivity.this, SenseActivity.class);
        startActivity(i);
        
        // close this activity
        finish();
	}	
	
	public void onClickWriteByFriend(View v) {
		// Call vibration pattern
		long[] pattern = {0, 50, 150, 50, 150, 50, 150, 50};
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
		
		// Start personalize activity
        Intent i = new Intent(HomeActivity.this, SettingsActivity.class);
        startActivity(i);	
        
        // close this activity
        finish();
	}	
	
	public void onClickBooks(View v) {
		// Call vibration pattern
		long[] pattern = {0, 50, 150, 50};
		Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		vib.vibrate(pattern, -1);
		
		// Start write by friend activity
        Intent i = new Intent(HomeActivity.this, BooksActivity.class);
        startActivity(i);

        // close this activity
        finish();
	}	
	
	
	@Override
	public void onPause() {
		super.onPause();
        this.finish();
       
    }
	
	@Override
    public void onStop() {
		super.onStop();
		this.finish();
       
    }
    
	@Override
    public void onDestroy() {
		super.onDestroy();      
    }	
}
