package com.kls.okane_memo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleRecordActivity extends AppCompatActivity {

    EditText et;
    ImageView backIv, typeIv;
    TextView kindTv, typeTv;
    Bundle typeBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_record);
        typeBundle = getIntent().getExtras();

        setKindString();
        setInput();
        setTypeRow();


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

    private void setInput(){
        et = findViewById(R.id.input_money);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/bahnschrift.ttf");
        et.setTypeface(tf);
    }

    private void setTypeRow(){
        String typename = typeBundle.getString("typename");
        int imageId = typeBundle.getInt("imageId");
        typeIv = findViewById(R.id.type_iv);
        typeTv = findViewById(R.id.typename_tv);
        typeIv.setImageResource(imageId);
        typeTv.setText(typename);
    }

    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.single_record_iv_back:
                    finish();
                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    break;
            }
        }
    }
}