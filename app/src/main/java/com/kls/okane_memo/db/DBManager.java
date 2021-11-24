package com.kls.okane_memo.db;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class DBManager {
    private OkaneDB db;

    public DBManager(Context context){
        db = OkaneDB.getInstance(context.getApplicationContext());
    }

    public Completable insert(String typename, int kind, double money, int year, int month, int dayOfMonth, String remark){
        Record record = new Record(typename, kind, money, year, month, dayOfMonth, remark);
        return db.recordDao().insertRecord(record);
    }

    public void update(int id, String typename, int kind, double money, int year, int month, int dayOfMonth, String remark){
        Record record = new Record(typename, kind, money, year, month, dayOfMonth, remark);
        record.setId(id);
        db.recordDao().updateRecord(record);
    }

    public Flowable<List<Record>> getRecordByDate(int year, int month, int dayOfMonth){
        return db.recordDao().getRecordByDate(year, month, dayOfMonth);
    }

}
