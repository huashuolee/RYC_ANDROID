package com.goafter.demomultiprocess;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main2);
        Button button = (Button) findViewById(R.id.btngood);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new Thread(new mythread()).start();

            }
        });
    }

    class mythread implements Runnable {
        @Override
        public void run() {
            while (true) {
                Log.e("ttttttttttttt", System.currentTimeMillis() + "");
            }
        }
    }

}
