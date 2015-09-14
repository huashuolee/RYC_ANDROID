package com.goafter.transformerstoolkit;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.text.Html;

import com.goafter.transformerstoolkit.utility.*;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String[] mPlanetTitles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        ArrayAdapter<String> lfadapter = new ArrayAdapter<String>(this, R.layout.drawer_list_item, mPlanetTitles);
        mDrawerList.setAdapter(lfadapter);

        //动态插入fragment
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //动态插入fragment到framelayout中
            FragmentTransaction ft =  getFragmentManager().beginTransaction();
            Fragment fragment = null;
            switch (position){
                case 1:
                    fragment = new YouDao();
                    break;
                case 2:
                    fragment = new FileManager();
                    break;
                case 3:
                    fragment = new Weather();
                    break;
                case 4:
                    fragment = new MyBaiduMap();
                    break;
                default:
                    break;

            }
            if (fragment!=null) {
                ft.replace(R.id.content_frame, fragment).commit();
            }
            mDrawerLayout.closeDrawers();
            mDrawerList.setItemChecked(position, true);
            setTitle(mPlanetTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

        }


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
