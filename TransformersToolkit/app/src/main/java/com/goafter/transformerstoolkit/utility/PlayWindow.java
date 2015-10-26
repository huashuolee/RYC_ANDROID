package com.goafter.transformerstoolkit.utility;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;

import com.goafter.transformerstoolkit.R;

import java.io.File;

public class PlayWindow extends AppCompatActivity {
    CustomVideoView vView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_window);
        vView = (CustomVideoView) findViewById(R.id.vView);
        lnMeplayer();



    }


    public void lnMeplayer(){
        //方法二: videoview
        Uri uri = Uri.parse("/sdcard/video1.mp4");
        File file = new File("/sdcard/video1.mp4");
        file.getName();
        vView.setMediaController(new MediaController(this));
        vView.setVideoURI(uri);
        vView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(PlayWindow.this, "视频播放完毕", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        Toast.makeText(this, "正在播放 "+file.getName(),Toast.LENGTH_SHORT).show();
        vView.start();



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
