package com.InAction.X.x21InAction.add_expectation.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.InAction.X.x21InAction.add_expectation.model.TempExpectation;
import com.InAction.X.x21InAction.database.AppDatabase;

import java.util.List;

public class TempExpectationViewModel extends AndroidViewModel{

    private LiveData<List<TempExpectation>> tempExpectationsLive;

    public TempExpectationViewModel(@NonNull Application application) {
        super(application);

        tempExpectationsLive = AppDatabase.getInstance(application.getApplicationContext()).tempExpectationDao().getAllTempExpectationsLive();
    }

    public LiveData<List<TempExpectation>> getTempExpectationsLive() {
        return tempExpectationsLive;
    }
}
