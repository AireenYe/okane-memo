package com.kls.okane_memo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kls.okane_memo.db.DBManager;
import com.kls.okane_memo.db.OkaneDB;
import com.kls.okane_memo.db.Record;

import java.time.LocalDate;
import java.util.Calendar;

@RequiresApi(api = Build.VERSION_CODES.O)
public class SingleRecordActivity extends AppCompatActivity {

    DBManager dbm;
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
    }

    private void setRemarkInput(){
        remarkIv = findViewById(R.id.remark_iv);
        remarkEt = findViewById(R.id.input_remark);
        remarkEt.addTextChangedListener(new RemarkTextWatcher());
        remarkIv.setImageResource(R.drawable.ic_note);
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
        Calendar calendar = Calendar.getInstance();
        String todayDate = String.format("%d-%d-%d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH));
        dateTv.setText(todayDate);
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
                    money = Double.parseDouble(moneyEt.getText().toString());
                    remarkInfo = remarkEt.getText().toString();
                    if(money != 0){
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
        dbm = new DBManager(this);
    }

    private void applyChange(){
        if(infoBundle.getBoolean("ifCreate"))
            dbm.insert(typename, kind, money, recordYear, recordMonth, recordDayOfMonth, remarkInfo);
        else
            dbm.update(infoBundle.getInt("id"), typename, kind, money, recordYear, recordMonth, recordDayOfMonth, remarkInfo);
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