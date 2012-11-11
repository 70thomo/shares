package com.example.searchx;

import java.net.URL;
import java.net.URLConnection;
import org.xmlpull.v1.XmlPullParser;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends Activity {
	
	public class Button4ClickListener implements OnClickListener {

		public void onClick(View v) {
			finish();
		}

	}
	
	Intent intent1;
   
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subw);
        
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build()); 
        // �{�^��
        Button myBtn4 = (Button) findViewById(R.id.button4);
        myBtn4.setOnClickListener(new Button4ClickListener());
        
        // TextView�C���X�^���X�̎擾
        TextView tview = (TextView)findViewById(R.id.textview);
        
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
            
            // �^�O��(�ȉ�OK)
            String tag = "";
            String value = "";
            
            // XML�̉��
            for (int type = parser.getEventType(); type != XmlPullParser.END_DOCUMENT; type = parser.next()) {
                switch(type) {
                case XmlPullParser.START_TAG:
                    tag = parser.getName();
                    break;
                case XmlPullParser.TEXT:
                    value = parser.getText();
                    // �󔒂Ŏ擾�������̂͑S�ď����ΏۊO�Ƃ���
                    if(value.trim().length() != 0) {
                        if(tag.equals("hyou")) {
                            tview.setText(value);
                        } 
                    }
                    break;
                case XmlPullParser.END_TAG: // �I���^�O
                    break;
                }
            }
        } catch (Exception e) {
            tview.setText(e.toString());
        }    
    }
}