package com.goafter.demolaunchanother;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent( );
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ComponentName comp = new ComponentName("com.goafter.demoping", "com.goafter.demoping.MainActivity");
                mIntent.setComponent(comp);
                mIntent.setAction("android.intent.action.VIEW");
                startActivity(mIntent);
            }
        });
    }
}
