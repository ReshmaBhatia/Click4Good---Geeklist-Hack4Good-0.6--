package com.Click4Good;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener {
	Button b1,b2,b3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		b3 = (Button) findViewById(R.id.button3);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		
	}

	
	@Override
	public void onClick(View v)
	{
		
		switch (v.getId()) {

		  case R.id.button1:
			  Intent i1 = new Intent(getApplicationContext(),ShowLocationActivity.class);
			   startActivity(i1);
			   finish();
		   break;

		  case R.id.button2:
			  Intent i2 = new Intent(getApplicationContext(),Instructions.class);
			    startActivity(i2);
			    finish();
		   break;

		  case R.id.button3:
			  Intent i3 = new Intent(getApplicationContext(),SlideShow.class);
			    startActivity(i3);
			    finish();
		   break;

		  }// TODO Auto-generated method stub
	    
	    
	}

}
