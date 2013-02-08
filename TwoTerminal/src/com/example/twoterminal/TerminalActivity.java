package com.example.twoterminal;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.text.format.Time;
import android.util.Log;
import android.view.*;
import android.widget.*;

public class TerminalActivity extends Activity {
      SampleView sv;
      int a,b,d;
	  int date;
      float co1,co2;
      int dareka = 1;
      String value = "y";
      final Timer timer = new Timer();
      
      
      String aa,bb,cc;
      
      public void onCreate(Bundle savedIntanceState) {
    	  super.onCreate(savedIntanceState);
    	  LinearLayout ll = new LinearLayout(this);
    	  setContentView(ll); 
    	  sv = new SampleView(this);
          ll.addView(sv);
      
  	      timer.scheduleAtFixedRate(new TimerTask(){

				@Override
				public void run() {
					// TODO �����������ꂽ���\�b�h�E�X�^�u
		       		try {		
		        		   XmlPullParserFactory factory;
		        	  	   factory = XmlPullParserFactory.newInstance();
		        	  	   factory.setNamespaceAware(true);
		        	  	   XmlPullParser xpp = factory.newPullParser();
		        	  	   String ur = "http://192.168.2.102:8080/otsu/yotsu?zahyou=" + aa + "&jikoku=" + bb + "&dareka=" + cc;
		        	  	   URL url = new URL(ur);
		        	  	   
		        	  	   URLConnection connection = url.openConnection();
		        	  	   xpp.setInput(connection.getInputStream(),"UTF-8");
		        	  	   
		        	  	   int eventType = xpp.getEventType();
		        	  	   String tag = "";
		        	  	   
		                   for (int type = xpp.getEventType(); type != XmlPullParser.END_DOCUMENT; type = xpp.next()) {
		                       switch(type) {
		                       case XmlPullParser.START_TAG:
		                           tag = xpp.getName();
		                           break;
		                       case XmlPullParser.TEXT:
		                           value = xpp.getText();  // �󔒂Ŏ擾�������̂͑S�ď����ΏۊO�Ƃ���
		                           if(value.trim().length() != 0) {
		                               if(tag.equals("boody")) {
		                            	   if (value == "n") {
		                            		   // �Q�[�����~
		                            	   }
		                               } 
		                           }
		                           break;
		                       case XmlPullParser.END_TAG: // �I���^�O
		                           break;
		                       }
		                     }   // for�I���
		        	  	  }
		        	  	  
		        	  	  catch(XmlPullParserException e) {
		        	  	    e.printStackTrace();
		        	  	  } 
		        	  	  
		        	  	  catch (IOException e) {
		        	  	    e.printStackTrace();
		        	  	  }
		    			
		                Time time = new Time("Asia/Tokyo");
		  	            time.setToNow();
		  	            date = time.second;          	    
		  	            co1 = SampleView.x;
		  	            co2 = SampleView.y;
		  	            
		  	            int co01 = (int)co1;
		  	            
		  	            aa = String.valueOf(co01);
		  	            bb = String.valueOf(date);
		  	            cc = String.valueOf(dareka);

		      	            try 
		                    {
		                    Thread.sleep(0);
		                    } 
		                    catch(InterruptedException e)
		                    {
		              	
		                    }
		  	             
				}
  	           
  	           },0,100);
 
  	            	
 

      }
}


class SampleView extends View {
     //���W�F���p��x,y�̌^���`
     static float x;
	 static float y;
     
     //�����l�ݒ�
     public SampleView(Context cn) {
   	  super(cn);
   	  x = 400; y = 400;
     }
     
     
     
     public boolean onTouchEvent(MotionEvent e) {
	    x = e.getX();
	    y = e.getY();

	    //�X�V��L���ɂ���
	    this.invalidate();
	    return true;
     
     }
     
     
     protected void onDraw(Canvas cs) {
	   super.onDraw(cs);

	   //�`����@�̐ݒ�
	   Paint p = new Paint();
	   p.setColor(Color.BLACK);
           p.setStyle(Paint.Style.FILL);
           p.setStrokeWidth(8);
       
       //�~�̕`��
       cs.drawCircle( x, y, 50, p);
      
     }
  
}