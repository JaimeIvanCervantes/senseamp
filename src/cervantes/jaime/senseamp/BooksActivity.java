package cervantes.jaime.senseamp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.content.Context;

public class BooksActivity extends Activity {	
	private Vibrator vib;
	long[] pattern;
	private ListView filesListView;
	private ArrayAdapter<String> adapter;
	private ArrayList<String> fileNames;
	File files[];
	private int mode = 0; // 0 for Continue, 1 for Ready, 2 for Playing, and 3 for Paused
	
	BufferedReader br = null;
	File path = null;
	
	int lineNumber = 0;
	String stringToPlay;
	String fullFileName = "";
	
	Thread playThread;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Remove action bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// Set content
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_books);
		
		// Set mode
		mode = 0;
				
		// Get instance of Vibrator from current Context
		vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);		
		
		// Find list view
		filesListView = (ListView) findViewById(R.id.list);
		
		// Set new array adapter
		fileNames = new ArrayList<String>();
	    adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_list_view, fileNames);		
		
		// Get external Documents folder with public access
	    path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()); 		
	    if (!path.exists()) Log.e("SENSEAMP", "Download directory not found");
		
	    // Set the data in your ListView
	    filesListView.setAdapter(adapter);
		
		// Go through every file      
		files = path.listFiles();

		if (files.length != 0 && isExternalStorageReadable()) {
			for (int i=0; i < files.length; i++) {
		        // Add the data of your EditText and puts in your array
				if (files[i].getName().endsWith(".txt"))
				fileNames.add(files[i].getName());
		        
		        // Check if adapter has changed
		        adapter.notifyDataSetChanged();				
			}	
		} else {
			Log.e("ERROR", "No files found.");
		}
		
		// Set click files ListView click listener
		filesListView.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				  String fileName = fileNames.get(position);
				  
				  // Reset line number
				  lineNumber = 0;
				  
				  // Change to 
				  ImageButton playPauseButton = (ImageButton)findViewById(R.id.playPause);
				  playPauseButton.setImageResource(R.drawable.play);
				  mode = 1;
				  
				  // Remove ending
				  if (fileName.indexOf(".") > 0) fileName = fileName.substring(0, fileName.lastIndexOf("."));
				  
				  // Vibrate with pattern
				  vib.cancel();
				  pattern = String2Vibrations.getVibrations(fileName);
				  vib.vibrate(pattern, -1);
				  
				  // Get full file name
				  fullFileName = path.toString() + "/" + fileNames.get(position);		
				 
			  }
		}); 
	    
	}
	
	// On click the home button
	public void onClickHome(View v) {
		// Call vibration pattern
		long[] pattern = {0, 50};

		vib.cancel();
		vib.vibrate(pattern, -1);
		
		// Start write by me activity
        Intent i = new Intent(BooksActivity.this, HomeActivity.class);
        startActivity(i);

        // close this activity
        finish();
	}
	
	// On click the the play/pause button
	public void onClickPlayPause(View v) {
		ImageButton playPauseButton = (ImageButton)findViewById(R.id.playPause);
		long[] pausePattern = {0,50,150,50};

		// File list is empty
		if (fileNames.size() == 0) {
			vib.cancel();
			vib.vibrate(pausePattern, -1);			
			
		// If mode Continue, Ready, or Paused
		} else if (mode == 0 || mode == 1 || mode == 3) { 
			// Change image resource to play
			playPauseButton.setImageResource(R.drawable.pause);
			
			// Cancel vibration
			vib.cancel();
			
			// Start thread to play string
			playThread = new Thread() {//new Runnable() {
		    	public void run() {
		    		try {
		    			// Get previous settings
		    			Log.e("senseamp", "Mode: " + mode);
		    			
		    			// Retrieve shared preferences
		    			if (mode == 0) {
			    			SharedPreferences preferences = getSharedPreferences("senseamp", MODE_PRIVATE);
		    				fullFileName = preferences.getString("prevFullFileName", "");
		    				lineNumber = preferences.getInt("prevLineNumber", 0);	
		    				Log.e("senseamp", "Retrieving: " + fullFileName);
		    			}
		    			
		    			// Change mode to Playing
		    			mode = 2;
		    			
		    			// Open file and get stream ready
		    			File file = new File(fullFileName);
		    			br = new BufferedReader(new FileReader(file));
		    					    			
		    			if (lineNumber > 0) {
		    				lineNumber = lineNumber - 1;
		    				for (int i = 0; i < lineNumber; i++) {
		    					br.readLine();
		    				}
		    			}
		    			
		    			// Loop to play each line
		    			while ((stringToPlay = br.readLine()) != null && mode != 3) {
		    				lineNumber++;
		    				pattern = String2Vibrations.getVibrations(stringToPlay);
		    				//vib.vibrate(pattern, -1);
						
		    				long totalTime = 0L; // In milliseconds
						
		    				// Loop through each vibration 
		    				for (int i = 0; i < pattern.length; i = i + 2) {
		    					if (mode == 3) break;
		    					
		    					long[] subPattern = {0, 0};
		    					totalTime = pattern[i] + pattern[i+1];
		    					
		    					//subPattern = Arrays.copyOfRange(pattern, i, 2);
		    					subPattern[0] = pattern[i];
		    					subPattern[1] = pattern[i+1];
			    				vib.vibrate(subPattern, -1);
			    				
			    				SystemClock.sleep(totalTime);
			    				
		    				}
		    				
		    				SystemClock.sleep(String2Vibrations.getTime()*6);

		    				//totalTime += String2Vibrations.getTime()*6;
		    				//SystemClock.sleep(totalTime);
						
		    				//Log.d("BOOKS ACTIVITY", "Playing Line: " + stringToPlay);
		    			}
		    			
		    			// Reset 
		    			if (stringToPlay == null) {
		    				lineNumber = 0;
		    			}
		    			
	    				// Close buffer reader
						br.close(); 
		    		} catch  (Exception e) {  
		    			// Handle input file errors
		    		}
	
		        } 
			};
			
			// Start thread
			if (!playThread.isAlive())
			playThread.start();
		
		// If mode playing
		} else if (mode == 2) {
			// Change to Paused
			mode = 3;
			
			// Cancel vibration
			vib.cancel();
			vib.vibrate(pausePattern, -1);
			
			// Change image resource to play
			playPauseButton.setImageResource(R.drawable.play);
			
			// Save state 
			SharedPreferences preferences = getSharedPreferences("senseamp",MODE_PRIVATE);
			SharedPreferences.Editor editor = preferences.edit();
			editor.clear();
			editor.putString("prevFullFileName", fullFileName);
			editor.putInt("prevLineNumber", lineNumber);
			editor.commit();			

		}		
	}
	
	// Checks if external storage is available to at least read 
	public boolean isExternalStorageReadable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state) ||
	        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	@Override
	public void onPause() {
       super.onPause();
       
       // Pause
       vib.cancel();
       mode = 3;
       
       // Save state
		SharedPreferences preferences = getSharedPreferences("senseamp",MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.clear();
		editor.putString("prevFullFileName", fullFileName);
		editor.putInt("prevLineNumber", lineNumber);
		editor.commit();
		
		// End activity
		this.finish();
    }
	
	@Override
    public void onStop() {
       super.onStop();
       
       // Pause
       vib.cancel();
       mode = 3;
       
       // Save state
		SharedPreferences preferences = getSharedPreferences("senseamp",MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.clear();
		editor.putString("prevFullFileName", fullFileName);
		editor.putInt("prevLineNumber", lineNumber);
		editor.commit();
		
		// End activity
		this.finish();

    }
    
	@Override
    public void onDestroy() {
       super.onDestroy();
       
       // Pause
       vib.cancel();
       mode = 3;
       
       // Save state
		SharedPreferences preferences = getSharedPreferences("senseamp",MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.clear();
		editor.putString("prevFullFileName", fullFileName);
		editor.putInt("prevLineNumber", lineNumber);
		editor.commit();	       
    }	

}
