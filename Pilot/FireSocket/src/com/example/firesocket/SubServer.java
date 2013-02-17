package com.example.firesocket;

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
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.*;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

@SuppressLint("NewApi")
public class SubServer extends Activity implements Runnable{
    
    int port = 10000;
    public SampleViewww sv;
    public static int hantei = 0;
    volatile Thread runner = null;
    Handler mHandler = new Handler();
    final Timer timer = new Timer();
    String aa,bb,cc;
    float co1,co2;
    ServerSocket serverSocket = null;
    Intent intent;
    Handler handler = new Handler();
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
  	  super.onCreate(savedInstanceState);
  	  final RelativeLayout ll = (RelativeLayout) getLayoutInflater().inflate(R.layout.subser, null);
  	  setContentView(ll); 
  	  intent = getIntent();
  	  
  	  sv = new SampleViewww(getApplicationContext());
      ll.addView(sv);
    	
  	  	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
	    timer.scheduleAtFixedRate(new TimerTask(){

 	    @Override
		public void run() {
	        
 	    	try {
 	    		serverSocket = new ServerSocket(port);
 
                // 接続があるまでブロック
                Socket socket = serverSocket.accept();
                
	        	  co1 = SampleViewww.x;
	        	  co2 = SampleViewww.y;
    
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
	        	  Log.d(aa,bb);

 
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
		        	  if (xzah-100 < co01 ) {
		        		  if (co01 < xzah+100) {
		        			  if (yzah-100 < co02) {
		        				  if (co02 < yzah+100) {
		        					  hantei = 1;
		        					  
		        				  } else {
				        			  hantei = 0;
				        			  handler.post(new Runnable() {
				        				  public void run() {
				        					  ll.removeView(sv); 
				        					  sv = new SampleViewww(getApplication());
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
			        					  sv = new SampleViewww(getApplication());
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
		        					  sv = new SampleViewww(getApplication());
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
		        				  sv = new SampleViewww(getApplication());
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
 	           
	    },0,100);
    }

	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}

class SampleViewww extends View
{
     static float x,y;
     private float dx,dy,dx1,dy1;
	 private Resources resources;
	 private Bitmap bgBitmap,hokuro;
	 //private Object ObjectBitmap;
	 private int dp_w;
	 private int dp_h;
	 private int drow_h;
	 private int drow_s;
     
 	 public SampleViewww(Context cn)
     {
      super(cn);     
      
      // リソースからbitmapを作成
      bgBitmap = BitmapFactory.decodeResource(cn.getResources(), R.drawable.face);
      hokuro = BitmapFactory.decodeResource(cn.getResources(), R.drawable.hoc);
      // WindowManager取得
      //WindowManager wm = (WindowManager)cn.getSystemService(Context.WINDOW_SERVICE);
      // Displayインスタンス生成
      //Display dp = wm.getDefaultDisplay();
      // ディスプレイサイズ取得
      //dp_w = dp.getWidth();
      //dp_h = dp.getHeight();
      // リサイズ画像の高さ
      //drow_h = (dp_w / 2) * 3;
      // 描画始点の高さ
      //drow_s = (dp_h - drow_h) / 2;
      
      //コンストラクタで、リソースの取得
      resources = getContext().getResources();
      bgBitmap = BitmapFactory.decodeResource(resources, R.drawable.face);
      hokuro = BitmapFactory.decodeResource(resources, R.drawable.hoc);
      //ObjectBitmap = BitmapFactory.decodeResource(resources, R.drawable.face);
      
      x = 100; y = 600;
     }
 	 
    public boolean onTouchEvent(MotionEvent e)
    {		
		dx = x-e.getX();
		dy = y-e.getY();
    	  
		dx1 = 400 - e.getX();
		dy1 = 300 - e.getY();

    	if(dx<100)
    	{
    		if(dx>-100)
    		{
    		   if(dy<100)
    		   {
    		      if(dy>-100)
    		      {
    		       x = e.getX();
    	           y = e.getY();
    		       invalidate();
    		       
    		       if(dx1<100)
    		       {
    		           if(dx1>-100)
    		           {	   
    		    	       if(dy1<100)
    		               {
    		    	           if(dy1>-100)
    		    	           {	   
    		    	    	      //if(SubServer.hantei==1)
    		    	              //{
    		    	                  if(e.getAction() == MotionEvent.ACTION_MOVE)
    		    	                  {
    		    	                	  Toast.makeText(getContext(),"成功", Toast.LENGTH_LONG).show();
    		    	                  }
    		    	               //} 
    		    	            }
    		    	         }
    		             }
    		       }
    		           
    		           return true;
    	          }
    	          
    	          }
    	       }
    		   else
    		   {
    		   return false;	
    		   }
    		}
    		else
    		{
    		return false;
    		}
    		
    	
    	
    	
    	return true;
    	
    }
       
     public void onDraw(Canvas cs)
     {      
       super.onDraw(cs);
       
	   //描画方法の設定
	   Paint p = new Paint();
	   if (SubServer.hantei == 0) {
		   p.setColor(Color.BLACK);
	   } else if (SubServer.hantei == 1) {
		   p.setColor(Color.RED);
	   }
	  
       p.setStyle(Paint.Style.FILL);
       p.setStrokeWidth(8);
       
       //画像描画
       cs.drawBitmap(bgBitmap,0,0,p);
       cs.drawBitmap(hokuro,x-47,y-55,p);
       // イメージ画像リサイズ
       //bgBitmap = Bitmap.createScaledBitmap(bgBitmap, dp_w, drow_h , true);  
       // 描画
       //cs.drawBitmap(bgBitmap, 0, drow_s, null);       
       //円の描画
       //cs.drawCircle( x, y, 50, p);
       cs.drawCircle(400,300,10,p);
     }
  
}