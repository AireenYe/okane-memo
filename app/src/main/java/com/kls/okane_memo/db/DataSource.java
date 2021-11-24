package com.kls.okane_memo.db;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface DataSource {

    /**
     * Gets the user from the data source.
     *
     * @return the user from the data source.
     */
    Flowable<List<Record>> getRecord();

    Flowable<List<Record>> getRecordByDate(int year, int month, int dayOfMonth);

    /**
     * Inserts the user into the data source, or, if this is an existing user, updates it.
     *
     * @param record the record to be inserted or updated.
     */
    Completable insertRecord(Record record);

//    /**
//     * Deletes all users from the data source.
//     */
//    void deleteAllUsers();
}