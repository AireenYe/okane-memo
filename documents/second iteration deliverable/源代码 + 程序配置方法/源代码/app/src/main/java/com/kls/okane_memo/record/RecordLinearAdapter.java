package com.kls.okane_memo.record;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kls.okane_memo.R;
import com.kls.okane_memo.SingleRecordActivity;
import com.kls.okane_memo.db.DBManager;
import com.kls.okane_memo.db.Record;
import com.kls.okane_memo.util.type.TypeList;

import java.util.List;

public class RecordLinearAdapter extends RecyclerView.Adapter<RecordLinearAdapter.LinearViewHolder> {
    private Context context;
    int year, month, dayOfMonth;
    private List<Record> records;
    private Record record;
    int imageId;

    public RecordLinearAdapter(Context context, int year, int month, int dayOfMonth){
        this.context = context;
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        // 找到当天的记录列表
        records = new DBManager(context.getApplicationContext()).getRecordByDate(year, month, dayOfMonth);
    }

    @NonNull
    @Override
    public LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_record, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinearViewHolder holder, int position) {
        record = records.get(position);
        holder.moneyTv.setText(record.getMoney());
        holder.typeTv.setText(record.getTypename());
        imageId = TypeList.getInstance().getImageByName(record.getTypename());
        if(imageId == 0){
            // 抛出异常
        }
        holder.typeIv.setImageResource(imageId);

        // 设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SingleRecordActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("ifCreate", false);
                bundle.putInt("id", record.getId());
                bundle.putInt("kind", record.getKind());
                bundle.putString("typename", record.getTypename());
                bundle.putInt("imageId", imageId);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView moneyTv, typeTv;
        private ImageView typeIv;
        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            moneyTv = itemView.findViewById(R.id.item_record_money);
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/bahnschrift.ttf");
            moneyTv.setTypeface(tf);
            typeIv = itemView.findViewById(R.id.item_record_iv);
            typeTv = itemView.findViewById(R.id.item_record_typename);
        }
    }
}
