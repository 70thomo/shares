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
	
	// 1stChildren
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = (LinearLayout) this.getLayoutInflater().inflate(R.layout.subw, null);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);
       
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build()); 
        
        // �{�^��
        Button myBtn4 = (Button) findViewById(R.id.button4);
        myBtn4.setOnClickListener(new Button4ClickListener());
        
        a = 0;
        b = 0;
        
        //�C���e���g�̏���
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
                    if(value.trim().length() != 0) {
                        if(tag.equals("hyou")) {
                            String data1 = "1";  // yes�̐�
                            String data2 = "2";  // no�̐�                
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
    }
}

@SuppressLint({ "DrawAllocation"})
class DrawView extends View 
{

	  public DrawView(Context context) {
	    super(context);	    
	  }

		//�t�B�[���h�F�~���p
		private int agr;
		private int dis;
		private int ind;
		private int mid;

		//���[���E�߂�l2
		int getA() {
			int a = SubActivity.a;
			return a;
			}
		
        int getB() {
        	int b = SubActivity.b;
        	return b;
        	}
        
		int getD() {
			int d = 0;
	        if (SubActivity.a == 0 && SubActivity.b == 0) {
	        	d = 1;
	        }   
			return d;
			}

		int getAgr() {
			//�߂�l2
			int a = getA();
			int b = getB();
			int d = getD();

			agr = 360*a/(a+b+d);
			return agr;
		}

		int getDis() {
			//�߂�l
			int a = getA();
			int b = getB();
			int d = getD();

			dis = 360*b/(a+b+d);
			return dis;
		}

		int getInd() {
			ind = agr+dis;
			return ind;
		}

		int getMid() {
			//�߂�l
			int a = getA();
			int b = getB();
			int d = getD();

			mid = 360*d/(a+b+d);
		    return mid;
		}

	  public void onDraw(Canvas c) {

		//�~���p:�߂�l
		int agr= getAgr();
		int dis= getDis();
        int ind= getInd();
	    int mid= getMid();

		c.drawColor(Color.WHITE);
		
	    Paint paint = new Paint();
	    c.drawColor(Color.WHITE);
	    paint.setStrokeWidth(5);
	    paint.setAntiAlias(true);

	    //�O���t�`��
	    paint.setStyle(Paint.Style.FILL);
	    paint.setColor(Color.RED);
	    c.drawArc(new RectF(10.0f,10.0f,200.0f,150.0f), 0, agr, true, paint);
	    paint.setColor(Color.BLUE);
	    c.drawArc(new RectF(10.0f,10.0f,200.0f,150.0f), agr, dis, true, paint);
	    paint.setColor(Color.GRAY);
	    c.drawArc(new RectF(10.0f,10.0f,200.0f,150.0f), ind, mid, true, paint);

	    int a = getA();
        int b = getB();
        int d = getD();

	    //���ʕ\��
        paint.setColor(Color.BLACK);
        c.drawText("����", 280, 80, paint);
        paint.setColor(Color.RED);
        c.drawText("�^��"+a,280,100,paint);
        paint.setColor(Color.BLUE);
        c.drawText("����"+b,280,120,paint);
        paint.setColor(Color.GRAY);
	  }
}