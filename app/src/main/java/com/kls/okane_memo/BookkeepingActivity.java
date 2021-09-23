package com.kls.okane_memo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.kls.okane_memo.record.TypeGridAdapter;

public class BookkeepingActivity extends AppCompatActivity {

    private ImageView backIv;
    private RecyclerView inRvGrid, outRvGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookkeeping);

        inRvGrid = findViewById(R.id.record_in_gv);
        inRvGrid.setLayoutManager(new GridLayoutManager(this, 3));
        inRvGrid.setAdapter(new TypeGridAdapter(this, 1));
//        setRvGridOnClick(inRvGrid);

        outRvGrid = findViewById(R.id.record_out_gv);
        outRvGrid.setLayoutManager(new GridLayoutManager(this, 3));
        outRvGrid.setAdapter(new TypeGridAdapter(this, -1));
//        setRvGridOnClick(outRvGrid);


        backIv = findViewById(R.id.record_iv_back);
        backIv.setOnClickListener(new OnClick());
    }

    private void setRvGridOnClick(RecyclerView rvGrid){
        rvGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookkeepingActivity.this, SingleRecordActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });
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