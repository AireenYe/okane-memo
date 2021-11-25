package com.kls.okane_memo.db;

import android.util.Log;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Using the Room database as a data source.
 */
public class LocalDataSource implements DataSource {

    private final RecordDao recordDao;

    public LocalDataSource(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    @Override
    public Flowable<List<Record>> getRecord() {
        Log.d("查询数据", "全部");
        return recordDao.getAllRecord();
    }

    public Flowable<List<Record>> getRecordByDate(int year, int month, int dayOfMonth){
        Log.d("查询数据", "具体日期数据");
        return recordDao.getRecordByDate(year, month, dayOfMonth);
    }

    @Override
    public Completable insertRecord(Record record) {
        Log.d("插入数据", record.getTypename());
        return recordDao.insertRecord(record);
    }

    @Override
    public Completable updateRecord(Record record) {
        return recordDao.updateRecord(record);
    }

    @Override
    public Flowable<List<Record>> getRecordByMonth(int year, int month){
        return recordDao.getRecordByMonth(year, month);
    }





//    @Override
//    public void deleteAllUsers() {
//        recordDao.deleteAllUsers();
//    }
}
