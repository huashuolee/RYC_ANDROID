package huashuo.camera2;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity  {
    private Camera camera;
    private SurfaceView surfaceView;
    private File path;
    private boolean safeCapture = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button captureButton = (Button) findViewById(R.id.captureButton);
        captureButton.setOnClickListener(new capture());
        Button recordButton = (Button)findViewById(R.id.Record);


        surfaceView = (SurfaceView) this.findViewById(R.id.surface);
        SurfaceHolder holder = surfaceView.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        Log.e("RYC","MainActivity onCreate");
        holder.addCallback(new myPreview());


        //String path = Environment.getExternalStorageDirectory() + "/test/";
        path = new File(Environment.getExternalStorageDirectory()+"/test");
        if (!path.exists()){
            path.mkdir();
        }


    }

    public class capture implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (safeCapture){
                camera.takePicture(null,null,new pictureTaken());
                safeCapture=false;
            }

        }
    }


    public class pictureTaken implements Camera.PictureCallback{

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File JPG = new File(path, System.currentTimeMillis()+"RYC.jpg");
            try {
                FileOutputStream out = new FileOutputStream(JPG);
                out.write(data);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            camera.startPreview();
            safeCapture = true;
        }
    }
    public class myPreview implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera = Camera.open(0);
                Log.e("RYC", "Camera.open()");
                Camera.Parameters param = camera.getParameters();
                Log.e("RYC", param.flatten());
                param.setPreviewSize(320, 240);
                param.setPictureSize(320, 240);
                surfaceView.setLayoutParams(new RelativeLayout.LayoutParams(320, 240));
                camera.setParameters(param);
                camera.setPreviewDisplay(holder);
                camera.startPreview();
                safeCapture=true;
            } catch (IOException e) {
                e.printStackTrace();
                camera.release();
                Log.e("RYC", "camera.release()");
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            camera.stopPreview();
            camera.release();
            Log.e("RYC", "camera.release()");

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

    }


}
