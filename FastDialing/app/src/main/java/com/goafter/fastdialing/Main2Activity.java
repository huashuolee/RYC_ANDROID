package com.goafter.fastdialing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    ListView lvDisplay2;
    ArrayAdapter adapter;
    Button btnC;
    PopupWindow mPopupWindow;
    int mScreenWidth;
    int mScreenHeight;
    int mPopupWindowWidth;
    int mPopupWindowHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lvDisplay2 = (ListView) findViewById(R.id.lvDisplay2);
        btnC = (Button) findViewById(R.id.btnC);

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,new String[]{"rty","adfadf","adsfad"});
        lvDisplay2.setAdapter(adapter);
        lvDisplay2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Main2Activity.this, view.getId() + "", Toast.LENGTH_SHORT).show();
            }
        });



        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopuptWindow();
                mPopupWindow.showAsDropDown(v);

            }
        });
    }

    public void initPopuptWindow() {
        LayoutInflater layoutInflater = LayoutInflater.from(Main2Activity.this);
        View popupWindow = layoutInflater.inflate(R.layout.popup_window, null);
        // 获取屏幕和PopupWindow的width和height
        mScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
        mScreenHeight = getWindowManager().getDefaultDisplay().getHeight();
 /*       mPopupWindowWidth = mPopupWindow.getWidth();
        mPopupWindowHeight = mPopupWindow.getHeight();*/
        mPopupWindow = new PopupWindow(popupWindow, mScreenWidth, mScreenHeight);
    }
}
