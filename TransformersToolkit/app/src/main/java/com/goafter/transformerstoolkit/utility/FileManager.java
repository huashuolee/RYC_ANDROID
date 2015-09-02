package com.goafter.transformerstoolkit.utility;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.goafter.transformerstoolkit.R;


public class FileManager extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_file_manager, container, false);
/*        TextView tv = (TextView) view.findViewById(R.id.tvDeving);*/
        return view;
    }



}
