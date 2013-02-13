package com.example.firesocket;

import java.net.URL;
import java.net.URLConnection;
import org.xmlpull.v1.XmlPullParser;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint({ "NewApi", "ShowToast" })
public class MainActivity extends Activity {
	String ipad = "";
    String test = "test";
    String ipid = "";
	
	public class Button1ClickListener implements OnClickListener {

		public void onClick(View v) {
			
	        try{
	        	
	        	EditText editText1 = (EditText)findViewById(R.id.editText1);
	        	String id = editText1.getText().toString();
	        	
	            XmlPullParser parser = Xml.newPullParser();            
	            URL url = new URL("http://192.168.2.102:8080/ipid/touroku?idt="+id+"&ipt="+ipad);
	            URLConnection connection = url.openConnection();
	            parser.setInput(connection.getInputStream(), "UTF-8");
	            
	            // タグ名
	            String tag = "";
	            String value = "";

	            // XMLの解析
	            for (int type = parser.getEventType(); type != XmlPullParser.END_DOCUMENT; type = parser.next()) {
	                switch(type) {
	                case XmlPullParser.START_TAG:
	                    tag = parser.getName();
	                    break;
	                case XmlPullParser.TEXT:
	                    value = parser.getText();  // 空白で取得したものは全て処理対象外とする
	                    if(value.trim().length() != 0) {
	                        if(tag.equals("boody")) {
	                        	Log.d(test,value);
	                        	if (value.equals("ok")){
	                        		Toast.makeText(getApplication(),"登録完了", Toast.LENGTH_LONG).show();
	                        	} else {
	                        		Toast.makeText(getApplication(),"登録失敗", Toast.LENGTH_LONG).show();
	                        	}
	                        } 
	                    }
	                    break;
	                case XmlPullParser.END_TAG: // 終了タグ
	                    break;
	                }
	            }
	        } catch (Exception e) {
	           System.out.println(e);
	        }    
		}

	}

    public class Button2ClickListener implements OnClickListener {

		public void onClick(View v) {

		}

	}

	public class Button3ClickListener implements OnClickListener {

		public void onClick(View v) {

	        try{
	        	
	        	EditText editText2 = (EditText)findViewById(R.id.editText2);
	        	String name = editText2.getText().toString();
	        	
	            XmlPullParser parser = Xml.newPullParser();            
	            URL url = new URL("http://192.168.2.102:8080/ipid/kensaku?kensaku="+name);
	            URLConnection connection = url.openConnection();
	            parser.setInput(connection.getInputStream(), "UTF-8");
	            
	            // タグ名
	            String tag = "";
	            String value = "";

	            // XMLの解析
	            for (int type = parser.getEventType(); type != XmlPullParser.END_DOCUMENT; type = parser.next()) {
	                switch(type) {
	                case XmlPullParser.START_TAG:
	                    tag = parser.getName();
	                    break;
	                case XmlPullParser.TEXT:
	                    value = parser.getText();  // 空白で取得したものは全て処理対象外とする
	                    if(value.trim().length() != 0) {
	                        if(tag.equals("boody")) {
	                        	ipid = value;
	                        	Log.d(test,ipid);
	                        } 
	                    }
	                    break;
	                case XmlPullParser.END_TAG: // 終了タグ
	                    break;
	                }
	            }
	        } catch (Exception e) {
	           System.out.println(e);
	        }    
	
		}

	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        
        Button myBtn1 = (Button) findViewById(R.id.button1);
        myBtn1.setOnClickListener(new Button1ClickListener());
        
        Button myBtn2 = (Button) findViewById(R.id.button2);
        myBtn2.setOnClickListener(new Button2ClickListener());
        
        Button myBtn3 = (Button) findViewById(R.id.button3);
        myBtn3.setOnClickListener(new Button3ClickListener());
        
        WifiManager wifiManager =  (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifIinfo = wifiManager.getConnectionInfo();
        int address = wifIinfo.getIpAddress();

        ipad = ((address >> 0) & 0xFF) + "."
                + ((address >> 8) & 0xFF) + "." + ((address >> 16) & 0xFF)
                + "." + ((address >> 24) & 0xFF);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
