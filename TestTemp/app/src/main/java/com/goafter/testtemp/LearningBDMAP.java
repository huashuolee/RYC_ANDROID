package com.goafter.testtemp;

import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;

public class LearningBDMAP extends AppCompatActivity {
    public LocationClient mLocationClient;
    public BDLocationListener myListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_learning_bdmap);

        mLocationClient = new LocationClient(getApplicationContext());
        myListener = new MyLocationListener2();
        mLocationClient.registerLocationListener(myListener);
        configOption();


    }

    public void configOption(){
        LocationClientOption opt = new LocationClientOption();
        opt.setLocationMode();
    }


    private class MyLocationListener2 implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

        }
    }
}
