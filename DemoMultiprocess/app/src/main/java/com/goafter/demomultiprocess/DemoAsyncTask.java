package com.goafter.demomultiprocess;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DemoAsyncTask extends AppCompatActivity {
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_async_task);
        Button btcClick = (Button) findViewById(R.id.btnClick);
        tv = (TextView) findViewById(R.id.tv);
        btcClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new update().execute();
            }
        });
    }

    class update extends AsyncTask<Void, Void,Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            publishProgress();
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            tv.setText("Nice to meet you ");
        }
    }
}
