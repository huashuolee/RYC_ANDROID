package com.goafter.learnviewpager;

import android.content.Intent;
import android.provider.Settings;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listView = new ListView(this);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1,getData());
        listView.setAdapter(arrayAdapter);
        setContentView(listView);
    }

   private ArrayList<String> getData(){
       ArrayList<String> data = new ArrayList<>();
       data.add("神州租车");
       data.add("内附优惠");
       data.add("短租自驾");
       data.add("长租代驾");
       return data;
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
