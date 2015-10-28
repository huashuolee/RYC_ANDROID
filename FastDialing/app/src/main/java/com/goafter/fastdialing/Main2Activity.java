package com.goafter.fastdialing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    ListView lvDisplay2;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lvDisplay2 = (ListView) findViewById(R.id.lvDisplay2);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,new String[]{"rty","adfadf","adsfad"});
        lvDisplay2.setAdapter(adapter);
        lvDisplay2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Main2Activity.this,view.getId()+"",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
