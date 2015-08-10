package com.goafter.learningdrawerlayout;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;



public class Content_fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content_fragment, container, false);
        TextView tv = (TextView)view.findViewById(R.id.textView);
        String text = getArguments().getString("text");
        tv.setText(text);
        return view;
    }




}
