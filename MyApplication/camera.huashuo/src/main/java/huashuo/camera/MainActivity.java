package huashuo.camera;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import android.app.Activity;
import android.content.ContentValues;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Android自带的Camera应用程序可以完成很多功能。但是当其不能满足我们需要的时候
 * 我们可以定制自己的Camera。Android提供了Camera类来辅助我们实现自己的Camera。
 * 这个例子就来定义一个自己的Camera
 * 首先，在Manifest中需要引入权限<uses-permission android:name="android:permission.CAMERA"/>
 * 我们需要用来存放取景器的容器，这个容器就是SurfaceView。
 * 使用SurfaceView的同时，我们还需要使用到SurfaceHolder，SurfaceHolder相当于一个监听器，可以监听
 * Surface上的变化,通过其内部类CallBack来实现。
 * 为了可以获取图片，我们需要使用Camera的takePicture方法同时我们需要实现Camera.PictureCallBack类，实现onPictureTaken方法
 * @author Administrator
 *
 */
public class MainActivity extends Activity implements SurfaceHolder.Callback,Camera.PictureCallback{

    public static final int MAX_WIDTH = 200;
    public static final int MAX_HEIGHT = 200;

    private SurfaceView surfaceView;

    private Camera camera; //这个是hardare的Camera对象

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        surfaceView = (SurfaceView)this.findViewById(R.id.myCameraView);
        //SurfaceView中的getHolder方法可以获取到一个SurfaceHolder实例
        SurfaceHolder holder = surfaceView.getHolder();
        //为了实现照片预览功能，需要将SurfaceHolder的类型设置为PUSH
        //这样，画图缓存就由Camera类来管理，画图缓存是独立于Surface的
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.addCallback(this);
    }
    public void capture(View view){
        camera.takePicture(null,null,this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // 当Surface被创建的时候，该方法被调用，可以在这里实例化Camera对象
        //同时可以对Camera进行定制
        Log.e("RYC","surfaceCreated");
        camera = Camera.open(); //获取Camera实例
        Log.e("RYC","Camera.open()");


        /**
         * Camera对象中含有一个内部类Camera.Parameters.该类可以对Camera的特性进行定制
         * 在Parameters中设置完成后，需要调用Camera.setParameters()方法，相应的设置才会生效
         */
        try {

            Camera.Parameters param = camera.getParameters();
            Log.e("RYC",param.flatten());
            //param.setSceneMode("hdr");
            param.setPreviewSize(1280, 720);
            param.setPictureSize(176,144);
            //camera.setDisplayOrientation(90);
            surfaceView.setLayoutParams(new LinearLayout.LayoutParams(320,240));
            //param.setColorEffect("sepia");
            camera.setParameters(param);
            camera.setPreviewDisplay(holder);

        } catch (Exception e) {
            // 如果出现异常，则释放Camera对象
            camera.release();
        }

        //启动预览功能
        camera.startPreview();

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // 当Surface被销毁的时候，该方法被调用
        //在这里需要释放Camera资源
        camera.setPreviewCallback(null) ;
        camera.stopPreview();
        Log.e("RYC","stopPreview");
        camera.release();
        Log.e("RYC","camera.release()");

    }
    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        // data是一个原始的JPEG图像数据，
        //在这里我们可以存储图片，很显然可以采用MediaStore
        //注意保存图片后，再次调用startPreview()回到预览
        Uri imageUri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        try {
            OutputStream os = this.getContentResolver().openOutputStream(imageUri);
            os.write(data);
            os.flush();
            os.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        camera.startPreview();
    }

}