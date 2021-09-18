package com.kls.okane_memo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class BookkeepingActivity extends AppCompatActivity {

    public ImageView backIv;
    public TabLayout tabLayout;
    public ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookkeeping);

        backIv = findViewById(R.id.record_iv_back);
        backIv.setOnClickListener(new OnClick());
    }

    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.record_iv_back:
                    finish();
                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    break;
            }
        }
    }
}