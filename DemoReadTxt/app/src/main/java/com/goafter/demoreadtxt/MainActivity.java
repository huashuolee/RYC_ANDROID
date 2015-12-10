package com.goafter.demoreadtxt;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    StringBuilder sb = new StringBuilder();
    MyView myView;
    String TAG = "=============";
    BufferedReader br;
    TextView tvDisplay;
    Button btnPre, btnNext;
    int pageSize = 400;
    int startNum = 0;
    int endNum = startNum + pageSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
        btnPre = (Button) findViewById(R.id.btnPre);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        btnPre.setOnClickListener(this);
        //myView = (MyView) findViewById(R.id.myView);
        loadBook();
        loadPage();
    }

    private void loadPage() {
        char[] buf = new char[1024];

        String str = sb.substring(startNum, startNum + pageSize);
        str.trim();
        Log.e(TAG, str.length() + "");
        tvDisplay.setTextSize(15);
        tvDisplay.setText(str);
        /*try {
            br.read(buf);
            String result = new String(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    private void prepage() {
        String str = sb.substring(startNum - pageSize, startNum);
        Log.e(TAG, str.length() + "");
        tvDisplay.setText(str);
    }

    private void loadBook() {
        String path = Environment.getExternalStorageDirectory().getPath() + "/a.txt";
        File file = new File(path);
        try {
            br = new BufferedReader(new FileReader(file));
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
            String line = null;
            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    sb.append(line + "\n");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                loadPage();
                startNum = startNum + pageSize;
                break;
            case R.id.btnPre:
                startNum = startNum - pageSize;
                prepage();
                break;
        }
    }
}
