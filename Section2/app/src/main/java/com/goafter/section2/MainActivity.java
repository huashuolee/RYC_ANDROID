package com.goafter.section2;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {


    Button btnClick,btndialog;
    ProgressBar pbLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);
        btnClick = (Button) findViewById(R.id.btnClick);
        btndialog = (Button) findViewById(R.id.btndialog);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prograss = pbLoading.getProgress();
                prograss += 10;
                pbLoading.setProgress(prograss);
                Log.e("==========", prograss + "");
            }
        });
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("ATTENTION: XXXXXXXXX");
                dialog.setMessage("this is very important");
                dialog.show();
            }
        });


    }
}
