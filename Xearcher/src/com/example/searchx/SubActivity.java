package com.example.searchx;

import java.net.URL;
import java.net.URLConnection;
import org.xmlpull.v1.XmlPullParser;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubActivity extends Activity {

	public static int a;
	public static int b;
	
	public class Button4ClickListener implements OnClickListener {
		public void onClick(View v) {
			finish();
		}
	}
	
	Intent intent1;   
	@SuppressLint("NewApi")
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = (LinearLayout) this.getLayoutInflater().inflate(R.layout.subw, null);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);
       
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build()); 
        
        // ボタン
        Button myBtn4 = (Button) findViewById(R.id.button4);
        myBtn4.setOnClickListener(new Button4ClickListener());
        
        
        //インテントの処理
        intent1 = getIntent();
        Bundle extras = intent1.getExtras();
        String ur = null;
        if (extras != null) {
        	ur = extras.getString("RESP");
        }       
  
        try{
            XmlPullParser parser = Xml.newPullParser();            
            URL url = new URL("http://192.168.2.102:8080/xx/serch?judge="+ur);
            URLConnection connection = url.openConnection();
            parser.setInput(connection.getInputStream(), "UTF-8");
            
            // タグ名(以下OK)
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
                        if(tag.equals("hyou")) {
                        	// ここから
                            String data1 = "1";  // yesの数
                            String data2 = "2";  // noの数                
                            Pattern pattern1 = Pattern.compile(data1);
                            Matcher matcher1 = pattern1.matcher(value);
                            Pattern pattern2 = Pattern.compile(data2);
                            Matcher matcher2 = pattern2.matcher(value);                          
                            while (matcher1.find()) {
                                a++;
                            }
                            while (matcher2.find()) {
                            	b++;
                            }
                            DrawView dview = new DrawView(getApplication());                         
                            layout.addView(dview);
                            // ここまでは文字列検索でつけたした。以前このif句はtview.setText(value);のみだった。
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

class DrawView extends View 
{

	  public DrawView(Context context) {
	    super(context);
	  }

	//フィールド:投票数
	  	int a01 = SubActivity.a;
	  	int b01 = SubActivity.b;

		//フィールド：円周角
		private int agr;
		private int dis;
		private int ind;

		//投票数・戻り値2

		int getAgr() {
			agr = 360*a01/(a01+b01);
			return agr;
		}

		int getDis() {


			dis = 360*b01/(a01+b01);
			return dis;
		}

		int getInd() {
			ind = agr+dis;
			return ind;
		}


	  public void onDraw(Canvas c) {

		//円周角:戻り値
		int agr= getAgr();
		int dis= getDis();
        int ind= getInd();

		c.drawColor(Color.WHITE);
		
	    Paint paint = new Paint();
	    c.drawColor(Color.WHITE);
	    paint.setStrokeWidth(5);
	    paint.setAntiAlias(true);

	    //グラフ描画
	    paint.setStyle(Paint.Style.FILL);
	    paint.setColor(Color.RED);
	    c.drawArc(new RectF(10.0f,10.0f,200.0f,150.0f), 0, agr, true, paint);
	    paint.setColor(Color.BLUE);
	    c.drawArc(new RectF(10.0f,10.0f,200.0f,150.0f), agr, dis, true, paint);



	    //結果表示
        paint.setColor(Color.BLACK);
        paint.setColor(Color.RED);
        c.drawText("賛成"+a01,280,100,paint);
        paint.setColor(Color.BLUE);
        c.drawText("反対"+b01,280,120,paint);
        paint.setColor(Color.GRAY);

	  }
}	