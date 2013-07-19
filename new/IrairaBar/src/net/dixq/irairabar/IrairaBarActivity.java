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

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//��ʂ̃^�C���A�E�g�h�~ 
		
		_view = new GameSurfaceView(this);
		setContentView(_view);
		AcSensor.Inst().onCreate(this); // �Z���T�[������
	
		
		//��͗p
		 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
		
		Thread trd = new Thread(new Runnable()
		   {
			public void run() 
	  	    {	        
	               //�ǉ�1
			//	if(st==1)
				//{
				   //�ǉ�1�����܂�
	
	               for(int i=1; i>0; i++)
	               {	             	
	            	   
					//try
	         	  	  //{		
	            		//   XmlPullParserFactory factory;
	            		  // factory = XmlPullParserFactory.newInstance();
	            		   //factory.setNamespaceAware(true);
	            		   //XmlPullParser xpp = factory.newPullParser();
	            		 
	            		   // assets���̎擾
	            		   //AssetManager asset = getResources().getAssets();
	            		   // XML�t�@�C���̃X�g���[�������擾
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
	            	   
	            	   
	                
	                //�Z���T�[�̒l�@����1
	                float co1 = Player._cir._x ;
		            //�Z���T�[�̒l ����2
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
		                
		                // �^�O��
		                String tag = "";
		                String value = "";
		                
		                // XML�̉��
		                for (int type = parser.getEventType(); type != XmlPullParser.END_DOCUMENT; type = parser.next()) {
		                    switch(type) {
		                    case XmlPullParser.START_TAG:
		                        tag = parser.getName();
		                        break;
		                    case XmlPullParser.TEXT:
		                        value = parser.getText();  // �󔒂Ŏ擾�������̂͑S�ď����ΏۊO�Ƃ���
		            	  	     double num = Double.parseDouble(value);
		             	  	     int numm = (int) num;
		             	  	     String st1 = String.valueOf(numm);

		                        if(value.trim().length() != 0) {
		                            if(tag.equals("boody")) {
		                            	if(st1.equals("1")){ // ����Ɏ��s���Ă�H
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
		                    case XmlPullParser.END_TAG: // �I���^�O
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
				   //�ǉ�2
		  	  // }
				//�ǉ�2�����܂�
	      });
		
		      trd.start();
	    
	
	}

	@Override
	protected void onResume() { // �A�N�e�B�r�e�B�������n�߂鎞�Ă΂��
		super.onResume();
		AcSensor.Inst().onResume();// �J�n���ɃZ���T�[�𓮂����n�߂�
	}

	@Override
	protected void onPause() { // �A�N�e�B�r�e�B�̓������~�܂鎞�Ă΂��
		super.onPause();
		AcSensor.Inst().onPause();// ���f���ɃZ���T�[���~�߂�
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			_view = new GameSurfaceView(this);
			setContentView(_view); // �����̎��̂�GameSurfaceView����GameMgr
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
}