package com.InAction.X.x21InAction.expectation.presenter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.InAction.X.x21InAction.expectation.ExpectationContract;
import com.InAction.X.x21InAction.expectation.model.Expectation;
import com.InAction.X.x21InAction.expectation.model.ExpectationModel;
import com.InAction.X.x21InAction.expectation.view.ExpectationViewModel;

import java.util.List;

public class ExpectationPresenter implements ExpectationContract.Presenter {

    private Fragment fragment;
    private ExpectationModel model;
    private ExpectationContract.View view;
    private ExpectationViewModel viewModel;


    public ExpectationPresenter(Fragment fragment, ExpectationContract.View view) {

        this.fragment = fragment;
        model = new ExpectationModel(fragment.getActivity().getApplicationContext());
        this.view = view;
        viewModel = ViewModelProviders.of(fragment.getActivity()).get(ExpectationViewModel.class);
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

                view.setExpectations(expectationList);
            }
        });
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
