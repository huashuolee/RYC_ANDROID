package com.goafter.testtemp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class TTPlayer extends AppCompatActivity {

    MediaPlayer mp;
    Button btnPlay, btnPause, btnStop;
    SurfaceHolder holder;
    SurfaceView videoSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttplayer);
        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnPause = (Button)findViewById(R.id.btnPause);
        btnStop = (Button)findViewById(R.id.btnStop);
        videoSurface = (SurfaceView)findViewById(R.id.videoSurface);
        holder = videoSurface.getHolder();
        holder.addCallback(new control());
        btnPlay.setOnClickListener(new myclicklistener());
        btnPause.setOnClickListener(new myclicklistener());
        btnStop.setOnClickListener(new myclicklistener());

    }

    class myclicklistener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnPlay:
                    mp.start();
                    break;
                case R.id.btnPause:
                    mp.pause();
                    break;
                case R.id.btnStop:
                    mp.stop();
                    mp.reset();
                    mp.release();
                    break;
                default:
                    break;
            }
        }
    }


    class control implements SurfaceHolder.Callback{
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Uri uri = Uri.parse("file:///sdcard/video.mp4");
            mp = MediaPlayer.create(getApplication(),uri);
            mp.setDisplay(holder);


        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

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
