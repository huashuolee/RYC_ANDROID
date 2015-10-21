package com.goafter.transformerstoolkit.utility;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.goafter.transformerstoolkit.R;

import java.io.File;
import java.net.URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class MePlayer extends Fragment {

    MediaPlayer mediaPlayer;
    VideoView vView;
    SurfaceView playView;
    SurfaceHolder holder;


    public MePlayer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me_player, container, false);
        Button btnPlay = (Button) view.findViewById(R.id.btnPlay);
        //vView = (VideoView) view.findViewById(R.id.vView);
        playView = (SurfaceView) view.findViewById(R.id.playView);
        holder = playView.getHolder();
        holder.addCallback(new playback());

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        return view;
    }

    public class playback implements SurfaceHolder.Callback{
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            lnMeplayer();

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }

    public void lnMeplayer(){


        //方法一：调用系统自带的播放器
/*        Uri uri1 = Uri.parse("file://"+Environment.getExternalStorageDirectory()+"/DCIM/Camera/video.mp4");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        Log.v("URI:::::::::", uri1.toString());
        intent.setDataAndType(uri1, "video/mp4");
        startActivity(intent);*/

        //方法二: videoview
/*        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/DCIM/Camera/video.mp4");
        vView.setMediaController(new MediaController(getActivity()));
        vView.setVideoURI(uri);
        vView.start();
        vView.requestFocus();*/



       // File resource = new File(Environment.getExternalStorageDirectory()+"/DCIM/Camera/video.mp4");

       //方法三： MediaPlayer
        Uri uri = Uri.parse( Environment.getExternalStorageDirectory() + "/DCIM/Camera/video.mp4");
        mediaPlayer = MediaPlayer.create(getActivity(),uri);
        mediaPlayer.start();






    }


}
