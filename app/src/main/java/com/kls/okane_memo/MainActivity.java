package com.kls.okane_memo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        DatePickerDialog.OnDateSetListener{

    public FloatingActionButton recordBtn;
    public TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordBtn = findViewById(R.id.btn_record);
        recordBtn.setOnClickListener(this);

        dateTextView = findViewById(R.id.datePicker);
        dateTextView.setOnClickListener(this);
        Calendar calendar = Calendar.getInstance();
        String todayDate = String.format("%d月%d日",calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH));
        dateTextView.setText(todayDate);
    }

    @Override
    public void onDateSet(DatePicker datePicker,int year, int month, int dayOfMonth) {
        String date=String.format("%d月%d日",month+1,dayOfMonth);
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
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
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