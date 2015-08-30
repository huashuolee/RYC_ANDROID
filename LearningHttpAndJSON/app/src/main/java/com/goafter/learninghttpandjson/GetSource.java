package com.goafter.learninghttpandjson;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class GetSource extends Activity {

    Button btnGetSource;
    EditText etUrl;
    TextView tvSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_source);
        etUrl = (EditText) findViewById(R.id.etUrl);
        tvSource = (TextView) findViewById(R.id.tvSource);
        btnGetSource = (Button) findViewById(R.id.btnGetSource);
        btnGetSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = etUrl.getText().toString();
                getSource(url);

            }
        });


    }

    public void getSource(String url){
        new AsyncTask<String, Void, String>(){
            @Override
            protected String doInBackground(String... params) {
                try {
                    URL url = new URL(params[0]);
                    URLConnection connection = url.openConnection();
                    InputStream ins = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(ins,"utf-8");
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = br.readLine())!= null){
                        builder.append(line);

                    }
                    return builder.toString();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                tvSource.setText(s);

                super.onPostExecute(s);
            }
        }.execute(url);
    }


}
