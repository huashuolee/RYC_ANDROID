package com.goafter.testtemp;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;


public class SQLite extends ListActivity {

    SQLiteDatabase dbRead, dbWrite;
    SimpleCursorAdapter adapter;
    Button btnAdd,btnClearA;
    EditText edName, edSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        Db db = new Db(this);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd = (Button)findViewById(R.id.btnClearA);
        edName = (EditText)findViewById(R.id.edName);
        edSex = (EditText)findViewById(R.id.edSex);

        dbWrite = db.getWritableDatabase();
        dbRead = db.getReadableDatabase();
        btnAdd.setOnClickListener(new Addto());
        btnAdd.setOnClickListener(new ClearA());




        //dbWrite.close();

        Cursor c = dbRead.query("user",null,null,null,null,null,null);
        adapter = new SimpleCursorAdapter(this,R.layout.ll,c,new String[]{"name","sex"},new int[]{R.id.tvName,R.id.tvSex});
        getListView().setAdapter(adapter);
        while (c.moveToNext()){
            String name = c.getString(c.getColumnIndex("name"));
            String sex = c.getString(c.getColumnIndex("sex"));
            Log.e(getApplication().getPackageName().toString(),name + " " + sex);

        }





    }

    public class Addto implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            ContentValues cv = new ContentValues();
            String name = edName.getText().toString();
            String sex = edSex.getText().toString();

            cv.put("name", name);
            cv.put("sex", sex);
            dbWrite.insert("user", null, cv);
            refresh();


        }
    }

    public void refresh(){
        Cursor c = dbRead.query("user",null,null,null,null,null,null);
        adapter.changeCursor(c);
    }


    public class Db extends SQLiteOpenHelper{
        public Db(Context context) {
            super(context, "db", null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE user(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT DEFAULT NONE , sex TEXT DEFAULT NONE)");


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    private class ClearA implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            dbWrite.delete("user",null,null);
            refresh();

        }
    }
}
