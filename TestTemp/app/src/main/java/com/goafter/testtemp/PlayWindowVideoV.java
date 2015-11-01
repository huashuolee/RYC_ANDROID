package com.goafter.testtemp;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;

public class PlayWindowVideoV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE;
        window.setAttributes(params);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_window_video_v);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        CustomVideoView videoV = (CustomVideoView) findViewById(R.id.videoV);
        videoV.setVideoPath(bundle.getString("uri"));
        MediaController mController = new MediaController(this);
        videoV.setMediaController(mController);
        videoV.start();


    }




}
