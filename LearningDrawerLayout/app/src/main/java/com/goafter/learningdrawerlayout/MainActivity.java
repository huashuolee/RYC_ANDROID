package com.goafter.learningdrawerlayout;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private DrawerLayout mDrawerLayout;
    private ArrayList<String> itemList;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mTitle;
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        mTitle = (String)getTitle();
        actionBar = getSupportActionBar();

        Log.e("A", mTitle);
       // listView = new ListView(this);
        listView = (ListView) findViewById(R.id.left_drawer);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getData());
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.drawable.ic_drawer,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                actionBar.setTitle("");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                actionBar.setTitle(mTitle);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //开启actionbar APP icon 功能
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);

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

        if (mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){
            case R.id.action_search:
                Intent intent = new Intent(Intent.ACTION_SEARCH);
                startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //动态插入fragment
        Fragment content_fragment = new Content_fragment();
        Bundle mBundle = new Bundle();
        mBundle.putString("text",itemList.get(position));
        content_fragment.setArguments(mBundle);


        FragmentManager mFragmentManager = getFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.content_frame,content_fragment).commit();
        mDrawerLayout.closeDrawers();



    }
}
