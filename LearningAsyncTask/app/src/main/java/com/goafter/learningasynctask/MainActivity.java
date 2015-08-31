package com.goafter.learningasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    Button btnAction;
    TextView tvSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvSource = (TextView) findViewById(R.id.tvSource);
        btnAction = (Button) findViewById(R.id.btnAction);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeAction("http://www.baidu.com");
            }
        });
    }

    public void takeAction(String url){

        new AsyncTask<String, Void, StringBuilder>(){

            @Override
            protected StringBuilder doInBackground(String ... url) {
                try {
                    URL url1 = new URL(url[0]);
                    URLConnection connection = url1.openConnection();
                    InputStreamReader ins = new InputStreamReader(connection.getInputStream(),"utf-8");
                    BufferedReader br = new BufferedReader(ins);
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = br.readLine())!= null){
                        builder.append(line);
                    }
                    return  builder;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return  null;

            }

            @Override
            protected void onPostExecute(StringBuilder builder) {
                super.onPostExecute(builder);
                tvSource.setText(builder.toString());
            }
        }.execute(url);


    }



}
