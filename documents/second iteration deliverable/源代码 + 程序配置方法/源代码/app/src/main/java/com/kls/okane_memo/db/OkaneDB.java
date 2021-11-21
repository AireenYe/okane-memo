package com.kls.okane_memo.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Record.class}, version = 2)
public abstract class OkaneDB extends RoomDatabase {
    private static final String dbName = "okane-db";

    private static OkaneDB okaneDB;

    public static synchronized OkaneDB getInstance(Context context){
        if(okaneDB == null){
            okaneDB = Room.databaseBuilder(context.getApplicationContext(), OkaneDB.class, dbName).
                    fallbackToDestructiveMigrationOnDowngrade().
                    build();
        }
        return okaneDB;
    }

    public abstract RecordDao recordDao();
}
