package com.appspot.senseampapp.senseamp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.ViewGroup;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.util.AttributeSet;
import android.os.Vibrator;
import android.util.Log;


public class SettingsActivity extends Activity {
	private Drawable circle;
	private long duration = 100L;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		// Get circle
		circle = getResources().getDrawable(R.drawable.circle);
				
		// Get ViewGroup
		ViewGroup main = (ViewGroup) findViewById(R.id.drawmain);
		DrawingView drawingView = new DrawingView(this);
		LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 1, 1);
		//LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1 ) //LayoutParams(LayoutParams.MATCH_PARENT, 0, 1); 
		//drawingView.setLayoutParams(params);
		main.addView(drawingView);
	}
	
	public void onClickHome(View v) {
		// Call vibration pattern
		long[] pattern = {0, 50};
		Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		vib.vibrate(pattern, -1);
		
		// Start write by me activity
        Intent i = new Intent(SettingsActivity.this, HomeActivity.class);
        startActivity(i);

        // close this activity
        finish();
	}
	
	public void onClickDuration(View v) {
		// Set String2Vibrations class
		String2Vibrations.time = duration;
		
		// Call vibration pattern
		long[] pattern = {0, String2Vibrations.time, String2Vibrations.time, String2Vibrations.time};
		Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		vib.vibrate(pattern, -1);

        // close this activity
        //finish();
	}
		
	
	private class DrawingView extends View {
		private Paint paint;
		private float selectionOffsetX = 0;
		private float selectionOffsetY = 0;
		private float currentX = 0;
		private float currentY = 0;
		private RectF bounds = new RectF();
		private Rect rect = new Rect();
		private int moving = 0;
		
		public DrawingView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
		}
		public DrawingView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}
		public DrawingView(Context context) {
			super(context);
		}
		private void init() {			
			paint = new Paint();
			paint.setColor(Color.RED);
		}
		
		@Override public boolean onTouchEvent(MotionEvent event) {
			float width = 200;
			float height = 200;
			
			switch(event.getAction()) {
				case MotionEvent.ACTION_MOVE:
						currentX = event.getX();
						currentY = event.getY();
					
						moving = 1;
						
						invalidate();
					
					break;
				case MotionEvent.ACTION_UP:
					moving = 0;
					break;
				case MotionEvent.ACTION_DOWN:
					moving = 0;				
					break;
				default:
					break;
				
			}
				
			return true;
		}
		
		@Override protected void onDraw(Canvas canvas) {
			//long[] pattern = {50, (long)duration};
			
			if (paint == null) 
				init();
			
				// Update slider
				canvas.drawRect(0, 0, currentX, this.getHeight(), paint);
				
			if (moving == 1 && currentX > 0) {
				// Set duration
				
				duration = (long)((currentX/this.getWidth())*600);
				Log.i("DURATION", Long.toString(duration));
				
				// Vibrate
		    	Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		    	v.vibrate(100);
			}
		
		}
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sense, menu);
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
