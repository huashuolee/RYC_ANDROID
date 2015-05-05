package huashuo.learning_bitmap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class MainActivity extends ActionBarActivity {
    ImageView iv,iv1;
    Bitmap bmp;
    Camera mCamera=null;
    SurfaceHolder mHolder;
    Button ka;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView)findViewById(R.id.imageView);
        //bmp = BitmapFactory.decodeFile("/mnt/sdcard/DCIM/Camera/1.jpg");
        //iv.setImageBitmap(bmp);
        ka = (Button)findViewById(R.id.ka);
        SurfaceView mySurface = (SurfaceView)findViewById(R.id.mysurfaceview);
        mHolder = mySurface.getHolder();
        mHolder.addCallback(new cPreview());
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        ka.setOnClickListener(new ka());



    }
    public class ka implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            mCamera.takePicture(null,null,new takepic());
        }
    }

    public class takepic implements Camera.PictureCallback{
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            //方法一
          /*  File jpg = new File("/sdcard/test/"+System.currentTimeMillis()+".jpg");
            try {
                FileOutputStream outputStream = new FileOutputStream(jpg);
                outputStream.write(data);
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mCamera.startPreview();
            uri = Uri.fromFile(jpg);
            iv.setImageURI(uri);*/
            //方法二
            if (data !=null){
                Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream("/sdcard/test/"+System.currentTimeMillis()+"_A.jpg");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //旋转90
                Matrix matrix = new Matrix();
                matrix.setRotate(90);
                bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                bmp.compress(Bitmap.CompressFormat.JPEG,100,out);
                iv.setImageBitmap(bmp);
                mCamera.startPreview();
            }
        }
    }

    public Camera safeOpen(int id){
        try {
            if (mCamera!=null){
                mCamera.release();
            }
            mCamera = Camera.open(id);
        } catch (Exception e) {
            Log.e(getString(R.string.app_name),"fail to open camera");
            e.printStackTrace();
        }
        return mCamera;

    }

    public class cPreview implements SurfaceHolder.Callback{
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mCamera=safeOpen(0);
            mCamera.setDisplayOrientation(90);

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
