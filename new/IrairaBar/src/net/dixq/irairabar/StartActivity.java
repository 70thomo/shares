package net.dixq.irairabar;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class StartActivity extends Activity
{
      Button bt;

      public void onCreate(Bundle savedInstanceState)
      {
    	  super.onCreate(savedInstanceState);
          LinearLayout ll = new LinearLayout(this);
          ll.setOrientation(LinearLayout.VERTICAL);
          setContentView(ll);
          
      
          bt = new Button(this);
          bt.setText("start");
      
          ll.addView(bt);
          
          bt.setOnClickListener(new Sample());
          }
      
      class Sample implements OnClickListener, android.view.View.OnClickListener
      {
		public void onClick(View v) 
		{
			Intent it = new Intent(StartActivity.this,net.dixq.irairabar.IrairaBarActivity.class);
			it.setAction(Intent.ACTION_MAIN);
			startActivity(it);
		}

		@Override
		public void onClick(DialogInterface dialog, int which)
		{
			// TODO Auto-generated method stub
			Intent it = new Intent(StartActivity.this,net.dixq.irairabar.IrairaBarActivity.class);
			it.setAction(Intent.ACTION_MAIN);
			startActivity(it);
		}
    	  
      }
}