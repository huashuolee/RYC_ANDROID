package com.goafter.com.getlog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    Button btnGetLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetLog = (Button) findViewById(R.id.getlog);
        btnGetLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cmd = "mkdir /sdcard/logs";
                try {
                    Process p = Runtime.getRuntime().exec(cmd);
                    int status = p.waitFor();
                    Log.e("status code: ", status+"");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String getlogcmd = "logcat -v time -f /sdcard/logs/ap.log -r 2048 -n 10 &";
                try {
                    Process p = Runtime.getRuntime().exec(getlogcmd);
                    int status = p.waitFor();
                    Log.e("status code: ", status+"");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
