package com.InAction.X.x21InAction.expectation.view;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction.expectation.ExpectationContract;
import com.InAction.X.x21InAction.expectation.model.Expectation;
import com.InAction.X.x21InAction.expectation.presenter.ExpectationPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpectationFragment extends Fragment implements ExpectationContract.View {


    private ExpectationAdapter adapter = new ExpectationAdapter();
    private ExpectationContract.Presenter presenter;


    @BindView(R.id.expectations_recyclerView)
    RecyclerView expectationsRecyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_zero, container, false);
        ButterKnife.bind(this, view);

        presenter = new ExpectationPresenter(getContext(), this);


        presenter.setupRecyclerViewWithAdapter();
        presenter.setupExpectationLive();


        return view;
    }


    @Override
    public ExpectationAdapter getAdapter() {

        return adapter;
    }


    @Override
    public RecyclerView getRecyclerView() {

        return expectationsRecyclerView;
    }

    @Override
    public LifecycleOwner getLifeCycleOwner() {

        return getActivity();
    }

    @Override
    public ExpectationViewModel getViewModel() {

        return ViewModelProviders.of(getActivity()).get(ExpectationViewModel.class);
    }


    @Override
    public void setExpectations(final List<Expectation> expectationList) {

        adapter.setExpectationList(expectationList);
        adapter.notifyDataSetChanged();
    }
}
