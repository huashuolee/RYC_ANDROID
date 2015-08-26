package com.goafter.learninghttpandjson;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity_HttpGet extends AppCompatActivity {

    Button btnSearch;
    TextView tvResult;
    EditText etWord;
    String line,url;
    StringBuilder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity__http_get);
        btnSearch  = (Button) findViewById(R.id.btnSearch);
        tvResult = (TextView)findViewById(R.id.tvDisplayResult);
        etWord = (EditText)findViewById(R.id.etWord);
        btnSearch.setOnClickListener(new Search());

    }

    class Search implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String word = etWord.getText().toString();
            url = UrlConst.YOUDAOAPI + word;
            update();

        }

        public void update(){

            new AsyncTask<String,Void,StringBuilder>(){
                @Override
                protected StringBuilder doInBackground(String... params) {
                    try {
                        URL url = new URL(params[0]);
                        URLConnection connection = url.openConnection();
                        InputStream is = connection.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is,"utf-8");
                        BufferedReader br = new BufferedReader(isr);
                        builder = new StringBuilder();

                        while((line = br.readLine()) != null){
                            builder.append(line);
                            Log.e("1111111",line);
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return builder;
                }

                @Override
                protected void onPostExecute(StringBuilder stringBuilder) {
                    super.onPostExecute(stringBuilder);
                    try {

                        JSONObject jsonObject = new JSONObject(builder.toString()).getJSONObject("basic");
                        String explains = new String(jsonObject.getString("explains"));
                        //tvResult.setText(explains);


                        JSONArray array = jsonObject.getJSONArray("explains");

                        String result1 = "";
                        for (int i=0;i<array.length();i++){
                           // JSONObject lan = array.getString(i);
                            Log.e("1111111",array.getString(i));
                            result1 += array.getString(i)+"\r\n";

                        }
                        tvResult.setText(result1);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }
            }.execute(url);


        }



    }


}
