package com.goafter.fastdialing;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CrtDB db;
    EditText etname, etnumber;
    Button btnAdd;
    Cursor c;
    ListView lvDisplay;
    SimpleCursorAdapter adapter;
    SQLiteDatabase dbRead, dbWrite;
    TextView tvName,tvNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new CrtDB(this);
        lvDisplay = (ListView) findViewById(R.id.lvDispaly);
        adapter = new SimpleCursorAdapter(this, R.layout.cell, null, new String[]{"name", "number"}, new int[]{R.id.tvName, R.id.tvNumber});
        lvDisplay.setAdapter(adapter);
        lvDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "1213123123", Toast.LENGTH_SHORT).show();

            }
        });

        etname = (EditText) findViewById(R.id.etName);
        etnumber = (EditText) findViewById(R.id.etNum);
        tvName = (TextView) findViewById(R.id.tvName);
        tvNumber = (TextView) findViewById(R.id.tvNumber);
        btnAdd = (Button) findViewById(R.id.btnAdd);



        refresh();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata();
                refresh();
            }
        });

    }

    public void savedata() {
        dbWrite = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", etname.getText().toString());
        cv.put("number", etnumber.getText().toString());
        dbWrite.insert("user", null, cv);
        db.close();

    }

    public void refresh() {
        dbRead = db.getReadableDatabase();
        Cursor c = dbRead.query("user", new String[]{"_id", "name", "number"}, null, null, null, null, null, null);
        adapter.changeCursor(c);


    }
}
