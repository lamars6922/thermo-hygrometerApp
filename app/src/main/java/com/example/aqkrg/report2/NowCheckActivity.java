package com.example.aqkrg.report2;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NowCheckActivity extends AppCompatActivity implements Runnable, Handler.Callback {

    final String DATA_URL = "웹 서버";
    Handler handler;
    TextView textView;
    Button btn;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_check);
        SharedPreferences preferences = getSharedPreferences("MYINFO", 0);
        StringBuilder strs = new StringBuilder();
        handler = new Handler(this);
        textView = (TextView) findViewById(R.id.txtNow);
        btn = (Button)findViewById(R.id.btnCheck);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        thread = new Thread(NowCheckActivity.this);
        thread.start();
    }
    @Override
    public boolean handleMessage(Message message) {
        String jsonString = message.getData().getString("welcome");
        StringBuilder string = new StringBuilder();
        String temp, humi;
        try {
            JSONObject jobj = new JSONObject(jsonString);
            temp = jobj.getString("temp");
            humi = jobj.getString("humidity");
            string.append("temp : " + temp + "\n");
            string.append("humidity : " + humi);

            textView.setText(string.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void run() {
        URL url;
        Bundle bundle = new Bundle();
        Message msg = Message.obtain();
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader;
        String tmp;
        try {
            url = new URL( DATA_URL );
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setConnectTimeout(10000);
            con.setUseCaches(false);
            if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(url.openStream()));
                while((tmp = reader.readLine()) != null) {
                    stringBuilder.append(tmp);
                }
                reader.close();
                bundle.putString("welcome", stringBuilder.toString());
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}

