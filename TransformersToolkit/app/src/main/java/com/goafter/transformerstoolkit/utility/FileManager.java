package com.goafter.transformerstoolkit.utility;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

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
        showFileDir(ROOTPATH);
       // SimpleAdapter adapter = new SimpleAdapter(getActivity(),getData(),R.layout.file_manager_cell,new String[]{"",""},new int[]{R.id.imgfile,R.id.tvfile});

/*        TextView tv = (TextView) view.findViewById(R.id.tvDeving);*/


        return view;
    }

    private void showFileDir(String path){
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,getArrayData(path));
        setListAdapter(arrayAdapter);




    }

    private List<Map<String,Object>> getData(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        return list;
    }

    private List<String> getArrayData(String path){
        List<String> list = new ArrayList<>();
        names = new ArrayList<String>();
        paths = new ArrayList<String>();
        File[] files = new File(path).listFiles();
        File[] files1 = Environment.getExternalStorageDirectory().listFiles();

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

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String path = paths.get(position);
        Log.e("22222222",path);
        File file = new File(path);
        // 文件存在并可读
        if (file.exists() && file.canRead()){
            if (file.isDirectory()){
                //显示子目录及文件
                showFileDir(path);
            }
            else{
                //处理文件
                //fileHandle(file);
                Toast.makeText(getActivity(),"无法打开", Toast.LENGTH_SHORT).show();
            }
        }
        //没有权限
        else{
            Resources res = getResources();
            new AlertDialog.Builder(getActivity()).setTitle("Message")
                    .setMessage(res.getString(R.string.no_permission))
                    .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }

        super.onListItemClick(l, v, position, id);
    }
}
