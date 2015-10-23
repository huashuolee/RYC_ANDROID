package com.goafter.testtemp;

import android.content.Intent;
import android.media.MediaPlayer;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;

public class PlayWindow extends AppCompatActivity {

    MediaPlayer mp;
    SurfaceView sfview;
    SurfaceHolder holder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play_window);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final Uri uri = Uri.parse(bundle.getString("uri"));
        sfview = (SurfaceView)findViewById(R.id.sfview);
        holder = sfview.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mp = new MediaPlayer().create(PlayWindow.this,uri);
                mp.setDisplay(holder);

                mp.start();
                MediaController mController = new MediaController(PlayWindow.this);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        Toast.makeText(PlayWindow.this,"视频播放完毕",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mp.release();

            }
        });




    }


}
