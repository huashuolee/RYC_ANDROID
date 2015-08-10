package com.goafter.learnsurfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by huashuolee on 2015/8/10.
 */
public class MyView extends SurfaceView implements SurfaceHolder.Callback{

    public  MyView(Context context){
        super(context);
        getHolder().addCallback(this);

    }
    public void draw(){
        Canvas canvas = getHolder().lockCanvas();

        getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
