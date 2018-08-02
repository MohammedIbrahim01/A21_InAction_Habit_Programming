package com.InAction.X.x21InAction.expectation.presenter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.InAction.X.x21InAction.expectation.ExpectationContract;
import com.InAction.X.x21InAction.expectation.model.Expectation;
import com.InAction.X.x21InAction.expectation.model.ExpectationModel;
import com.InAction.X.x21InAction.expectation.view.ExpectationAdapter;
import com.InAction.X.x21InAction.expectation.view.ExpectationViewModel;

import java.util.List;

public class ExpectationPresenter implements ExpectationContract.Presenter {

    private ExpectationModel model;
    private ExpectationContract.View view;
    private ExpectationViewModel viewModel;
    private Context context;


    public ExpectationPresenter(Context applicationContext){

        this.context = applicationContext;
    }

    public ExpectationPresenter(Context context, ExpectationContract.View view) {

        this.context = context;
        model = new ExpectationModel(context.getApplicationContext());
        this.view = view;
        viewModel = view.getViewModel();
    }


    /**
     * setup recyclerView with adapter
     */
    @Override
    public void setupRecyclerViewWithAdapter() {

        ExpectationAdapter adapter = view.getAdapter();
        RecyclerView recyclerView = view.getRecyclerView();

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }


    /**
     * setup Expectation ViewModel
     */
    @Override
    public void setupExpectationLive() {

        viewModel.getExpectations().observe(view.getLifeCycleOwner(), new Observer<List<Expectation>>() {
            @Override
            public void onChanged(@Nullable List<Expectation> expectationList) {

                view.setExpectations(expectationList);
            }
        });
    }


    /**
     * insert Expectation List (Model)
     *
     * @param expectationList
     */
    @Override
    public void insertExpectationList(List<Expectation> expectationList) {

        model.insertExpectationList(expectationList);
    }
}
