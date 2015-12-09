package com.goafter.demoreadtxt;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class MainActivity extends AppCompatActivity {
    StringBuilder sb = new StringBuilder();
    MyView myView;
    String TAG = "=============";
    BufferedReader br;
    TextView tvDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
        //myView = (MyView) findViewById(R.id.myView);
        loadBook();
        loadPage();
    }

    private void loadPage() {
        char[] buf = new char[3];
        try {
            br.read(buf);
            String result = new String(buf);
            Log.e(TAG, result);
            tvDisplay.setTextSize(15);
            tvDisplay.setText(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadBook() {
        String path = Environment.getExternalStorageDirectory().getPath() + "/a.txt";
        File file = new File(path);
        try {
            br = new BufferedReader(new FileReader(file));
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"gbk"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
