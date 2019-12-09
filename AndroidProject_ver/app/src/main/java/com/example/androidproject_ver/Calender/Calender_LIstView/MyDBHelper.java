package com.example.androidproject_ver.Calender.Calender_LIstView;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {


    public MyDBHelper(@Nullable Context context) {
        super(context,"calenderDB",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE calenderTBL(date CHAR(50) PRIMARY KEY, coinrdo CHAR(5),coinedttxt CHAR(20),coinfilter CHAR(10),coinimage CHAR(50));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS calenderTBL");
        onCreate(db);
    }
}
