package com.goafter.transformerstoolkit.utility;

import android.app.Activity;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.goafter.transformerstoolkit.R;

import java.util.List;


public class Torch extends Fragment {
    Camera camera;
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

        Button btnOn = (Button) view.findViewById(R.id.btnOn);
        Button btnOff = (Button) view.findViewById(R.id.btnOff);

        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera = Camera.open(0);
                turnLightOn(camera);


            }
        });


        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnLightOff(camera);

            }
        });


        return view;
    }

    public static void turnLightOn(Camera mCamera) {

        parameters = mCamera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        mCamera.setParameters(parameters);

    }

    public static void turnLightOff(Camera mCamera) {
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        mCamera.setParameters(parameters);
        mCamera.release();
    }


}
