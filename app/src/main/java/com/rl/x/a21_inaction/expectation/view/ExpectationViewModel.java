package com.rl.x.a21_inaction.expectation.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.expectation.model.Expectation;

import java.util.List;

public class ExpectationViewModel extends AndroidViewModel {

    private LiveData<List<Expectation>> expectations;


    public ExpectationViewModel(@NonNull Application application) {
        super(application);

        expectations = AppDatabase.getInstance(application.getApplicationContext()).expectationDao().getAllExpectationsLive();
    }


    public LiveData<List<Expectation>> getExpectations() {
        return expectations;
    }
}
