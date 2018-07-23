package com.rl.x.a21_inaction.day_zero.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rl.x.a21_inaction.R;
import com.rl.x.a21_inaction.day_zero.ExpectationContract;
import com.rl.x.a21_inaction.day_zero.model.Expectation;
import com.rl.x.a21_inaction.day_zero.presenter.ExpectationPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DayZeroFragment extends Fragment implements ExpectationContract.View {

    private ExpectationAdapter adapter = new ExpectationAdapter();
    private ExpectationContract.Presenter presenter;

    @BindView(R.id.expectations_recyclerView)
    RecyclerView expectationsRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_zero, container, false);
        ButterKnife.bind(this, view);

        presenter = new ExpectationPresenter(getActivity().getApplicationContext(), this);
        presenter.start();

        return view;
    }

    @Override
    public void setupRecyclerViewWithAdapter() {
        expectationsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        expectationsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void displayExpectations(final List<Expectation> expectationList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setExpectationList(expectationList);
                adapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void refreshExpectations(final List<Expectation> expectationList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setExpectationList(expectationList);
                adapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public ExpectationAdapter getAdapter() {
        return adapter;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return expectationsRecyclerView;
    }
}
