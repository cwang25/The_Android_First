package com.cj.numbermagic;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	Button startbutton;
	Button oddbutton;
	Button evenbutton;
	Button quitbutton; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_stage);
        startbutton = (Button)this.findViewById(R.id.start);
        
        
        startbutton.setOnClickListener((OnClickListener) this);
       
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    public void onClick(View v){
    	if(v == startbutton){
    		setContentView(R.layout.second_stage);
    		oddbutton = (Button)this.findViewById(R.id.odd);
            evenbutton = (Button)this.findViewById(R.id.even);
            oddbutton.setOnClickListener((OnClickListener) this);
            evenbutton.setOnClickListener((OnClickListener) this);
    	} else if(v == oddbutton){
    		setContentView(R.layout.third_stage);
    		quitbutton = (Button)this.findViewById(R.id.quit);
    		quitbutton.setOnClickListener((OnClickListener) this);
    	} else if(v == evenbutton){
    		setContentView(R.layout.third_stage);
    		quitbutton = (Button)this.findViewById(R.id.quit);
    		quitbutton.setOnClickListener((OnClickListener) this);
    	} else if(v == quitbutton){
    		setContentView(R.layout.first_stage);
            startbutton = (Button)this.findViewById(R.id.start);
            startbutton.setOnClickListener((OnClickListener) this);
    	}
    }
    
}
