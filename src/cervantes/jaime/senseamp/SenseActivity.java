package cervantes.jaime.senseamp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
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


public class SenseActivity extends Activity {
	private Drawable circle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Remove action bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// Initialize
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sense);
		
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
        Intent i = new Intent(SenseActivity.this, HomeActivity.class);
        startActivity(i);

        // close this activity
        finish();
	}
		
	
	private class DrawingView extends View {
		private Paint paint;
		private float selectionOffsetX = 0;
		private float selectionOffsetY = 0;
		private RectF bounds = new RectF();
		private Rect rect = new Rect();
		private int visible = 0;
		
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
						bounds.set(
								event.getX() - 200/2,
								event.getY() - 200/2, 
								event.getX() - 200/2 + width, 
								event.getY() - 200/2 + height);
						invalidate();
						
				    	// Vibrate
				    	Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				    	v.vibrate(100);
					
					break;
				case MotionEvent.ACTION_UP:
					visible = 0;
					invalidate();	
					break;
				case MotionEvent.ACTION_DOWN:
					visible = 1;
					
					bounds.set(
							event.getX() - 200/2,
							event.getY() - 200/2, 
							event.getX() - 200/2 + width, 
							event.getY() - 200/2 + height);
					invalidate();					
					break;
				default:
					break;
				
			}
				
			return true;
		}
		
		@Override protected void onDraw(Canvas canvas) {
			if (paint == null) 
				init();
			
			if (visible == 1) {
				bounds.round(rect);
				circle.setBounds(rect);
				circle.draw(canvas);
			}
		
		}
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
