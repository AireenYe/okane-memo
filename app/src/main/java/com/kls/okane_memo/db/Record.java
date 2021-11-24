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
    double money;  // 金额
    int year;   // 年份
    int month;  // 月份
    int dayOfMonth;     // 日期
    String remark;      // 备注

    public Record(String typename, int kind, double money, int year, int month, int dayOfMonth, String remark) {
        this.typename = typename;
        this.kind = kind;
        this.money = money;
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.remark = remark;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
