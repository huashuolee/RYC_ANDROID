package com.goafter.demoservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnStartService,btnStopService,btnBind,btnUnBind;
    private MyService.CustomBinder binder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MyService.CustomBinder) service;
            binder.pringlog();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStartService = (Button) findViewById(R.id.btnStartService);
        btnStopService = (Button) findViewById(R.id.btnStopService);
        btnBind = (Button) findViewById(R.id.btnBindService);
        btnUnBind = (Button) findViewById(R.id.btnUnBindService);
        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnBind.setOnClickListener(this);
        btnUnBind.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStartService:
                Intent intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            case R.id.btnStopService:
                Intent intent1 = new Intent(this, MyService.class);
                stopService(intent1);
                break;
            case R.id.btnBindService:
                Intent intentBinder = new Intent(this, MyService.class);
                bindService(intentBinder, connection, BIND_AUTO_CREATE);

                break;
            case R.id.btnUnBindService:
                unbindService(connection);

                break;
            default:
                break;
        }
    }
}
