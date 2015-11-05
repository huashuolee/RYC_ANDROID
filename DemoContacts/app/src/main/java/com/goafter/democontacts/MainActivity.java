package com.goafter.democontacts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private List<String> listContact = new ArrayList<>();
    private ContentResolver cr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lvContacts = (ListView) findViewById(R.id.lvContacts);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listContact);
        lvContacts.setAdapter(adapter);
        readContacts();
    }
    private void readContacts() {
        cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null,"display_name");
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String cname = cursor.getColumnName(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String cnumber = cursor.getColumnName(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                listContact.add(name + "\n"+ number+"\n"+cname+ " " + cnumber );
            }
    }
}
