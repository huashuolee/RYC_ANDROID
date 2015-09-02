package com.goafter.transformerstoolkit.utility;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.goafter.transformerstoolkit.R;

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

/**
 * Created by huashuolee on 2015/9/2.
 */

public class YouDao extends Fragment {

    Button btnSearch;
    TextView tvResult;
    EditText etWord;
    String line,url;
    StringBuilder builder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_youdao_dict, container, false);
        btnSearch  = (Button)view.findViewById(R.id.btnSearch);
        tvResult = (TextView)view.findViewById(R.id.tvDisplayResult);
        etWord = (EditText)view.findViewById(R.id.etWord);
        btnSearch.setOnClickListener(new Search());

        return view;
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
                        //String explains = new String(jsonObject.getString("explains"));
                        JSONArray array = jsonObject.getJSONArray("explains");

                        String result1 = "";
                        for (int i=0;i<array.length();i++){
                            Log.e("1111111",array.getString(i));
                            result1 += array.getString(i)+"\r\n";

                        }
                        tvResult.setText(result1);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                protected void onPreExecute() {
                    Toast.makeText(getActivity(), "玩命查询ing", Toast.LENGTH_SHORT).show();
                    super.onPreExecute();
                }
            }.execute(url);


        }



    }


}



