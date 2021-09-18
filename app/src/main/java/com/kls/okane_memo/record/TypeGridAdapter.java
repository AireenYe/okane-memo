package com.kls.okane_memo.record;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kls.okane_memo.R;

public class TypeGridAdapter extends RecyclerView.Adapter<TypeGridAdapter.GridViewHolder> {
    private Context mContext;

    public TypeGridAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public TypeGridAdapter.GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new GridViewHolder(LayoutInflater.from(mContext).inflate(R.layout.grid_item_type,parent,false));
    }

    @Override
    public void onBindViewHolder(TypeGridAdapter.GridViewHolder holder, final int position) {
        holder.textView.setText("测试");
        holder.imageView.setImageResource(R.drawable.test);
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class GridViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;

        public GridViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.item_type_tv);
            imageView = view.findViewById(R.id.item_type_iv);
        }
    }

}
