package com.kls.okane_memo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleRecordActivity extends AppCompatActivity {

    ImageView backIv, typeIv;
    TextView typeTv;
    Bundle typeBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_record);
        typeBundle = getIntent().getExtras();
        setTypeRow();

        backIv = findViewById(R.id.single_record_iv_back);
        backIv.setOnClickListener(new OnClick());
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