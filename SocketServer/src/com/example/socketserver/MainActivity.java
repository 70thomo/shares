package com.example.socketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import android.annotation.SuppressLint;
import android.app.*;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.*;
import android.util.Log;
import android.widget.*;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements Runnable{
    
    private ServerSocket mServer;
    private Socket mSocket;
    int port = 10000;
    volatile Thread runner = null;
    Handler mHandler = new Handler();
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  	  	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
         
        WifiManager wifiManager =  (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifIinfo = wifiManager.getConnectionInfo();
        int address = wifIinfo.getIpAddress();
        String ipAddressStr = ((address >> 0) & 0xFF) + "."
                + ((address >> 8) & 0xFF) + "." + ((address >> 16) & 0xFF)
                + "." + ((address >> 24) & 0xFF);
        TextView tv = (TextView) findViewById(R.id.tv1);
        tv.setText(ipAddressStr);
         
        if(runner == null){
            runner = new Thread(this);
            runner.start();
        }
        Toast.makeText(this, "スレッドスタート", Toast.LENGTH_SHORT).show();
    }
     
    public void run() {
        try {
        	while(true) {
        		
        	
            mServer = new ServerSocket(port);
            mSocket = mServer.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            String message;
            String str = in.readLine();
            String test = "test";
            String aaa = "アクセスされた";
            Log.d(aaa,str);
            Log.d(test,str);
            
            final StringBuilder messageBuilder = new StringBuilder();
            while ((message = in.readLine()) != null){
                messageBuilder.append(message);
            }
             
            mHandler.post(new Runnable() {
                 
                public void run() {
                    Toast.makeText(getApplicationContext(), messageBuilder.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            runner.start();
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}