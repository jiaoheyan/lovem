package com.example.lovej.jlovem.jishiben;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2018/2/20 0020.
 */

public class OpenHelper extends SQLiteOpenHelper {

    public OpenHelper(Context context) {
        super(context, "db.student", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student (_id integer primary key autoincrement,name varchar(20),sex varchar(30),timers text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
