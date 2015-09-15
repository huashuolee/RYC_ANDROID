package com.goafter.testtemp;

import android.app.Activity;
import android.os.Bundle;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;

public class BAIDUMAP extends Activity {
    public MapView mMapView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mMapView = (MapView) findViewById(R.id.bmapView);
    }



}
