package com.goafter.testtemp;

import android.app.ListActivity;
import android.database.Cursor;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

public class ReadContacts extends ListActivity {
    private SimpleCursorAdapter adapter;
    private SimpleAdapter sadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_contacts);
        Cursor cursor = getContentResolver().query(Contacts.People.CONTENT_URI,null,null,null,null);
        Cursor cursor1 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        String[] strFrom = new String[] {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.TIMES_CONTACTED
        };



        int[] intTo = new int[]{
                R.id.tvName,
                R.id.tvTel,
                R.id.tvTel1
        };


        adapter = new SimpleCursorAdapter(this,R.layout.cell_list,cursor1,strFrom,intTo);
        setListAdapter(adapter);


    }


}
