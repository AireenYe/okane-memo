package com.kls.okane_memo.frag_record;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kls.okane_memo.R;

public class RecordFragment extends Fragment {
    private RecyclerView typeRvGrid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record_type, container, false);
        // 填充GridView数据
        typeRvGrid = view.findViewById(R.id.type_rv);
        typeRvGrid.setLayoutManager(new GridLayoutManager(this.getActivity(), 4));
        typeRvGrid.setAdapter(new TypeGridAdapter(this.getActivity()));

        return view;
    }
}
