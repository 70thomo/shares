package com.example.firesocket;

import java.net.URL;
import java.net.URLConnection;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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
	        	
	        	XmlPullParserFactory factory;
     	  	   factory = XmlPullParserFactory.newInstance();
     	  	   factory.setNamespaceAware(true);
     	  	   XmlPullParser xpp = factory.newPullParser();
     	  	   URL url = new URL("http://192.168.2.102:8080/ipid/touroku?idt="+id+"&ipt="+ipad);
     	  	   URLConnection connection = url.openConnection();
     	  	   xpp.setInput(connection.getInputStream(),"UTF-8");
     	  	   
     	  	   int eventType = xpp.getEventType();
     	  	   
     	  	   while (eventType != XmlPullParser.END_DOCUMENT) 
     	  	   {
     	  	    if(eventType == XmlPullParser.TEXT) 
     	  	    {
     	  	     String st = new String();
     	  	     st = xpp.getText().toString();
     	  	     double num = Double.parseDouble(st);
     	  	     int numm = (int) num;
     	  	     String stt = String.valueOf(numm);
             	if (stt.equals("1")){
             		Log.d(test,st);
            		Toast.makeText(getApplication(),"“o˜^Š®—¹", Toast.LENGTH_LONG).show();
            	} else {
            		Toast.makeText(getApplication(),"“o˜^Ž¸”s", Toast.LENGTH_LONG).show();
            		
            	}

     	  	    }	    
     	  	     eventType = xpp.next();
     	  	    }
	        	
	            /* XmlPullParser parser = Xml.newPullParser();            
	            URL url = new URL("http://192.168.2.102:8080/ipid/touroku?idt="+id+"&ipt="+ipad);
	            URLConnection connection = url.openConnection();
	            parser.setInput(connection.getInputStream(), "UTF-8");
	            
	            // ƒ^ƒO–¼
	            String tag = "";
	            String value = "";

	            // XML‚Ì‰ðÍ
	            for (int type = parser.getEventType(); type != XmlPullParser.END_DOCUMENT; type = parser.next()) {
	                switch(type) {
	                case XmlPullParser.START_TAG:
	                    tag = parser.getName();
	                    break;
	                case XmlPullParser.TEXT:
	                    value = parser.getText();  // ‹ó”’‚ÅŽæ“¾‚µ‚½‚à‚Ì‚Í‘S‚Äˆ—‘ÎÛŠO‚Æ‚·‚é
	                    if(value.trim().length() != 0) {
	                        if(tag.equals("boody")) {
	                        	Log.d(test,value);
	                        	if (value.equals("1")){
	                        		Toast.makeText(getApplication(),"“o˜^Š®—¹", Toast.LENGTH_LONG).show();
	                        	} else {
	                        		Toast.makeText(getApplication(),"“o˜^Ž¸”s", Toast.LENGTH_LONG).show();
	                        		Log.d(test,value);
	                        	}
	                        } 
	                    }
	                    break;
	                case XmlPullParser.END_TAG: // I—¹ƒ^ƒO
	                    break;
	                }
	            } */
	        } catch (Exception e) {
	           System.out.println(e);
	        }    
		}

	}

    public class Button2ClickListener implements OnClickListener {

		public void onClick(View v) {
            Intent intent1 = new Intent(MainActivity.this, com.example.firesocket.SubServer.class);
            startActivity(intent1);
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
	            
	            // ƒ^ƒO–¼
	            String tag = "";
	            String value = "";

	            // XML‚Ì‰ðÍ
	            for (int type = parser.getEventType(); type != XmlPullParser.END_DOCUMENT; type = parser.next()) {
	                switch(type) {
	                case XmlPullParser.START_TAG:
	                    tag = parser.getName();
	                    break;
	                case XmlPullParser.TEXT:
	                    value = parser.getText().toString();  // ‹ó”’‚ÅŽæ“¾‚µ‚½‚à‚Ì‚Í‘S‚Äˆ—‘ÎÛŠO‚Æ‚·‚é
	                    
	                    if(value.trim().length() != 0) {
	                        if(tag.equals("boody")) {
	                        	
	                        	String strAry[] = value.split("\\.");
	                        	
	                        	double a01 = Double.parseDouble(strAry[0]);
	                        	int a02 = (int)a01;
	                        	String aa = String.valueOf(a02);
	                        	
	                        	double b01 = Double.parseDouble(strAry[1]);
	                        	int b02 = (int)b01;
	                        	String bb = String.valueOf(b02);
	                        	
	                        	double c01 = Double.parseDouble(strAry[2]);
	                        	int c02 = (int)c01;
	                        	String cc = String.valueOf(c02);

	                        	double d01 = Double.parseDouble(strAry[3]);
	                        	int d02 = (int)d01;
	                        	String dd = String.valueOf(d02);

	                        	String test2 = "adfalsjaosdi";
	                        	Log.d(test2,strAry[0]);

	                        	String tvalue = aa + "." + bb + "." + cc + "." + dd;

	                        	ipid = tvalue;
	                        	Log.d(tvalue,ipid);
	                        } 
	                    }
	                    break;
	                case XmlPullParser.END_TAG: // I—¹ƒ^ƒO
	                    break;
	                }
	            }
	            Intent intent2 = new Intent(MainActivity.this, com.example.firesocket.SubTerminal.class);
	            intent2.putExtra("ipa",ipid);
	            startActivity(intent2);

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
