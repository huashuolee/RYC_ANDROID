package com.goafter.transformerstoolkit.utility;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.goafter.transformerstoolkit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class Weather extends Fragment {
    StringBuilder  builderaqi;
    TextView tvResult, tvAQI;
    TextView tvLocation;
    LocationClient mLocationClient;
    BDLocationListener mylistener;
    String locCity, locAddrStr, locDistrict, locDescribe;
    ArrayList<String> dailyinfo = new ArrayList<String>();
    LinearLayout dailyforcast;
    MyLogUtil logUtil = new MyLogUtil();
    public static final String TAG = "=============================";

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
        dailyforcast = (LinearLayout) view.findViewById(R.id.llDailyForcast);
        Button btnGetData = (Button) view.findViewById(R.id.btnGetData);
        tvResult = (TextView) view.findViewById(R.id.tvResult);
        tvAQI = (TextView) view.findViewById(R.id.tvAQI);
        btnGetData.setOnClickListener(new GetWeatherData());
        btnGetData.setVisibility(View.GONE);
        tvLocation = (TextView) view.findViewById(R.id.tvLocation);
        tvLocation.setText(getActivity().getString(R.string.quering));
        //获取当前位置, 以及天气
        getLocation();
        return view;
    }


    class GetWeatherData implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            getLocation();


        }
    }


    class myLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            StringBuilder sb = new StringBuilder();
            sb.append("getCity " + bdLocation.getCity() + "\n");
            sb.append("getDistrict " + bdLocation.getDistrict() + "\n");
            sb.append("getLocationDescribe " + bdLocation.getLocationDescribe() + "\n");
            sb.append("getAddrStr " + bdLocation.getAddrStr() + "\n");
            sb.append("getNetworkLocationType " + bdLocation.getNetworkLocationType() + "\n");
            logUtil.e("Transformers Tools", sb.toString());
            locCity = bdLocation.getCity();
            locDistrict = bdLocation.getDistrict();
            locDescribe = bdLocation.getLocationDescribe();
            locAddrStr = bdLocation.getAddrStr();
            if (mLocationClient.isStarted()) {
                mLocationClient.stop();
            }

            queryWeatherBaiduAPI();
        }

    }

    public void getLocation() {
        mLocationClient = new LocationClient(getActivity());
        initConfig();
        mylistener = new myLocationListener();
        mLocationClient.registerLocationListener(mylistener);
        safeStart();

    }

    public void initConfig() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 0;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);

    }

    public void queryWeatherBaiduAPI() {
        StringBuffer sb = new StringBuffer();
        sb.append(locAddrStr);

        if (locAddrStr != null) {
            String city = locCity.substring(0, locCity.indexOf("市"));
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                city = swCharset(city);
            }
            String url = UrlConst.WEATHER  + city;
            tvLocation.setText(locDescribe);
            updateaqi(url);

        } else {
            Toast.makeText(getActivity(), "刷新失败，请重试", Toast.LENGTH_LONG);

        }


    }


    public void safeStart() {
        if (mLocationClient.isStarted()) {
            mLocationClient.stop();

        } else {
            mLocationClient.start();

        }
    }



    private void updateaqi(String url) {
        new AsyncTask<String, Void, StringBuilder>() {
            protected StringBuilder doInBackground(String... params) {

                try {
                    URL url = new URL(params[0]);
                    URLConnection connection = url.openConnection();
                    connection.setRequestProperty("apikey", UrlConst.BAIDU_APIKEY);
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is, "utf-8");
                    BufferedReader br = new BufferedReader(isr);
                    builderaqi = new StringBuilder();
                    String line = null;

                    while ((line = br.readLine()) != null) {
                        builderaqi.append(line);
                    }
                    return builderaqi;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return builderaqi;
            }

            @Override
            protected void onPostExecute(StringBuilder stringBuilder) {
                super.onPostExecute(stringBuilder);

                try {

                    JSONObject jsonObject = new JSONObject(builderaqi.toString());
                    JSONArray array = jsonObject.getJSONArray("HeWeather data service 3.0");
                    JSONObject allData = array.getJSONObject(0);

                    //get the current weather data
                    JSONObject now = allData.getJSONObject("now");
                    String tmp = now.getString("tmp");
                    String txt = now.getJSONObject("cond").getString("txt");
                    String direction_wind = now.getJSONObject("wind").getString("dir");
                    String sc = now.getJSONObject("wind").getString("sc");
                    String sug_drsg = allData.getJSONObject("suggestion").getJSONObject("drsg").getString("txt");
                    String[] display1 = new String[]{"城市：" + locCity, "温度：" + tmp + "摄氏度", txt, direction_wind + ": " + sc, sug_drsg};
                    String result2 = "";
                    for (String i : display1) {
                        result2 += i + "\r\n";

                    }
                    tvResult.setText(result2);
                    // get the AQI Data
                    JSONObject aqi = allData.getJSONObject("aqi").getJSONObject("city");
                    String qlty = aqi.getString("qlty");
                    String pm10 = aqi.getString("pm10");
                    String pm25 = aqi.getString("pm25");
                    String so2 = aqi.getString("so2");
                    String[] display = new String[]{"空气质量: " + qlty, "PM2.5: " + pm25};
                    String result = "";
                    for (String i : display) {
                        result += i + "\r\n";
                    }
                    tvAQI.setText(result);
                    tvAQI.setTextSize(30);

                    //get the daily forecast data , 4 days
                    JSONArray daily_forecast = allData.getJSONArray("daily_forecast");
                    for (int i = 1; i < 5; i++) {
                        JSONObject JO = daily_forecast.getJSONObject(i);
                        String date = JO.getString("date");
                        String cond = JO.getJSONObject("cond").getString("txt_d");
                        String tmax = JO.getJSONObject("tmp").getString("max");
                        String tmin = JO.getJSONObject("tmp").getString("min");
                        String winddir = JO.getJSONObject("wind").getString("dir");
                        if (winddir.equals("无持续风向")) {
                            winddir = "风向";
                        }
                        String windsc = JO.getJSONObject("wind").getString("sc");
                        String[] eachItem = new String[]{date, cond, "温度: " + tmin + " ~ " + tmax, winddir + ": " + windsc};
                        String result1 = "";
                        for (String ii : eachItem) {
                            result1 += ii + "\r\n";
                        }
                        dailyinfo.add(result1);

                    }
                    getDailyInfo();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }.execute(url);

    }


    private void getDailyInfo() {
        if (null != dailyforcast) {
        }
        for (int i=0 ; i < dailyinfo.size(); i++) {
            TextView tv = new TextView(getActivity());
            tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
            tv.setText(dailyinfo.get(i));
            dailyforcast.addView(tv);

        }


    }
    //靠，4.4 UTF-8 天气city=北京，不支持。需要转码成ISO8859-1。但是5.1

    public String swCharset(String str) {

        try {
            byte[] tmp = str.getBytes("utf-8");
            str = new String(tmp, "ISO8859-1");
            return str;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return str;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
