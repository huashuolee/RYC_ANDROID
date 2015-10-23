package com.goafter.testtemp;

import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

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


    public class CustomVideoView extends VideoView {
        private int mVideoWidth;
        private int mVideoHeight;

        public CustomVideoView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
        }

        public CustomVideoView(Context context, AttributeSet attrs) {
            super(context, attrs);
            // TODO Auto-generated constructor stub
        }

        public CustomVideoView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            // TODO Auto-generated constructor stub
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            // TODO Auto-generated method stub
            // Log.i("@@@@", "onMeasure");

            //下面的代码是让视频的播放的长宽是根据你设置的参数来决定

            int width = getDefaultSize(mVideoWidth, widthMeasureSpec);
            int height = getDefaultSize(mVideoHeight, heightMeasureSpec);
            setMeasuredDimension(width, height);
        }
    }

}
