package com.InAction.X.x21InAction.temp_expectation;

import android.app.Activity;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.support.v7.widget.RecyclerView;

import com.InAction.X.x21InAction.temp_expectation.model.TempExpectation;
import com.InAction.X.x21InAction.temp_expectation.view.TempExpectationsAdapter;
import com.InAction.X.x21InAction.temp_expectation.view.TempExpectationsViewModel;

import java.util.List;

public interface TempExpectationContract {

    interface Model {

        void insertTempExpectation(TempExpectation tempExpectation);

        List<TempExpectation> getTempExpectationList();

        LiveData<List<TempExpectation>> getTempExpectationListLive();

        void deleteAllTempExpectations();
    }


    interface View {

        interface AddExpectationView {

            String getExpectationName();

            String getHabitName();

            void finishActivity();
        }

        interface TempExpectationsView {

            TempExpectationsAdapter getAdapter();

            RecyclerView getRecyclerView();

            TempExpectationsViewModel getViewModel();

            LifecycleOwner getLifecycleOwner();
        }
    }

    interface Presenter {

        void insertTempExpectation();

        List<TempExpectation> getTempExpectationList();

        LiveData<List<TempExpectation>> getTempExpectationListLive();

        void deleteAllTempExpectations();

        void setupRecyclerViewAndAdapter();

        void setupDisplayTempExpectationsLive();
    }
}
