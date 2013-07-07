package com.cj.kingscup;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private boolean mShowingBack = false;
	ImageView imgview = (ImageView) findViewById(R.id.imageView1);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gamescreen);
		if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.imageView1, new CardFrontFragment())
                    .commit();
        }
//		imgview.setOnClickListener((OnClickListener) this);
		
		
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
	
	private void flipCard() {
	    if (mShowingBack) {
	        getFragmentManager().popBackStack();
	        return;
	    }

	    // Flip to the back.

	    mShowingBack = true;

	    // Create and commit a new fragment transaction that adds the fragment for the back of
	    // the card, uses custom animations, and is part of the fragment manager's back stack.

	    getFragmentManager()
	            .beginTransaction()

	            // Replace the default fragment animations with animator resources representing
	            // rotations when switching to the back of the card, as well as animator
	            // resources representing rotations when flipping back to the front (e.g. when
	            // the system Back button is pressed).
	            .setCustomAnimations(
	                    R.animator.card_flip_right_in, R.animator.card_flip_right_out,
	                    R.animator.card_flip_left_in, R.animator.card_flip_left_out)

	            // Replace any fragments currently in the container view with a fragment
	            // representing the next page (indicated by the just-incremented currentPage
	            // variable).
	            .replace(R.id.imageView1, new CardBackFragment())

	            // Add this transaction to the back stack, allowing users to press Back
	            // to get to the front of the card.
	            .addToBackStack(null)

	            // Commit the transaction.
	            .commit();
	}
	
	public void onClick(View v){
		if(v == imgview){
    		flipCard();
    	}
	}
}
