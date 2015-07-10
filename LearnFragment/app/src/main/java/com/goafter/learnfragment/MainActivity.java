package com.goafter.learnfragment;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private Button addFragment,rmFrament,rpFragment;
    private MainActivityFragment fragment;
    private sdFragment sdFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new MainActivityFragment();
        sdFragment = new sdFragment();
        findViewById(R.id.addFragment).setOnClickListener(this);
        findViewById(R.id.rmFragment).setOnClickListener(this);
        findViewById(R.id.rpFragment).setOnClickListener(this);
        findViewById(R.id.btnSlider).setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addFragment:
                addFragments();
                break;
            case R.id.rmFragment:
                rmFragments();
                break;
            case R.id.rpFragment:
                rpFragments();
                break;
            case R.id.btnSlider:
                startActivity(new Intent(this, SliderActivity.class));
        }

    }

    public void addFragments(){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();

    }
    public void rmFragments(){
        getSupportFragmentManager()
                .beginTransaction()
                .remove(fragment)
                .addToBackStack(null)
                .commit();

    }
    public void rpFragments(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, sdFragment)
                .commit();
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
