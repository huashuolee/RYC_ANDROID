package com.goafter.transformerstoolkit.utility;



import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.goafter.transformerstoolkit.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MePlayer extends Fragment {



    public MePlayer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me_player, container, false);
        Button btnPlay = (Button) view.findViewById(R.id.btnPlay);



        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PlayWindow.class);
                startActivity(intent);

            }
        });
        return view;
    }





}
