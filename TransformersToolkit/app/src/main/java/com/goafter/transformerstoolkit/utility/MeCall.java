package com.goafter.transformerstoolkit.utility;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.goafter.transformerstoolkit.R;
import com.goafter.transformerstoolkit.inadvance.CrtDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeCall extends Fragment {
    Button btnCall,btnSMS,btnAdd;
    EditText etName,etNumber;
    CrtDB db;
    ListView lvDisplay;
    SimpleCursorAdapter adapter;


    public MeCall() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me_call, container, false);
        btnCall = (Button) view.findViewById(R.id.btnCall);
        btnSMS = (Button) view.findViewById(R.id.btnSMS);
        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        etName = (EditText) view.findViewById(R.id.etName);
        etNumber = (EditText) view.findViewById(R.id.etNum);
        lvDisplay = (ListView) view.findViewById(R.id.lvDispaly);
        db = new CrtDB(getActivity());
        displayall();


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContacts();
                displayall();
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }
        });

        btnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeSMS();
            }
        });


        return view;
    }

    private void makeCall(){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10010"));
        startActivity(intent);
    }

    private void makeSMS(){
        Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:10086"));
        intent.putExtra("sms_body","ye");
        startActivity(intent);
    }

    private void saveContacts(){
        SQLiteDatabase dbWrite = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String name = etName.getText().toString();
        String number = etNumber.getText().toString();
        cv.put("name",name);
        cv.put("number", number);
        dbWrite.insert("user", null, cv);
        dbWrite.close();

    }

    private void displayall(){
        SQLiteDatabase dbRead = db.getReadableDatabase();
        //第二个参数 new String[]{"_id","name", "sex"}, 可以用null,每次查询时必须带"_id"，否则SimpleCursorAdapter 会报错。
        Cursor c = dbRead.query("user", new String[]{"_id","name", "number"}, null, null, null, null, null);
        adapter = new SimpleCursorAdapter(getActivity(),R.layout.cell_for_me_call,c,new String[]{"name","number"},new int[]{R.id.tvName, R.id.tvNumber});
        adapter.changeCursor(c);
        lvDisplay.setAdapter(adapter);
        Log.e("===============",c.getString(c.getColumnIndex("name")));
        Log.e("===============",c.getString(c.getColumnIndex("number")));

    }







}
