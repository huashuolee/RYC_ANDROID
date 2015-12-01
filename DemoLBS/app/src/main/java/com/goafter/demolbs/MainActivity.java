package com.goafter.demolbs;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    TextView tvDispaly;
    private  String provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取LocationManager
        tvDispaly = (TextView) findViewById(R.id.tvDisplay);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.NETWORK_PROVIDER;
        try {
            Location location = locationManager.getLastKnownLocation(provider);
            showLocation(location);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void showLocation(Location location){
        tvDispaly.setText("纬度：" + location.getLatitude()+"\n"+"经度： "+ location.getLongitude() );

        }
}
