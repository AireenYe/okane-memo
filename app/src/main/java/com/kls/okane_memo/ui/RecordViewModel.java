package com.kls.okane_memo.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kls.okane_memo.db.DataSource;
import com.kls.okane_memo.db.Record;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class RecordViewModel extends ViewModel
{
    private final DataSource mDataSource;

    private Record record;

    public RecordViewModel(DataSource dataSource) {
        mDataSource = dataSource;
    }

    /**
     * Get the user name of the user.
     *
     * @return a {@link Flowable} that will emit every time the user name has been updated.
     */
    public Flowable<List<Record>> getRecordByDate(int year, int month, int dayOfmonth) {
//        return mDataSource.getRecord()
//                // for every emission of the user, get the user name
//                .map(user -> {
//                    mUser = user;
//                    return user.getUserName();
//                });
            return mDataSource.getRecordByDate(year, month, dayOfmonth);
    }

    public Flowable<List<Record>> getRecord() {
        return mDataSource.getRecord();
    }


    public Completable insertRecord(final Record record) {
        return mDataSource.insertRecord(record);
    }

    public Completable updateRecord(final Record record){
        return mDataSource.updateRecord(record);
    }

}