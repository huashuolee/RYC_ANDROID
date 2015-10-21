package com.goafter.testtemp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;


public class VideoPlayer extends AppCompatActivity {

    private Button bplay,bpause,bstop;
    private MediaPlayer mp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_video_player);

        bplay = (Button)findViewById(R.id.play);
        bpause = (Button)findViewById(R.id.pause);
        bstop = (Button)findViewById(R.id.stop);
        File file = new File(Environment.getExternalStorageDirectory()+"/DCIM/Camera/video.mp4");
        Uri uri = Uri.parse("file://"+Environment.getExternalStorageDirectory()+"/ts2.mp3");
        mp = MediaPlayer.create(this,uri);

        bplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
/*                try {
                    mp.setDataSource("file:///sdcard/DCIM/camera/video.mp4");
                    //mp.prepareAsync();
                    mp.prepare();
                    mp.start();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
            }
        });

        bpause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mp != null){
                    mp.pause();
                }
            }
        });

        bstop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mp != null){
                    mp.stop();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if(mp != null)
            mp.release();
        super.onDestroy();
    }



}
