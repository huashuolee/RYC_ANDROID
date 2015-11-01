package com.goafter.testtemp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TTPlayer extends AppCompatActivity {

    Button btnPlay, btnPause, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttplayer);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnPlay.setOnClickListener(new myclicklistener());
        btnPause.setOnClickListener(new myclicklistener());
        btnStop.setOnClickListener(new myclicklistener());

    }

    class myclicklistener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btnPlay:
                    Intent intent = new Intent(TTPlayer.this, PlayWindow.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("uri", "file:///sdcard/video1.mp4");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.btnPause:
                    Intent intentv = new Intent(TTPlayer.this, PlayWindowVideoV.class);
                    Bundle bundlev = new Bundle();
                    bundlev.putString("uri", "file:///sdcard/video1.mp4");
                    intentv.putExtras(bundlev);
                    startActivity(intentv);
                    break;
                default:
                    break;
            }
        }
    }

}
