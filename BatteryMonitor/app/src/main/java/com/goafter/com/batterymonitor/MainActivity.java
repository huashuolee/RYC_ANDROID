package com.goafter.com.batterymonitor;

import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tvCurrent = (TextView) findViewById(R.id.current);
        Button btnRefresh = (Button) findViewById(R.id.btnRefresh);


        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                BatteryManager batteryManager=(BatteryManager)getSystemService(BATTERY_SERVICE);
                String current_now = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW)+"";
                String current_average = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE)+"";
                String current_current = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER)+"";
                tvCurrent.setText(current_now + " " + current_average);*/
                BatteryManager btManager = new BatteryManager();

            }
        });


    }
}
