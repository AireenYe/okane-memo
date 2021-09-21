package com.kls.okane_memo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static SQLiteOpenHelper dbInstance;

    public synchronized SQLiteOpenHelper getDbInstance(Context context){
        if(dbInstance == null){
            dbInstance = new DBOpenHelper(context, "kls.db", null, 1);
        }
        return dbInstance;
    }

    private DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table record(_id integer primary key autoincrement, type text, kind integer);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
