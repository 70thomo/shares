package com.example.socketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.app.*;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.*;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements Runnable{
    
    int port = 10000;
    public SampleView sv;
    static int hantei = 0;
    volatile Thread runner = null;
    Handler mHandler = new Handler();
    final Timer timer = new Timer();
    String aa,bb,cc;
    float co1,co2;
    ServerSocket serverSocket = null;
    
    Handler handler = new Handler();
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
    	final LinearLayout ll = new LinearLayout(getApplicationContext());
  	  	setContentView(ll); 
  	  	sv = new SampleView(getApplicationContext());
  	  	ll.addView(sv);
    	
  	  	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
	    timer.scheduleAtFixedRate(new TimerTask(){

 	    @Override
		public void run() {
	        
 	    	try {
 	    		serverSocket = new ServerSocket(port);
 
                // 接続があるまでブロック
                Socket socket = serverSocket.accept();
                
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
	        	  
	        	  cc = aa + bb;

 
                BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
	        	PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
	        	pw.println(cc);                
 
                String str = br.readLine();
                String xza,yza; 
	        	  xza = str.substring(0,4);
	        	  yza = str.substring(5,8);
	        	  
	        	  int xzah = Integer.parseInt(xza);
	        	  int yzah = Integer.parseInt(yza);
	        	  Log.d(xza,yza);
		        	  if (xzah-50 < co01 ) {
		        		  if (co01 < xzah+50) {
		        			  if (yzah-50 < co02) {
		        				  if (co02 < yzah+50) {
		        					  hantei = 1;
		        					  
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

		        	 } // ifend 

 
                if( socket != null){
                    socket.close();
                    socket = null;
                }
            
 
 	    	} catch (IOException e) {
 	    		e.printStackTrace();
 	    	}
 
 	    	if( serverSocket != null){
 	    		try {
 	    			serverSocket.close();
 	    			serverSocket = null;
 	    		} catch (IOException e) {
 	    			e.printStackTrace();
 	    		}
 	    	}
 	      }
 	           
	    },0,250);
    }

	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		
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