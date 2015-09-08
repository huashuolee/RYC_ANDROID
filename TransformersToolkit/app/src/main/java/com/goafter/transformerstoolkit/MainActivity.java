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
        ArrayAdapter <String > lfadapter = new ArrayAdapter<String>(this,R.layout.drawer_list_item,mPlanetTitles);
        mDrawerList.setAdapter(lfadapter);

        //动态插入fragment
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //动态插入fragment到framelayout中
/*            switch (position) {
                case 1:
                    Log.e("11111111111","11111111111");
                    Fragment youDao_fragment = new com.goafter.transformerstoolkit.utility.YouDao();
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.drawer_layout, youDao_fragment)
                            .addToBackStack(null)
                            .commit();
                    mDrawerLayout.closeDrawers();
                    break;
                case 2:
                    Log.e("2222222222","2222222222");

                    Fragment fileManager_fragment  = new com.goafter.transformerstoolkit.utility.FileManager();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.drawer_layout, fileManager_fragment)
                            .addToBackStack(null)
                            .commit();
                    mDrawerLayout.closeDrawers();
                    break;

            }*/


            FragmentTransaction ft =  getFragmentManager().beginTransaction();
            Fragment fragment = null;
            switch (position){
                case 1:
                    fragment = new com.goafter.transformerstoolkit.utility.YouDao();
                    break;
                case 2:
                    fragment = new com.goafter.transformerstoolkit.utility.FileManager();
                    break;
                default:
                    break;

            }
            if (fragment!=null) {
                ft.replace(R.id.drawer_layout, fragment).commit();
            }
            mDrawerLayout.closeDrawers();

        }

/*        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Fragment fragment = new com.goafter.transformerstoolkit.utility.FileManager();

            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();

            // Highlight the selected item, update the title, and close the drawer
            mDrawerList.setItemChecked(position, true);
            setTitle(mPlanetTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);


        }*/
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
