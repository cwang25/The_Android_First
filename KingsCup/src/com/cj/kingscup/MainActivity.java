package com.cj.kingscup;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
}
