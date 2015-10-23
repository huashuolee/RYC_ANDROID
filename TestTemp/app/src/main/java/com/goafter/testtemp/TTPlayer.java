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
            System.err.println("555555555555");

            switch (v.getId()) {
                case R.id.btnPlay:
                    System.err.println("11111111");
                    Intent intent = new Intent(TTPlayer.this, PlayWindow.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("uri","file:///sdcard/video1.mp4");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    System.err.println("2222222222");
                    break;
                case R.id.btnPause:
                    System.err.println("3333333333333333");
                    Intent intentv = new Intent(TTPlayer.this, PlayWindowVideoV.class);
                    Bundle bundlev = new Bundle();
                    bundlev.putString("uri","file:///sdcard/video1.mp4");
                    intentv.putExtras(bundlev);
                    startActivity(intentv);
                    System.err.println("44444444444444444444");
                    break;
                default:
                    break;


            }


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ttplayer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
