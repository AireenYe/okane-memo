package com.kls.okane_memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kls.okane_memo.record.RecordLinearAdpater;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        DatePickerDialog.OnDateSetListener{

    private FloatingActionButton recordBtn;
    private TextView dateTextView;
    private TextView inTv, outTv;
    private RecyclerView recordRv;
    private int year, month, dayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    void init(){
        // 设置日期显示选择
        dateTextView = findViewById(R.id.datePicker);
        dateTextView.setOnClickListener(this);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String todayDate = String.format("%d月%d日", month, dayOfMonth);
        dateTextView.setText(todayDate);

        // 设置总金额数字的字体
        inTv = findViewById(R.id.income_money);
        outTv = findViewById(R.id.outcome_money);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/bahnschrift.ttf");
        inTv.setTypeface(tf);
        outTv.setTypeface(tf);

        // 有问题，不能在主线程中使用Room，百度后台使用Room的方法
        // 曾在Builder调用allowMainThreadQueries()方法，不知为何，无法从数据库中抓取数据
        // 设置显示的记录
//        recordRv = findViewById(R.id.record_lv);
//        recordRv.setLayoutManager(new LinearLayoutManager(this));
//        recordRv.setAdapter(new RecordLinearAdpater(this, year, month, dayOfMonth));

        // 设置记录按钮
        recordBtn = findViewById(R.id.btn_record);
        recordBtn.setOnClickListener(this);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month + 1;
        this.dayOfMonth = dayOfMonth;
        String date = String.format("%d月%d日", month + 1, dayOfMonth);
        dateTextView.setText(date);
        Log.d("日期",date);
    }

    @Override
    public void onClick(View view){
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_record:
                intent = new Intent(MainActivity.this, BookkeepingActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                break;
            case R.id.datePicker:
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(this, this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                dialog.show();
                break;
        }
    }

}