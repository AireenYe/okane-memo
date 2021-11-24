package com.kls.okane_memo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kls.okane_memo.db.DBManager;
import com.kls.okane_memo.db.Record;
import com.kls.okane_memo.record.RecordLinearAdapter;
import com.kls.okane_memo.record.RecordViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        DatePickerDialog.OnDateSetListener{

    private FloatingActionButton recordBtn;
    private TextView dateTextView;
    private TextView inTv, outTv;
    private RecyclerView recordRv;
    private int year, month, dayOfMonth;
    RecordLinearAdapter adapter;
    private List<Record> records;
    RecordViewModel recordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initShow();

    }

    void initShow(){
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

        // 有问题，显示不了
        // 设置显示的记录
        recordRv = findViewById(R.id.record_lv);
        recordRv.setLayoutManager(new LinearLayoutManager(this));
        records = new ArrayList<>();
        adapter = new RecordLinearAdapter(this, year, month, dayOfMonth, records);
        recordRv.setAdapter(adapter);

        // 获取ViewModel
        recordViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(RecordViewModel.class);
        recordViewModel.getAllRecords().observe(this, new Observer<List<Record>>()
        {
            @Override
            public void onChanged(List<Record> records)
            {
                records.clear();
                records.addAll(records);
                adapter.notifyDataSetChanged();
            }
        });

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