package com.goafter.learnviewpager;

import android.database.Cursor;
import android.provider.Contacts;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class LearingSimpleCursorAdapter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = new ListView(this);

        Cursor cursor = getContentResolver().query(Contacts.People.CONTENT_URI,null,null,null,null);
        startManagingCursor(cursor);
        ListAdapter listAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_expandable_list_item_1,cursor,new String[]{Contacts.People.DISPLAY_NAME},new int[]{android.R.id.text1});
        listView.setAdapter(listAdapter);
        setContentView(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simple_cursor_adapter, menu);
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
