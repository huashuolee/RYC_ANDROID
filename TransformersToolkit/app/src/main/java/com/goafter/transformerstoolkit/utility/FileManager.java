package com.goafter.transformerstoolkit.utility;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.goafter.transformerstoolkit.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FileManager extends ListFragment implements View.OnClickListener {


    private String ROOTPATH = File.separator;
    private String SDPATH = Environment.getExternalStorageDirectory().getPath();
    //存储文件名称
    private ArrayList<String> names = null;
    //存储文件路径
    private ArrayList<String> paths = null;
    Button btnFileHome, btnFileUp;

    private static final String TAG = "=============";
    private static final MyLogUtil logUtil = new MyLogUtil();
    private TextView tvFilePath;
    String ABspath = SDPATH;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_file_manager, container, false);
        tvFilePath = (TextView) view.findViewById(R.id.tvFilePath);
        btnFileHome = (Button) view.findViewById(R.id.btnFileHome);
        btnFileUp = (Button) view.findViewById(R.id.btnFileUp);
        btnFileHome.setOnClickListener(this);
        btnFileUp.setOnClickListener(this);
        showFileDir(SDPATH);
        // SimpleAdapter adapter = new SimpleAdapter(getActivity(),getData(),R.layout.file_manager_cell,new String[]{"",""},new int[]{R.id.imgfile,R.id.tvfile});

/*        TextView tv = (TextView) view.findViewById(R.id.tvDeving);*/


        return view;
    }

    private void showFileDir(String path) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, getArrayData(path));
        setListAdapter(arrayAdapter);


    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        return list;
    }

    private List<String> getArrayData(String path) {
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
        for (File f : files) {
            names.add(f.getName());
            paths.add(f.getPath());

        }


        logUtil.e(TAG, names.toString());
        list.add("test");

        return names;

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ABspath = paths.get(position);
        tvFilePath.setText(ABspath);
        logUtil.e(TAG, ABspath);
        File file = new File(ABspath);
        // 文件存在并可读
        if (file.exists() && file.canRead()) {
            if (file.isDirectory()) {
                //显示子目录及文件
                showFileDir(ABspath);
            } else {
                //处理文件
                //fileHandle(file);
                //Toast.makeText(getActivity(), "无法打开", Toast.LENGTH_SHORT).show();
                openFile(file);
            }
        }
        //没有权限
        else {
            Resources res = getResources();
            new AlertDialog.Builder(getActivity()).setTitle("Message")
                    .setMessage(res.getString(R.string.no_permission))
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }

        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFileHome:
                showFileDir(SDPATH);
                tvFilePath.setText(getActivity().getString(R.string.rootFolder));
                break;

            case R.id.btnFileUp:
                logUtil.e(TAG, ABspath + "  " + SDPATH);
                if (!ABspath.equals(SDPATH)) {
                    String path1 = getLastPath(ABspath);
                    ABspath = path1;
                    showFileDir(path1);
                    tvFilePath.setText(path1);
                } else {
                    Toast.makeText(getActivity(), getActivity().getString(R.string.msgrootFolder), Toast.LENGTH_SHORT).show();
                    tvFilePath.setText(getActivity().getString(R.string.rootFolder));
                }
                logUtil.e(TAG, ABspath + "  " + SDPATH);
                break;
        }
    }

    private String getLastPath(String path) {
        String str1 = "";
        str1 = new File(path).getParent();

        logUtil.e(TAG, str1);
        return str1;

    }


    private String getMIMEType(File file) {
        String type = "*/*";
        String fileName = file.getName();
        int dotIndex = fileName.indexOf('.');
        if (dotIndex < 0) {
            return type;
        }
        String end = fileName.substring(dotIndex, fileName.length()).toLowerCase();
        if (end == "") {
            return type;
        }
        for (int i = 0; i < UrlConst.MIME_MapTable.length; i++) {
            if (end == UrlConst.MIME_MapTable[i][0]) {
                type = UrlConst.MIME_MapTable[i][1];
            }
        }
        return type;
    }

    private void openFile(File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        String type = getMIMEType(file);
        intent.setDataAndType(Uri.fromFile(file), type);
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "未知类型，不能打开", Toast.LENGTH_SHORT).show();

        }
    }
}
