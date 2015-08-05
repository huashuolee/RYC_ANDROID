package com.goafter.learningdrawerlayout;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ArrayList<String> itemList;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = (String)getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
       // listView = new ListView(this);
        listView = (ListView) findViewById(R.id.left_drawer);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getData());
        listView.setAdapter(arrayAdapter);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.drawable.ic_drawer,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle("");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActionBar().setTitle(mTitle);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    public ArrayList<String> getData(){
        itemList = new ArrayList<>();
        itemList.add("短租自驾");
        itemList.add("长租代驾");
        itemList.add("神州租车");
        itemList.add("内附优惠");
        return itemList;
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
