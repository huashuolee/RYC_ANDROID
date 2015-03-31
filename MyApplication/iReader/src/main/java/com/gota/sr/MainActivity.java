package com.gota.sr;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    String TAG="Amazoning Android";
    SurfaceView mySurfaceView;
    SurfaceHolder mHolder;
    Button recordButton;
    Button stopButton;
    Button captureButton;
    MediaRecorder mediaRecorder;
    Camera mCamera=null;
	ReadView readView;
	BufferedReader reader;
	CharBuffer buffer = CharBuffer.allocate(8000);
	int position;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		readView = (ReadView) findViewById(R.id.read_view);
        recordButton = (Button) findViewById(R.id.start);
        stopButton = (Button) findViewById(R.id.stop);
        mySurfaceView = (SurfaceView) findViewById(R.id.mySurfaceView);
        mySurfaceView.setFocusable(true);
        mySurfaceView.setFocusableInTouchMode(true);
        mySurfaceView.setClickable(true);
        mHolder = mySurfaceView.getHolder();
        mHolder.addCallback(new cameraPreview());
		
		loadBook();
		loadPage(0);
	}


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private Camera safeOpen(int id){
        try {
            if (mCamera != null) {
                mCamera.release();
            }
            mCamera = Camera.open(id);
        }catch (Exception e){
            Log.e(getString(R.string.app_name), "failed to open Camera");
            e.printStackTrace();

        }
        return mCamera;
    }
    public class cameraPreview  implements SurfaceHolder.Callback{
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mCamera = safeOpen(0);



            /*try {
                Camera.Parameters parameters = mCamera.getParameters();
                parameters.set("orientation","landscape");
                //parameters.setRotation(90);
                mCamera.setDisplayOrientation(0);
                mCamera.setPreviewDisplay(mHolder);
                mCamera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Configuration mConfiguration = getResources().getConfiguration();
            int ori = mConfiguration.orientation;
            if (ori == Configuration.ORIENTATION_LANDSCAPE){
                //mCamera.stopPreview();
                mCamera.setDisplayOrientation(0);
            }else if (ori == Configuration.ORIENTATION_PORTRAIT){
                //mCamera.stopPreview();
                mCamera.setDisplayOrientation(90);
            }
            try {
                mCamera.setPreviewDisplay(mHolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mCamera.startPreview();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            mCamera.stopPreview();
            mCamera.release();

        }
    }

    public void record(View v){
        switch (v.getId()){
            case R.id.start:
                recordvideo();
                break;
            case R.id.stop:
                stopvideo();
                break;
        }



    }

    public void recordvideo(){
        try {

            mCamera.unlock();
            File videofile = new File(Environment.getExternalStorageDirectory() + "/test/", System.currentTimeMillis() + ".mp4");
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setCamera(mCamera);
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_480P));
            mediaRecorder.setOutputFile(videofile.getAbsolutePath());
            mediaRecorder.prepare();
            mediaRecorder.start();
            Log.e(TAG,"START RECORDING");
            stopButton.setEnabled(true);
            recordButton.setEnabled(false);
        }catch (Exception e){
            Log.e("huashuo","can't record");
            e.printStackTrace();
        }

    }
    public void stopvideo(){
        mediaRecorder.stop();
        mediaRecorder.release();
        stopButton.setEnabled(false);
        recordButton.setEnabled(true);
        Log.e(TAG, "STOP RECORDING");
    }

    /**
	 * 将电子书都一部分到缓冲区
	 */
	private void loadBook() {
		AssetManager assets = getAssets();
		try {
			InputStream in = assets.open("documents/The Golden Compass.txt");
			Charset charset = CharsetDetector.detect(in);
			reader = new BufferedReader(new InputStreamReader(in, charset));
			reader.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 从指定位置开始载入一页
	 */
	private void loadPage(int position) {
		buffer.position(position);
		readView.setText(buffer);
	}
	
	/**
	 * 上一页按钮
	 */
	public void previewPageBtn(View view) {
		
	}
	
	/**
	 * 下一页按钮
	 */
	public void nextPageBtn(View view) {
		position += readView.getCharNum();
		loadPage(position);
		readView.resize();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
