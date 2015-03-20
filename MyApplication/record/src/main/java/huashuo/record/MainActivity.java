package huashuo.record;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends Activity {
    String TAG="Amazoning Android";
    SurfaceView mySurfaceView;
    SurfaceHolder mHolder;
    Button recordButton;
    Button stopButton;
    Button captureButton;
    MediaRecorder mediaRecorder;
    Camera mCamera=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        recordButton = (Button) findViewById(R.id.start);
        stopButton = (Button) findViewById(R.id.stop);
        captureButton = (Button) findViewById(R.id.capture);
        mySurfaceView = (SurfaceView) findViewById(R.id.mySurfaceView);
        mySurfaceView.setFocusable(true);
        mySurfaceView.setFocusableInTouchMode(true);
        mySurfaceView.setClickable(true);
        mHolder = mySurfaceView.getHolder();
        mHolder.addCallback(new cameraPreview());

    }


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
            mCamera.startPreview();
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

            mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_720P));
            mediaRecorder.setOutputFile(videofile.getAbsolutePath());
            mediaRecorder.setOrientationHint(90);
            Log.e(TAG,"SET 15");
            //mediaRecorder.setVideoFrameRate(15);

            Log.e(TAG,"SET 15 DONE");
            mediaRecorder.prepare();
            mediaRecorder.start();
            recordButton.setVisibility(View.GONE);
            stopButton.setVisibility(View.VISIBLE);
            captureButton.setVisibility(View.GONE);
            Log.e(TAG,"START RECORDING");

            stopButton.setEnabled(true);
        }catch (Exception e){
            Log.e("huashuo","can't record");
            e.printStackTrace();
        }

    }
    public void stopvideo(){
        mediaRecorder.stop();
        mediaRecorder.release();
        stopButton.setVisibility(View.GONE);
        recordButton.setVisibility(View.VISIBLE);
        captureButton.setVisibility(View.VISIBLE);
        Log.e(TAG, "STOP RECORDING");
    }


    public void capture(View v){
        mCamera.takePicture(null,null,new takejpeg());


    }

    private class takejpeg implements PictureCallback{
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File jpgfile = new File(Environment.getExternalStorageDirectory()+"/test", System.currentTimeMillis()+".jpg");
            try {
            FileOutputStream out = new FileOutputStream(jpgfile);
            out.write(data);
            out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        mCamera.startPreview();
        }

    }





}
