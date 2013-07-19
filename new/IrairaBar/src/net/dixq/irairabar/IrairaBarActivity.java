package net.dixq.irairabar;

import java.net.URL;
import java.net.URLConnection;
import org.xmlpull.v1.XmlPullParser;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class IrairaBarActivity extends Activity 
{
	static int st;
	//String us = "a";
	String us = "b";
	
	GameSurfaceView _view;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//画面のタイムアウト防止 
		
		_view = new GameSurfaceView(this);
		setContentView(_view);
		AcSensor.Inst().onCreate(this); // センサー初期化
	
		
		//解析用
		 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
		
		Thread trd = new Thread(new Runnable()
		   {
			public void run() 
	  	    {	        
	               //追加1
			//	if(st==1)
				//{
				   //追加1ここまで
	
	               for(int i=1; i>0; i++)
	               {	             	
	            	   
					//try
	         	  	  //{		
	            		//   XmlPullParserFactory factory;
	            		  // factory = XmlPullParserFactory.newInstance();
	            		   //factory.setNamespaceAware(true);
	            		   //XmlPullParser xpp = factory.newPullParser();
	            		 
	            		   // assets情報の取得
	            		   //AssetManager asset = getResources().getAssets();
	            		   // XMLファイルのストリーム情報を取得
	            		   //InputStream is = null;
	            		   //is = asset.open("hantei.xml");
	            		   //InputStreamReader isr = new InputStreamReader(is);
	            		   //xpp.setInput(isr);
	            		    
	            		   //int eventType = xpp.getEventType();
	            		   
	         	  	   //while (eventType != XmlPullParser.END_DOCUMENT) 
	         	  	   //{
	         	  	    //if (eventType == XmlPullParser.START_DOCUMENT) 
	         	  	    //{} 
	         	  	    //else if (eventType == XmlPullParser.START_TAG) 
	         	  	    //{} 
	         	  	    //else if (eventType == XmlPullParser.END_TAG) 
	         	  	    //{} 
	         	  	    //else if(eventType == XmlPullParser.TEXT) 
	         	  	    //{
	         	  	    	//String tu = new String();
	         	  	    	//tu = xpp.getText();
	         	  	    	//st= Integer.parseInt(tu);
	         	  	    	
	         	  	    //}	    
	         	  	     //eventType = xpp.next();
	         	  	    //}
	         	  	  //} 
	         	  	  
	         	  	    //catch(XmlPullParserException e) 
	         	  	    //{
	         	  	    //e.printStackTrace();
	         	  	    //} 
	         	  	  
	         	  	    //catch (IOException e) 
	         	  	    //{
	         	  	    //e.printStackTrace();
	         	  	    //}
	            	   
	            	   
	                
	                //センサーの値　その1
	                float co1 = Player._cir._x ;
		            //センサーの値 その2
	                float co2 = Player._cir._y ;
	                
	                int ccc1 = (int)co1;
	                int ccc2 = (int)co2;
	                
	                String c1 = String.valueOf(ccc1);
	                String c2 = String.valueOf(ccc2);
	                String ii = String.valueOf(i);
		            
		            try{
		                XmlPullParser parser = Xml.newPullParser();            
		                URL url = new URL("http://192.168.2.102:8080/fiap/byebye?xza="+ c1 + "&yza=" + c2 + "&time=" + ii + "&user=" + us );
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
		            	  	     double num = Double.parseDouble(value);
		             	  	     int numm = (int) num;
		             	  	     String st1 = String.valueOf(numm);

		                        if(value.trim().length() != 0) {
		                            if(tag.equals("boody")) {
		                            	if(st1.equals("1")){ // 分岐に失敗してる？
		                            		st = 1;
		                            		Log.d("log1", value);
		                            		String stt = String.valueOf(st);
		                            		Log.d("log2",stt);
		                            	} else {
		                            		st = 0;
		                            		Log.d("log1",value);
		                            		String stt = String.valueOf(st);
		                            		Log.d("log2",stt);
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

		            
		           try 
	               {
		            	Thread.sleep(2000);
	               } 
	               catch(InterruptedException e)
	               {
	        	
	               }
		     }
	  	    }
				   //追加2
		  	  // }
				//追加2ここまで
	      });
		
		      trd.start();
	    
	
	}

	@Override
	protected void onResume() { // アクティビティが動き始める時呼ばれる
		super.onResume();
		AcSensor.Inst().onResume();// 開始時にセンサーを動かし始める
	}

	@Override
	protected void onPause() { // アクティビティの動きが止まる時呼ばれる
		super.onPause();
		AcSensor.Inst().onPause();// 中断時にセンサーを止める
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			_view = new GameSurfaceView(this);
			setContentView(_view); // 処理の実体はGameSurfaceView内のGameMgr
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
}