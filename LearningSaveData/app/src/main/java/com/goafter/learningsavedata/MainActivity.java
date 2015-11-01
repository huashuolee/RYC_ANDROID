package com.goafter.learningsavedata;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {
    private Button btnRead, btnWrite;
    private TextView result;
    private EditText editText;
    private Db db;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnWrite = (Button) findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new WriteData());
        btnRead.setOnClickListener(new ReadData());
        result = (TextView) findViewById(R.id.result);
        db = new Db(this);
        adapter = new SimpleCursorAdapter(MainActivity.this,R.layout.user_list_cell,null,new String[]{"name","sex"},new int[]{R.id.tvName, R.id.tvSex});
        setListAdapter(adapter);


    }

    private class WriteData implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            SQLiteDatabase dbWrite = db.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("name","zhang san");
            cv.put("sex", "male");
            dbWrite.insert("user", null, cv);

            cv = new ContentValues();
            cv.put("name", "li si");
            cv.put("sex", "male");
            dbWrite.insert("user", null, cv);
            dbWrite.close();
            refreshListView();

        }
    }

    private class ReadData implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            refreshListView();
        }
    }

    public void refreshListView(){
        /*            SQLiteDatabase dbRead = db.getReadableDatabase();
            Cursor c = dbRead.query("user", new String[]{"name", "sex"}, null, null, null, null, null);
            while (c.moveToNext()){
                String name = c.getString(c.getColumnIndex("name"));
                String sex = c.getString(c.getColumnIndex("sex"));
                Log.e("TEST", "NAME= " + name + " sex=" + sex);
            }*/
        //全部查询
        // db.getReadableDatabase().query("user",null,null,null,null,null,null);
        SQLiteDatabase dbRead = db.getReadableDatabase();
        //第二个参数 new String[]{"_id","name", "sex"}, 可以用null,每次查询时必须带"_id"，否则SimpleCursorAdapter 会报错。
        Cursor c = dbRead.query("user", new String[]{"_id","name", "sex"}, null, null, null, null, null);
        adapter.changeCursor(c);

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
