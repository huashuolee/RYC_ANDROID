package com.goafter.transformerstoolkit.utility;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


public class Weather extends Fragment {
    StringBuilder builder;
    TextView tvResult;
    TextView tvLocation;


    public Weather() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        Button btnGetData = (Button) view.findViewById(R.id.btnGetData);
        tvResult = (TextView) view.findViewById(R.id.tvResult);
        btnGetData.setOnClickListener(new GetWeatherData());
        update(UrlConst.WEATHER + "city=beijing");
        tvLocation = (TextView) view.findViewById(R.id.tvLocation);
        tvLocation.setText("北京　");


        return view;
    }

    class GetWeatherData implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String city = "city=beijing";
            String url = UrlConst.WEATHER + city;
            update(url);


        }
    }

    private void update(String url){
        new AsyncTask<String,Void,StringBuilder>(){
            protected StringBuilder doInBackground(String... params) {
                try {
                    URL url = new URL(params[0]);
                    URLConnection connection = url.openConnection();
                    connection.setRequestProperty("apikey", UrlConst.BAIDU_APIKEY);
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is,"utf-8");
                    BufferedReader br = new BufferedReader(isr);
                    builder = new StringBuilder();
                    String line = null;

                    while((line = br.readLine()) != null){
                        builder.append(line);
                    }
                    return builder;
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
/*
                Log.e("111111", builder.toString());
                try {
                    JSONObject  dataJson=new JSONObject(builder.toString()); //获得 dataJSON
                    JSONArray allDataJSONArray =dataJson.getJSONArray("HeWeather data service 3.0"); //  先获得JSONArray
                    JSONObject allDataJSONObject = allDataJSONArray.getJSONObject(0); //从JSONArray 中 获得JSONObject
                    JSONObject now = allDataJSONObject.getJSONObject("now"); //从JSONObject 中获取想要的OBJECT.
                    tvResult.setText(now.getJSONObject("wind").getString("dir"));
*/

                try {

                    JSONObject jsonObject = new JSONObject(builder.toString());
                    //JSONObject aqi = jsonObject.getJSONObject("aqi");
                    JSONArray array = jsonObject.getJSONArray("HeWeather data service 3.0");
                    JSONObject allData = array.getJSONObject(0);
                    JSONObject now = allData.getJSONObject("now");
                    Log.e("444444", allData.getString("basic"));
                    String fl = now.getString("fl");
                    String txt = now.getJSONObject("cond").getString("txt");
                    String direction_wind = now.getJSONObject("wind").getString("dir");
                    String sc = now.getJSONObject("wind").getString("sc");
                    String sug_drsg = allData.getJSONObject("suggestion").getJSONObject("drsg").getString("txt");
                    String[] display = new String[]{"体感温度："+fl+ "摄氏度",txt,direction_wind + ": "+ sc,sug_drsg};
                    String result = "";
                    for (String i : display){
                        result += i + "\r\n";

                    }
                    tvResult.setText(result);

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
