package com.goafter.section2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Main2ActivityBroadCast extends AppCompatActivity {

    IntentFilter filter;
    NetworkReceiver networkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_activity_broad_cast);
        filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkReceiver = new NetworkReceiver();
        registerReceiver(networkReceiver, filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkReceiver);
    }

    class NetworkReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(Main2ActivityBroadCast.this, "Network change", Toast.LENGTH_SHORT).show();
        }
    }
}
