package com.example.socketterminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.widget.*;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
      public SampleView sv;
      int a,b,d;
      float co1,co2;
      String address ="192.168.2.100";
      int port = 10000;
      String aa,bb,cc;
      final Timer timer = new Timer();
      static int hantei = 0;
      String sikibetu = "1";
      
      Handler handler = new Handler(); // (1)
      
      public void onCreate(Bundle savedIntanceState) {
    	  super.onCreate(savedIntanceState);
    	  final LinearLayout ll = new LinearLayout(this);
    	  setContentView(ll); 
    	  sv = new SampleView(this);
          ll.addView(sv);
          
 	      timer.scheduleAtFixedRate(new TimerTask(){

 	    	  @Override
			  public void run() {
					// TODO 自動生成されたメソッド・スタブ                  	    
			          Socket socket = null;
          
			          try {
        	  
			        	  co1 = SampleView.x;
			        	  co2 = SampleView.y;
              
			        	  int co01 = (int)co1;  
			        	  int co02 = (int)co2;
			        	  
			        	  aa = String.valueOf(co01);
			        	  while (aa.length() < 4) {
			        		  aa = "0" + aa;
			        	  }
			        	  
			        	  bb = String.valueOf(co02);
			        	  while (bb.length() < 4){
			        		  bb = "0" + bb;
			        	  }
			        	  
			        	  cc = sikibetu + aa + bb;
			        	  
			        	  int zah = Integer.parseInt(cc);
			        	  
			        	  char dare;
						  String xza,yza; 
              
			        	  StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
			        	  
			        	  socket = new Socket(address,port);
			        	  PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
			        	  pw.println(cc);
              
			        	  /* BufferedReader br = new BufferedReader (new InputStreamReader(socket.getInputStream()));
			        	  String str = br.readLine();
			        	  
			        	  dare = str.charAt(0);
			        	  // String dareda = String.valueOf(dare);
			        	  
			        	  xza = str.substring(1,5);
			        	  yza = str.substring(6,9);
			        	  
			        	  int xzah = Integer.parseInt(xza);
			        	  int yzah = Integer.parseInt(yza);
			        	  
			        	  // if (dareda != "1") {
			        	  		        	  
				        	  if (xzah-50 < co01 ) {
				        		  if (co01 < xzah+50) {
				        			  if (yzah-50 < co02) {
				        				  if (co02 < yzah+50) {
				        					  hantei = 1;
				        					  Log.d(xza,aa);
				        				  } else {
						        			  hantei = 0;
						        			  handler.post(new Runnable() {
						        				  public void run() {
						        					  ll.removeView(sv); 
						        					  sv = new SampleView(getApplication());
						        					  ll.addView(sv);
						        					  return;
						        				  }
						        			  });
			         
						        		  }
				        			  } else {
					        			  hantei = 0;
					        			  handler.post(new Runnable() {
					        				  public void run() {
					        					  ll.removeView(sv); 
					        					  sv = new SampleView(getApplication());
					        					  ll.addView(sv);
					        					  return;
					        				  }
					        			  });
		         
					        		  }
				        		  } else {
				        			  hantei = 0;
				        			  handler.post(new Runnable() {
				        				  public void run() {
				        					  ll.removeView(sv); 
				        					  sv = new SampleView(getApplication());
				        					  ll.addView(sv);
				        					  return;
				        				  }
				        			  });
	         
				        		  }
				        	  } else {
				        		  hantei = 0;
				        		  handler.post(new Runnable() {
				        			  public void run() {
				        				  ll.removeView(sv);
				        				  sv = new SampleView(getApplication());
				        				  ll.addView(sv);
				        				  return;
				        			  }
				        		  });
	   
				        	 } 

			        	//  } // ifend */

			          } catch (UnknownHostException e) {
			        	  e.printStackTrace();
			          } catch (IOException e) {
			        	  e.printStackTrace();
			          }

			          if( socket != null){
			        	  try {
			        		  socket.close();
			        		  socket = null;
			        	  } catch (IOException e) {
			        		  e.printStackTrace();
			        	  }
			          }
				}
	  	           
 	      },0,100);
      }
}

class SampleView extends View {
     //座標認識用にx,yの型を定義
     static float x;
	 static float y;
     
     //初期値設定
     public SampleView(Context cn) {
   	  super(cn);
   	  x = 100; y = 600;
     }
     
     
     
     public boolean onTouchEvent(MotionEvent e) {
	    x = e.getX();
	    y = e.getY();

	    //更新を有効にする
	    this.invalidate();
	    return true;
     
     }
     
     
     protected void onDraw(Canvas cs) {
	   super.onDraw(cs);

	   //描画方法の設定
	   Paint p = new Paint();
	   
	   if (MainActivity.hantei == 0) {
		   p.setColor(Color.BLACK);
	   } else if (MainActivity.hantei == 1) {
		   p.setColor(Color.RED);
	   }
	   
	   
           p.setStyle(Paint.Style.FILL);
           p.setStrokeWidth(8);
       
       //円の描画
       cs.drawCircle( x, y, 50, p);
      
     }
  
}