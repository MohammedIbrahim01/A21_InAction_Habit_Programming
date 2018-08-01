package com.InAction.X.x21InAction.temp_expectation.view;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction.temp_expectation.TempExpectationContract;
import com.InAction.X.x21InAction.intro_screens.view.IntroScreensActivity;
import com.InAction.X.x21InAction.manager.AppManager;
import com.InAction.X.x21InAction.temp_expectation.presenter.TempExpectationPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TempExpectationsFragment extends Fragment implements TempExpectationContract.View.TempExpectationsView {


    private TempExpectationPresenter presenter;
    private AppManager manager;
    private TempExpectationsAdapter adapter;
    private TempExpectationsViewModel viewModel;


    @BindView(R.id.temp_expectations_recyclerView)
    RecyclerView tempExpectationsRecyclerView;


    @OnClick(R.id.guidance_add_expectation_button)
    void addExpectation() {

        manager.goAddExpectation(((IntroScreensActivity) getActivity()).habitName);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.guide_3, container, false);
        ButterKnife.bind(this, view);


        presenter = new TempExpectationPresenter(getContext().getApplicationContext(), this);
        manager = new AppManager(getContext().getApplicationContext(), getActivity());
        adapter = new TempExpectationsAdapter();
        viewModel = ViewModelProviders.of(getActivity()).get(TempExpectationsViewModel.class);


        presenter.setupRecyclerViewAndAdapter();
        presenter.setupDisplayTempExpectationsLive();


        return view;
    }

    @Override
    public TempExpectationsAdapter getAdapter() {

        return adapter;
    }

    @Override
    public RecyclerView getRecyclerView() {

        return tempExpectationsRecyclerView;
    }

    @Override
    public TempExpectationsViewModel getViewModel() {

        return viewModel;
    }

    @Override
    public LifecycleOwner getLifecycleOwner() {

        return getActivity();
    }
}
