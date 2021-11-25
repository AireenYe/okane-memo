package com.kls.okane_memo.util;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kls.okane_memo.db.DataSource;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final DataSource mDataSource;

    public ViewModelFactory(DataSource dataSource) {
        mDataSource = dataSource;
    }

    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RecordViewModel.class)) {
            return (T) new RecordViewModel(mDataSource);
        }
        //noinspection unchecked
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
