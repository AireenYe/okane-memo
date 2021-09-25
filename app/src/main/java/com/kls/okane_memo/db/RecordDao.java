package com.kls.okane_memo.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface RecordDao {
    @Query("select * from records where year = :year and month = :month and dayOfMonth = :dayOfMonth")
    List<Record> getRecordByDate(int year, int month, int dayOfMonth);

    @Insert
    void insertRecord(Record record);

    @Delete
    void deleteRecordById(Record record);

    @Update
    void updateRecord(Record record);
}
