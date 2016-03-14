package com.goafter.demobroadcast;

import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnSend, btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnScan = (Button) findViewById(R.id.btnScan);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("a");
                sendBroadcast(intent);
            }
        });

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {

                    Intent intent = new Intent("android.intent.action.MEDIA_MOUNTED");
                    intent.setData(Uri.parse("file:///mnt/sdcard/"));
                    sendBroadcast(intent);
                } else {
                    MediaScannerConnection.scanFile(MainActivity.this, new String[]{"/sdcard/video1.mp4"}, null, null);
                }
            }
        });
    }
}
