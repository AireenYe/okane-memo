package com.kls.okane_memo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.kls.okane_memo.frag_record.RecordAdapter;
import com.kls.okane_memo.frag_record.RecordFragment;

import java.util.ArrayList;
import java.util.List;

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

        initPages();

    }

    private void initPages(){
        tabLayout = findViewById(R.id.record_tabs);
        viewPager = findViewById(R.id.record_vp);

        List<Fragment> fragmentList = new ArrayList<>();
        RecordFragment f1 = new RecordFragment();
        RecordFragment f2 = new RecordFragment();
        fragmentList.add(f1);
        fragmentList.add(f2);
        RecordAdapter recordAdapter = new RecordAdapter(getSupportFragmentManager(), getLifecycle(), fragmentList);
        viewPager.setAdapter(recordAdapter);
        ArrayList<String> title = new ArrayList<>();
        title.add("支出");
        title.add("收入");

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(title.get(position));
            }
        }).attach();

//        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                super.onPageScrollStateChanged(state);
//            }
//        });
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