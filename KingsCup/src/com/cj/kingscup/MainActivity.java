package com.cj.kingscup;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gamescreen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// TODO implement changeImage method
	public void changeImage(Card card) {
		ImageView imgview = (ImageView) findViewById(R.id.imageView1);
		// get Card image filename from database
		Drawable drawable = getResources().getDrawable(R.drawable.club0);

		// The "R.drawable.club0" should be dynamically generated somehow from
		// the card parameter
		//perhaps we should put the R.... in the database to make it simple?
		imgview.setImageDrawable(drawable);
	}
	
	
	
	// I added some inner classes  for card flips.  Since it requires some multiple layouts and activity classes.
	// 06/13
	
	public class CardFrontFragment extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
			return inflater.inflate(R.drawable.club12, container, false);
		}
	}
	
	public class CardBackFragment extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
			return inflater.inflate(R.drawable.club0, container, false);
		}
	}
}
