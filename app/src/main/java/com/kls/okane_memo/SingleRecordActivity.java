package com.kls.okane_memo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

import java.time.LocalDate;
import java.util.Calendar;

@RequiresApi(api = Build.VERSION_CODES.O)
public class SingleRecordActivity extends AppCompatActivity {

    EditText moneyEt, remarkEt;
    ImageView backIv, typeIv;
    TextView kindTv, typeTv, dateTv;
    Bundle typeBundle;
    String remarkInfo;
    int recordYear, recordMonth, recordDayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_record);
        typeBundle = getIntent().getExtras();

        setKindString();
        setMoneyInput();
        setTypeRow();
        setRemarkInput();
        setDate();

        backIv = findViewById(R.id.single_record_iv_back);
        backIv.setOnClickListener(new OnClick());
    }

    private void setKindString(){
        kindTv = findViewById(R.id.kind_tv);
        int kind = typeBundle.getInt("kind");
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
        remarkEt = findViewById(R.id.input_remark);
        remarkEt.addTextChangedListener(new RemarkTextWatcher());
    }

    private void setTypeRow(){
        String typename = typeBundle.getString("typename");
        int imageId = typeBundle.getInt("imageId");
        typeIv = findViewById(R.id.type_iv);
        typeTv = findViewById(R.id.typename_tv);
        typeIv.setImageResource(imageId);
        typeTv.setText(typename);
    }

    private void setDate(){
        dateTv = findViewById(R.id.date_tv);
        dateTv.setOnClickListener(new OnClick());
        Calendar calendar = Calendar.getInstance();
        String todayDate = String.format("%d-%d-%d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH));
        dateTv.setText(todayDate);
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
                    Calendar calendar = Calendar.getInstance();
                    DatePickerDialog dialog = new DatePickerDialog(SingleRecordActivity.this, this,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH));
                    dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                    dialog.show();
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