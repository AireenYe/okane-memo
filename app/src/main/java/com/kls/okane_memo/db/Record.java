package com.kls.okane_memo.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(tableName = "records")
public class Record {
    @PrimaryKey(autoGenerate = true)
    int id;

    String typename;    // 类型名称
    int kind;   // 类型，1为收入，-1为支出
    String date;     // 该记录的时间
    String remark;      // 备注

    public int getId() {
        return id;
    }


    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Record(String typename, int kind, String date, String remark) {
        this.typename = typename;
        this.kind = kind;
        this.date = date;
        this.remark = remark;
    }
}
