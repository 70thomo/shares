package com.example.socketterminal;

import java.io.IOException;
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
import android.view.*;
import android.widget.*;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
      SampleView sv;
      int a,b,d;
      float co1,co2;
      String address ="192.168.2.102";
      int port = 10000;
      String aa,bb,cc;
      final Timer timer = new Timer();
      
      public void onCreate(Bundle savedIntanceState) {
    	  super.onCreate(savedIntanceState);
    	  LinearLayout ll = new LinearLayout(this);
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
              bb = String.valueOf(co02);
              cc = aa + bb;
        	  StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
              socket = new Socket(address,port);
              PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
              pw.println(cc);

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
   	  x = 400; y = 400;
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
	   p.setColor(Color.BLACK);
           p.setStyle(Paint.Style.FILL);
           p.setStrokeWidth(8);
       
       //円の描画
       cs.drawCircle( x, y, 50, p);
      
     }
  
}