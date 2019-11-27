package com.example.aqkrg.report2;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadDataActivity extends AppCompatActivity implements Runnable, Handler.Callback {

    final String DATA_URL = "웹 서버";
    Handler handler;
    TextView textView;
    Button load, back;
    Thread thread;
    EditText year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);
        SharedPreferences preferences = getSharedPreferences("MYINFO", 0);
        StringBuilder strs = new StringBuilder();
        handler = new Handler(this);
        textView = (TextView)findViewById(R.id.txtList);
        year = (EditText)findViewById(R.id.editYear);
        month = (EditText)findViewById(R.id.editMonth);
        day = (EditText)findViewById(R.id.editDay);
        load = (Button)findViewById(R.id.btnLoad);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread = new Thread(LoadDataActivity.this);
                thread.start();
            }
        });
        back = (Button)findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public boolean handleMessage(Message message) {
        String jsonString = message.getData().getString("welcome");
        StringBuilder string = new StringBuilder();
        //String history;
        JSONArray history;
        String date, cyear, cmonth, cday;
        Double temp, humi;
        try {
            JSONObject jobj = new JSONObject(jsonString);
            JSONArray jarr = new JSONArray();
            jarr = jobj.getJSONArray("history");
            string.append("history" + "\n");
            cyear = year.getText().toString();
            cmonth = month.getText().toString();
            cday = day.getText().toString();
            for(int i=0;i<jarr.length();i++) {
                history = jarr.getJSONArray(i);
                if(cyear.equals(history.getString(0).substring(0, 4)) &&
                        cmonth.equals(history.getString(0).substring(5, 7)) &&
                        cday.equals(history.getString(0).substring(8, 10))) {
                    date = history.getString(0);
                    temp = history.getDouble(1);
                    humi = history.getDouble(2);
                    string.append("date : " + date.substring(0, 13)+"시" + "     temp : " + String.valueOf(Math.round(temp * 10) / 10d) + "     humi : " + String.valueOf(Math.round(humi * 10) / 10d) + "\n");
                }
            }
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
