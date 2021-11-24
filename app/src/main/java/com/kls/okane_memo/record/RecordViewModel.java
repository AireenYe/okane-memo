package com.kls.okane_memo.record;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kls.okane_memo.db.DBManager;
import com.kls.okane_memo.db.Record;

import java.util.List;

public class RecordViewModel extends AndroidViewModel
{
    private DBManager db;
    private LiveData<List<Record>> liveDataRecord;

    public RecordViewModel(@NonNull Application application)
    {
        super(application);
        db = new DBManager(application);
        liveDataRecord = db.getAllRecords();
    }

    public LiveData<List<Record>> getAllRecords()
    {
        return db.getAllRecords();
    }

    public LiveData<List<Record>> getLiveDataRecordByDate(int year, int month, int dayOfMonth)
    {
        return db.getRecordByDate(year, month, dayOfMonth);
    }

    public LiveData<List<Record>> getLiveDataRecordByMonth(int year, int month)
    {
        return db.getRecordByMonth(year, month);
    }

    public void insertRecord(String typename, int kind, double money, int year, int month, int dayOfMonth, String remark){
        db.insert(typename, kind, money, year, month, dayOfMonth, remark);
    }

    public void updateRecord(int id, String typename, int kind, double money, int year, int month, int dayOfMonth, String remark){
        db.update(id,typename, kind, money, year, month, dayOfMonth, remark);
    }
}