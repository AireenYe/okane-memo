package com.kls.okane_memo.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class DBManager {
    private RecordDao dao;

    public DBManager(Context context){
        dao = OkaneDB.getInstance(context.getApplicationContext()).recordDao();
    }

    public LiveData<List<Record>> getAllRecords(){
        return dao.getAllRecord();
    }

    public void insert(String typename, int kind, double money, int year, int month, int dayOfMonth, String remark){
        Record record = new Record(typename, kind, money, year, month, dayOfMonth, remark);
        new InsertRecordTask(dao, record);
    }

    class InsertRecordTask extends AsyncTask<Record, Void, Void>{
        private RecordDao recordDao;
        private Record record;

        public InsertRecordTask(RecordDao recordDao, Record record){
            this.recordDao = recordDao;
            this.record = record;
        }

        @Override
        protected Void doInBackground(Record... records) {
            recordDao.insertRecord(record);
            return null;
        }
    }

    public void update(int id, String typename, int kind, double money, int year, int month, int dayOfMonth, String remark){
        Record record = new Record(typename, kind, money, year, month, dayOfMonth, remark);
        record.setId(id);
        new UpdateRecordTask(dao, record);
    }

    class UpdateRecordTask extends AsyncTask<Record, Void, Void>{
        private RecordDao recordDao;
        private Record record;

        public UpdateRecordTask(RecordDao recordDao, Record record){
            this.recordDao = recordDao;
            this.record = record;
        }

        @Override
        protected Void doInBackground(Record... records) {
            recordDao.updateRecord(record);
            return null;
        }
    }

    public LiveData<List<Record>> getRecordByDate(int year, int month, int dayOfMonth){
        return dao.getRecordByDate(year, month, dayOfMonth);
    }

    public LiveData<List<Record>> getRecordByMonth(int year, int month){
        return dao.getRecordByMonth(year, month);
    }

}
