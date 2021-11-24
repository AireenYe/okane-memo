package com.kls.okane_memo.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface RecordDao {
    @Query("select * from records where year = :year and month = :month and dayOfMonth = :dayOfMonth")
    Flowable<List<Record>> getRecordByDate(int year, int month, int dayOfMonth);

    @Query("select * from records where year = :year and month = :month")
    Flowable<List<Record>> getRecordByMonth(int year, int month);

    @Query("select *from records")
    Flowable<List<Record>> getAllRecord();

    @Insert
    Completable insertRecord(Record record);

    @Delete
    void deleteRecordById(Record record);

    @Update
    void updateRecord(Record record);
}
