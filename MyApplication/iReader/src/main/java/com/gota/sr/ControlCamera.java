package com.gota.sr;

import android.app.Activity;
import android.hardware.Camera;
import android.util.Log;

/**
 * Created by lihuashx on 2015/3/20.
 */
public class ControlCamera extends Activity {
    Camera mCamera=null;
        protected Camera safeOpen(int id){
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
}
