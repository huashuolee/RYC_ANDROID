package com.goafter.transformerstoolkit.utility;

import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.goafter.transformerstoolkit.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class FileManager extends ListFragment {


    private String ROOTPATH = File.separator;
    //存储文件名称
    private ArrayList<String> names = null;
    //存储文件路径
    private ArrayList<String> paths = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_file_manager, container, false);
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),getData(),R.layout.file_manager_cell,new String[]{"",""},new int[]{R.id.imgfile,R.id.tvfile});
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,getData1());
        setListAdapter(arrayAdapter);
/*        TextView tv = (TextView) view.findViewById(R.id.tvDeving);*/
        return view;
    }

    private List<Map<String,Object>> getData(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();



        return list;
    }

    private List<String> getData1(){
        List<String> list = new ArrayList<>();
        names = new ArrayList<String>();
        paths = new ArrayList<String>();
        File[] files = new File(ROOTPATH).listFiles();

/*        //如果当前目录不是根目录
        if (!ROOTPATH.equals("/")){
            names.add("@1");
            paths.add(ROOTPATH);

            names.add("@2");
            paths.add(file.getParent());
        }*/
        //添加所有文件
        for (File f : files){
            names.add(f.getName());
            paths.add(f.getPath());

        }


        Log.e("111111", names.toString());
        Log.e("111111",paths.toString());
        list.add("test");

        return  paths;

    }



}
