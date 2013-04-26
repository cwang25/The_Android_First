package com.cj.numbermagic;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private Button startbutton;
	private Button oddbutton;
	private Button evenbutton;
	private Button quitbutton; 
	private MagicCore magiccore;
	private EditText input;
	private TextView answer;
	private boolean even = false;
	private int inputnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        introStage();
        inputnumber = 0;
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    public void onClick(View v){
    	if(v == startbutton){
    		oddOrEvenStage();
    	} else if(v == oddbutton){
    		even = false;
    		answerStage();
    	} else if(v == evenbutton){
    		even = true;
    		answerStage();
    	} else if(v == quitbutton){
    		introStage();
    	}
    }
    public void introStage(){
    	setContentView(R.layout.first_stage);
        startbutton = (Button)this.findViewById(R.id.start);
        startbutton.setBackgroundColor(getResources().getColor(R.color.white));
        startbutton.setOnClickListener((OnClickListener) this);
        TextView instruction = (TextView)this.findViewById(R.id.instruction);
        instruction.setTextColor(getResources().getColor(R.color.white));
        instruction.setText("Welcome to the Magic World!~  Now I want you to pull out your calculator, but not this phone. Just incase you will considering I will record down your inputs." +
        		"Now try to mutiply random numbers, but remember multiply only one digit number.\n For Example, 3x5x7x6x .....etc" +
        		"Keep multiplying random numbers, more random is better, until you final value gets between millions and billions.  "
        		);
        input = (EditText)this.findViewById(R.id.editText1);
        input.setTextColor(getResources().getColor(R.color.white));
    }
    public void oddOrEvenStage(){
    	String stringinput = input.getText().toString();
    	try{
        inputnumber = Integer.parseInt(stringinput);
        setContentView(R.layout.second_stage);
		oddbutton = (Button)this.findViewById(R.id.odd);
        evenbutton = (Button)this.findViewById(R.id.even);
        oddbutton.setOnClickListener((OnClickListener) this);
        evenbutton.setOnClickListener((OnClickListener) this);
    	} catch(NumberFormatException e){
    		if(stringinput.length() > 10){
    			Toast.makeText(getBaseContext(), "Please follow the direction, the number you used is too huge!!! I can't handle this!", Toast.LENGTH_SHORT).show();
    		} else {
        		Toast.makeText(getBaseContext(), "You have to put in number without your magic number before to start the magic!", Toast.LENGTH_SHORT).show();
    		}
    	}
    }
    public void answerStage(){
    	setContentView(R.layout.third_stage);
    	magiccore = new MagicCore(inputnumber,even);
    	answer = (TextView)this.findViewById(R.id.answer);
    	String finalanswer = magiccore.getAnswer()+"";
		answer.setText(finalanswer);
		quitbutton = (Button)this.findViewById(R.id.quit);
		quitbutton.setOnClickListener((OnClickListener) this);
		
    }
}
