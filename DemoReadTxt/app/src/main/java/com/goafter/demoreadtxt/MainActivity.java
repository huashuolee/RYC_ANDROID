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
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button tvHome, tvPre, tvNext;
    TextView tvDisplay;
    int next = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNext = (Button) findViewById(R.id.tvNext);
        tvDisplay = (TextView) findViewById(R.id.tvResult);
        tvNext.setOnClickListener(this);
        readtxt();
    }

    private void readtxt() {
        File file = new File(Environment.getExternalStorageDirectory().getPath()+"/a.txt");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"gbk"));
            String line ;
            String result;
            StringBuilder sb = new StringBuilder();
            while (next < 10 & (line = br.readLine()) != null  ) {
                sb.append(line+"\n");
                next++;
                Log.e("111111111", line);
            }
            Log.e("22222222", line);
            tvDisplay.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
      next = 0;
        Log.e("================", "nextnextnext:  "+next);
    }
}
