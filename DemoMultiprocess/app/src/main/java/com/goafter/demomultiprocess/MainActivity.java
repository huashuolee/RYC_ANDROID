package com.goafter.demomultiprocess;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView tvDisplay;
    Handler handler = new Handler(new Handler.Callback(){
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    tvDisplay.setText("Nice to meet you");
                    break;
                default:
                    break;
            }
            if (msg.what == 1) {
                tvDisplay.setText("Good good play, Hard hard study");
            }
            return true;
        }
    });
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnChangeText:
                new mythread().start();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnChangeText = (Button) findViewById(R.id.btnChangeText);
        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
        btnChangeText.setOnClickListener(this);
    }

    class mythread extends Thread {
        @Override
        public void run() {
/*            while (true) {
                Log.e("TTTTTTTTTTT", System.currentTimeMillis() + "");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);

        }
    }
}
