package com.kls.okane_memo.db;

import android.content.Context;

import com.kls.okane_memo.ui.ViewModelFactory;

/**
 * Enables injection of data sources.
 */
public class Injection {

    public static DataSource provideRecordDataSource(Context context) {
        OkaneDB database = OkaneDB.getInstance(context);
        return new LocalDataSource(database.recordDao());
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        DataSource dataSource = provideRecordDataSource(context);
        return new ViewModelFactory(dataSource);
    }
}
