package com.example.firesocket;

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
public class SubTerminal extends Activity {
      public SampleView sv;
      int a,b,d;
      float co1,co2;
      int port = 10000;
      String aa,bb,cc;
      static final Timer timer = new Timer();
      static int hantei = 0;
      String test = "ip";
      Intent intent;
     
      Handler handler = new Handler();
      
      public void onCreate(Bundle savedIntanceState) {
    	  super.onCreate(savedIntanceState);
    	  final RelativeLayout ll = (RelativeLayout) getLayoutInflater().inflate(R.layout.subter, null);
    	  setContentView(ll); 
    	  intent = getIntent();
    	  final Bundle bundle = intent.getExtras();  
    	  sv = new SampleView(getApplicationContext());
          ll.addView(sv);
          final String address = bundle.getString("ipa");
          // final String address = "192.168.2.100";
          
          StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
           
          Log.d(test,address);
          
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
			        	  
			        	  cc = aa + bb;
			        	  
						  String xza,yza; 
			        	  
			        	  Log.d(test,address);
			        	  
			        	  socket = new Socket(address,port);
			        	  PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
			        	  pw.println(cc);
              
			        	  BufferedReader br = new BufferedReader (new InputStreamReader(socket.getInputStream()));
			        	  String str = br.readLine();
			        	  
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
							        				  sv = new SampleView(getApplicationContext());
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
						        				  sv = new SampleView(getApplicationContext());
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
					        				  sv = new SampleView(getApplicationContext());
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
				        				  sv = new SampleView(getApplicationContext());
				        				  ll.addView(sv);
				        				  return;
				        			  }
				        		  });
	   
				        	 } // ifend 

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
	  	           
 	      },0,500);
      }
}

class SampleView extends View
{
     static float x,y;
     private float dx,dy,dx1,dy1;
	 //private Resources resources;
	 private Bitmap bgBitmap;
	 //private Object ObjectBitmap;
	 private int dp_w;
	 private int dp_h;
	 private int drow_h;
	 private int drow_s;
     
 	 public SampleView(Context cn)
     {
      super(cn);     
      
      // リソースからbitmapを作成
      bgBitmap = BitmapFactory.decodeResource(cn.getResources(), R.drawable.face);
      // WindowManager取得
      WindowManager wm = (WindowManager)cn.getSystemService(Context.WINDOW_SERVICE);
      // Displayインスタンス生成
      Display dp = wm.getDefaultDisplay();
      // ディスプレイサイズ取得
      dp_w = dp.getWidth();
      dp_h = dp.getHeight();
      // リサイズ画像の高さ
      drow_h = (dp_w / 2) * 3 + 80;
      // 描画始点の高さ
      drow_s = (dp_h - drow_h) / 2;
      
      //コンストラクタで、リソースの取得
      //resources = this.getContext().getResources();
      //bgBitmap = BitmapFactory.decodeResource(resources, R.drawable.face);
      //ObjectBitmap = BitmapFactory.decodeResource(resources, R.drawable.face);
      
      x = 100; y = 600;
     }
 	 
    public boolean onTouchEvent(MotionEvent e)
    {		
		dx = x-e.getX();
		dy = y-e.getY();
    	  
		dx1 = 250 - e.getX();
		dy1 = 250 - e.getY();

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
    		       this.invalidate();
    		       
    		       if(dx1<20)
    		       {
    		           if(dx1>-20)
    		           {	   
    		    	       if(dy1<20)
    		               {
    		    	           if(dy1>-20)
    		    	           {	   
    		    	    	      if(SubTerminal.hantei==1)
    		    	              {
    		    	                  if(e.getAction() == MotionEvent.ACTION_MOVE)
    		    	                  {
    		    	                	  Toast.makeText(getContext(),"成功", Toast.LENGTH_LONG).show();    		    	                  }
    		    	               } 
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
	   if (SubTerminal.hantei == 0) {
		   p.setColor(Color.BLACK);
	   } else if (SubTerminal.hantei == 1) {
		   p.setColor(Color.RED);
	   }
	  
       p.setStyle(Paint.Style.FILL);
       p.setStrokeWidth(8);
       
       //画像描画
       //cs.drawBitmap(bgBitmap,0,0,p);
       // イメージ画像リサイズ
       bgBitmap = Bitmap.createScaledBitmap(bgBitmap, dp_w, drow_h , true);  
       // 描画
       cs.drawBitmap(bgBitmap, 0, drow_s, null);       
       //円の描画
       cs.drawCircle( x, y, 50, p);
       cs.drawCircle(250,250,50,p);
     }
  
}