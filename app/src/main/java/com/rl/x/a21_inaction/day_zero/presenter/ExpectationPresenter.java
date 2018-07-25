package com.rl.x.a21_inaction.day_zero.presenter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.day_zero.ExpectationContract;
import com.rl.x.a21_inaction.day_zero.model.Expectation;
import com.rl.x.a21_inaction.day_zero.model.ExpectationDao;
import com.rl.x.a21_inaction.day_zero.model.ExpectationModel;
import com.rl.x.a21_inaction.day_zero.view.ExpectationViewModel;
import com.rl.x.a21_inaction.utils.AppExecutors;

import java.util.List;
import java.util.concurrent.Executor;

public class ExpectationPresenter implements ExpectationContract.Presenter {

    private ExpectationContract.View view;
    private ExpectationModel model;
    private ExpectationViewModel viewModel;
    private Fragment fragment;


    public ExpectationPresenter(Fragment fragment, ExpectationContract.View view) {

        this.view = view;
        model = new ExpectationModel(fragment.getActivity().getApplicationContext());
        viewModel = ViewModelProviders.of(fragment.getActivity()).get(ExpectationViewModel.class);
        this.fragment = fragment;
    }

    /**
     * setup recyclerView with adapter
     */
    @Override
    public void setupRecyclerViewWithAdapter() {
        view.setupRecyclerViewWithAdapter();
    }


    /**
     * setup Expectation ViewModel
     *
     */
    @Override
    public void setupExpectationLive() {

        viewModel.getExpectations().observe(fragment.getActivity(), new Observer<List<Expectation>>() {
            @Override
            public void onChanged(@Nullable List<Expectation> expectationList) {
                view.refreshExpectations(expectationList);
            }
        });
    }


    /**
     * insert expectation to database
     *
     * @param name
     */
    @Override
    public void insertExpectation(String name) {

        model.insertExpectation(new Expectation(name));
    }


    /**
     * insert mock Expectations
     *
     */
    @Override
    public void insertMockExpectations() {

        model.insertMockExpectation();
    }


    /**
     * delete expectation from database
     *
     * @param expectation
     */
    @Override
    public void deleteExpectation(Expectation expectation) {

        model.deleteExpectation(expectation);
    }


    /**
     * start ExpectationPresentation
     *
     */
    @Override
    public void start() {

        setupRecyclerViewWithAdapter();
        setupExpectationLive();
    }
}
