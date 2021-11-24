package com.kls.okane_memo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kls.okane_memo.db.Injection;
import com.kls.okane_memo.db.Record;
import com.kls.okane_memo.ui.RecordViewModel;
import com.kls.okane_memo.ui.ViewModelFactory;

import java.util.Calendar;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@RequiresApi(api = Build.VERSION_CODES.O)
public class SingleRecordActivity extends AppCompatActivity {

    RecordViewModel recordViewModel;
    ViewModelFactory viewModelFactory;
    Record mRecord;
    private final CompositeDisposable disposable = new CompositeDisposable();
    EditText moneyEt, remarkEt;
    ImageView backIv, typeIv, dateIv, remarkIv;
    TextView kindTv, typeTv, dateTv;
    FloatingActionButton checkBtn;
    Bundle infoBundle;
    String typename, remarkInfo;
    int kind, recordYear, recordMonth, recordDayOfMonth;
    double money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_record);
        infoBundle = getIntent().getExtras();

        initDB();
        setKindString();
        setMoneyInput();
        setTypeRow();
        setRemarkInput();
        setDate();
        setFloatingBtn();

        backIv = findViewById(R.id.single_record_iv_back);
        backIv.setOnClickListener(new OnClick());
    }

    @Override
    protected void onStop() {
        super.onStop();

        // clear all the subscriptions
        disposable.clear();
    }

    private void setKindString(){
        kindTv = findViewById(R.id.kind_tv);
        kind = infoBundle.getInt("kind");
        if(kind == 1){
            kindTv.setText(R.string.income);
        }else{
            kindTv.setText(R.string.outcome);
        }
    }

    private void setMoneyInput(){
        moneyEt = findViewById(R.id.input_money);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/bahnschrift.ttf");
        moneyEt.setTypeface(tf);
        double moneyValue = infoBundle.getDouble("money");
        if (moneyValue != 0)
        {
            money = moneyValue;
            moneyEt.setText(String.valueOf(moneyValue));
        }
    }

    private void setRemarkInput(){
        remarkIv = findViewById(R.id.remark_iv);
        remarkEt = findViewById(R.id.input_remark);
        remarkEt.addTextChangedListener(new RemarkTextWatcher());
        remarkIv.setImageResource(R.drawable.ic_note);
        String remark = infoBundle.getString("remarkInfo");
        if(remark != null)
        {
            remarkInfo = remark;
            remarkEt.setText(remarkInfo);
        }
    }

    private void setTypeRow(){
        typename = infoBundle.getString("typename");
        int imageId = infoBundle.getInt("imageId");
        typeIv = findViewById(R.id.type_iv);
        typeTv = findViewById(R.id.typename_tv);
        typeIv.setImageResource(imageId);
        typeTv.setText(typename);
    }

    private void setDate(){
        dateTv = findViewById(R.id.date_tv);
        dateIv = findViewById(R.id.date_iv);
        dateIv.setOnClickListener(new OnClick());
        dateTv.setOnClickListener(new OnClick());

        int yearInfo = infoBundle.getInt("year");
        int monthInfo = infoBundle.getInt("month");
        int dayOfMonthInfo = infoBundle.getInt("dayOfMonth");

        if(yearInfo != 0)
        {
            recordYear = yearInfo;
            recordMonth = monthInfo;
            recordDayOfMonth = dayOfMonthInfo;
        }
        else
        {
            Calendar calendar = Calendar.getInstance();
            recordYear = calendar.get(Calendar.YEAR);
            recordMonth = calendar.get(Calendar.MONTH) + 1;
            recordDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        }

        String date = String.format("%d-%d-%d", recordYear, recordMonth, recordDayOfMonth);
        dateTv.setText(date);
        dateIv.setImageResource(R.drawable.ic_calendar);
    }

    private void setFloatingBtn(){
        checkBtn = findViewById(R.id.finish_btn);
        checkBtn.setOnClickListener(new OnClick());
    }

    private class OnClick implements View.OnClickListener, DatePickerDialog.OnDateSetListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.single_record_iv_back:
                    finish();
                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    break;
                case R.id.date_tv:
                case R.id.date_iv:
                    Calendar calendar = Calendar.getInstance();
                    DatePickerDialog dialog = new DatePickerDialog(SingleRecordActivity.this, this,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH));
                    dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                    dialog.show();
                    break;
                case R.id.finish_btn:
                    if(moneyEt.getText().toString() != null){
                        money = Double.parseDouble(moneyEt.getText().toString());
                        remarkInfo = remarkEt.getText().toString();
                        applyChange();
                    }
                    Intent intent = new Intent(SingleRecordActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    break;
            }
        }

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            String dateInfo = String.format("%d-%d-%d", year, month + 1, dayOfMonth);
            dateTv.setText(dateInfo);
            recordYear = year;
            recordMonth = month + 1;
            recordDayOfMonth = dayOfMonth;
            Log.d("记录日期",dateInfo);
        }
    }

    private void initDB(){
        viewModelFactory = Injection.provideViewModelFactory(this);
        recordViewModel = new ViewModelProvider(this, viewModelFactory).get(RecordViewModel.class);
    }

    private void applyChange(){
        Log.d("SingleRecordActivity", "修改数据");
        if(infoBundle.getBoolean("ifCreate"))
        {
            mRecord = new Record(typename, kind, money, recordYear, recordMonth, recordDayOfMonth, remarkInfo);
            disposable.add(recordViewModel.insertRecord(mRecord)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
        }
        else
        {
            mRecord = new Record(typename, kind, money, recordYear, recordMonth, recordDayOfMonth, remarkInfo);
            mRecord.setId(infoBundle.getInt("id"));
            disposable.add(recordViewModel.updateRecord(mRecord)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
        }
    }

    private class RemarkTextWatcher implements android.text.TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable edit) {
            remarkInfo = edit.toString();
        }
    }
}