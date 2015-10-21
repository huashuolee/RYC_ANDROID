package com.goafter.transformerstoolkit.utility;


import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.goafter.transformerstoolkit.R;

import java.io.IOException;


public class Torch extends Fragment {

    Camera mCamera = null;
    static Camera.Parameters parameters;

    public Torch() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_torch, container, false);

        final Button btnOn = (Button) view.findViewById(R.id.btnOn);
        final Button btnOff = (Button) view.findViewById(R.id.btnOff);

        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera = safeOpenCamera(0);
                turnLightOn(mCamera);
                btnOn.setEnabled(false);
                btnOff.setEnabled(true);


            }
        });


        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnLightOff(mCamera);
                btnOn.setEnabled(true);
                btnOff.setEnabled(false);

            }
        });


        return view;
    }

    public Camera safeOpenCamera(int id) {
        if (mCamera == null) {
            mCamera = Camera.open(id);

        } else {
            mCamera.release();
            mCamera = Camera.open(id);
        }
        return mCamera;


    }

    public static void turnLightOn(Camera mCamera) {

        parameters = mCamera.getParameters();

        for (String f : parameters.getSupportedFlashModes()) {
            System.err.println(f);


        }
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);


        mCamera.setParameters(parameters);
        try {
            mCamera.setPreviewTexture(new SurfaceTexture(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCamera.startPreview();

    }

    public static void turnLightOff(Camera mCamera) {
        if (mCamera != null) {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            mCamera.setParameters(parameters);
            mCamera.stopPreview();
            mCamera.release();

        } else {
            Log.e("TAG", "Alread turn off");
        }
    }


}
