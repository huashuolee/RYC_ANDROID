package com.goafter.com.demoactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("==============","onCreate");
    }

    @Override
    protected void onPause() {
        Log.e("==============","onPause");
        super.onPause();

    }

    @Override
    protected void onStart() {
        Log.e("==============","onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.e("==============","onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e("==============","onDestory");
        super.onDestroy();
    }


}
