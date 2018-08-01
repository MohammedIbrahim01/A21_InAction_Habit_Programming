package com.InAction.X.x21InAction.temp_expectation.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.InAction.X.x21InAction.temp_expectation.TempExpectationContract;
import com.InAction.X.x21InAction.temp_expectation.model.TempExpectationModel;
import com.InAction.X.x21InAction.temp_expectation.model.TempExpectation;
import com.InAction.X.x21InAction.temp_expectation.view.TempExpectationsAdapter;
import com.InAction.X.x21InAction.temp_expectation.view.TempExpectationsViewModel;

import java.util.List;

public class TempExpectationPresenter implements TempExpectationContract.Presenter {


    private TempExpectationModel model;
    private Context applicationContext;
    private TempExpectationContract.View.AddExpectationView addExpectationView;
    private TempExpectationContract.View.TempExpectationsView tempExpectationsView;


    /**
     * first constructor to use in AddExpectationActivity
     *
     * @param applicationContext
     * @param addExpectationView
     */
    public TempExpectationPresenter(Context applicationContext, TempExpectationContract.View.AddExpectationView addExpectationView) {

        model = new TempExpectationModel(applicationContext);
        this.applicationContext = applicationContext;
        this.addExpectationView = addExpectationView;
    }


    /**
     * second constructor to use in TempExpectationsFragment
     *
     * @param applicationContext
     * @param tempExpectationsView
     */

    public TempExpectationPresenter(Context applicationContext, TempExpectationContract.View.TempExpectationsView tempExpectationsView) {

        model = new TempExpectationModel(applicationContext);
        this.applicationContext = applicationContext;
        this.tempExpectationsView = tempExpectationsView;
    }


    /**
     * insert TempExpectation (Model)
     *
     * then finish the pop up AddExpectationActivity
     */
    @Override
    public void insertTempExpectation() {

        model.insertTempExpectation(new TempExpectation(addExpectationView.getExpectationName(), addExpectationView.getHabitName()));
        addExpectationView.finishActivity();
    }


    /**
     * get TempExpectation List (Model)
     *
     * @return
     */
    @Override
    public List<TempExpectation> getTempExpectationList() {

        return model.getTempExpectationList();
    }


    /**
     * get TempExpectation List Live (Model)
     *
     * @return
     */
    @Override
    public LiveData<List<TempExpectation>> getTempExpectationListLive() {

        return model.getTempExpectationListLive();
    }


    /**
     * delete all TempExpectations (Model)
     */
    @Override
    public void deleteAllTempExpectations() {

        model.deleteAllTempExpectations();
    }


    /**
     * setup recyclerView and adapter from tempExpectationsFragment
     */
    @Override
    public void setupRecyclerViewAndAdapter() {

        TempExpectationsAdapter adapter = tempExpectationsView.getAdapter();
        RecyclerView recyclerView = tempExpectationsView.getRecyclerView();

        recyclerView.setLayoutManager(new LinearLayoutManager(applicationContext));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }


    /**
     * get TempExpectations List Live from tempExpectationsViewModel
     *
     * and set adapter's tempExpectationList to its value
     */
    @Override
    public void setupDisplayTempExpectationsLive() {

        TempExpectationsViewModel viewModel = tempExpectationsView.getViewModel();
        final TempExpectationsAdapter adapter = tempExpectationsView.getAdapter();


        viewModel.getTempExpectationsLive().observe(tempExpectationsView.getLifecycleOwner(), new Observer<List<TempExpectation>>() {
            @Override
            public void onChanged(@Nullable List<TempExpectation> tempExpectations) {

                adapter.setTempExpectationList(tempExpectations);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
