package com.goafter.transformerstoolkit.inadvance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by huashuolee on 2015/10/27.
 */
public class CrtDB extends SQLiteOpenHelper {

    public CrtDB(Context context) {
        super(context, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT DEFAULT NONE, number TEXT DEFAULT \"\")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}