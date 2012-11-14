package com.example.searchx;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
		public class Button5ClickListener implements OnClickListener {

		public void onClick(View v) {
			Intent intent5 = new Intent(MainActivity.this, com.example.searchx.SubActivity.class);
			rsp = "Result";
			intent5.putExtra("RESP", rsp);
			startActivity(intent5);
		}

	}

		String rsp;
		
    public class Button3ClickListener implements OnClickListener {

		public void onClick(View v) {
			Intent intent3 = new Intent(MainActivity.this, com.example.searchx.SubActivity.class);
			rsp = "Reset";
			intent3.putExtra("RESP", rsp);
			startActivity(intent3);
		}

	}

	public class Button2ClickListener implements OnClickListener {

		public void onClick(View v) {
			Intent intent2 = new Intent(MainActivity.this, com.example.searchx.SubActivity.class);
			rsp = "No";
			intent2.putExtra("RESP", rsp);
			startActivity(intent2);
		}

	}

	public class Button1ClickListener implements OnClickListener {

		public void onClick(View v) {
			Intent intent1 = new Intent(MainActivity.this, com.example.searchx.SubActivity.class);
			rsp = "Yes";
			intent1.putExtra("RESP", rsp);
			startActivity(intent1);
		}

	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button myBtn1 = (Button) findViewById(R.id.button1);
        myBtn1.setOnClickListener(new Button1ClickListener());
        
        Button myBtn2 = (Button) findViewById(R.id.button2);
        myBtn2.setOnClickListener(new Button2ClickListener());
        
        Button myBtn3 = (Button) findViewById(R.id.button3);
        myBtn3.setOnClickListener(new Button3ClickListener());
        
        Button myBtn5 = (Button) findViewById(R.id.button5);
        myBtn5.setOnClickListener(new Button5ClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
