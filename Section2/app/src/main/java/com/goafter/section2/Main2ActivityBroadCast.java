package com.goafter.section2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2ActivityBroadCast extends AppCompatActivity implements View.OnClickListener {

    IntentFilter filter;
    CustomReceiver customReceiver;
    Button btnBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_activity_broad_cast);
        btnBroadcast = (Button) findViewById(R.id.btnBroadCast);
        btnBroadcast.setOnClickListener(this);
        filter = new IntentFilter();
        filter.addAction("a");
        customReceiver = new CustomReceiver();
        registerReceiver(customReceiver, filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(customReceiver);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("a");
        sendBroadcast(intent);
    }

    class NetworkReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(Main2ActivityBroadCast.this, "Network change", Toast.LENGTH_SHORT).show();
        }
    }

    class CustomReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(Main2ActivityBroadCast.this, "Received.................", Toast.LENGTH_SHORT).show();
        }
    }
}
