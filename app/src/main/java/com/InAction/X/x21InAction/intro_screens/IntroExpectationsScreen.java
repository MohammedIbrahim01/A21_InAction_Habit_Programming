package com.InAction.X.x21InAction.intro_screens;

import android.arch.lifecycle.Observer;
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
import android.widget.Toast;

import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction.add_expectation.model.TempExpectation;
import com.InAction.X.x21InAction.add_expectation.view.TempExpectationViewModel;
import com.InAction.X.x21InAction.add_expectation.view.TempExpectationsAdapter;
import com.InAction.X.x21InAction.intro_screens.view.IntroActivity;
import com.InAction.X.x21InAction.manager.AppManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class IntroExpectationsScreen extends Fragment {

    private  TempExpectationsAdapter adapter;
    private AppManager manager;

    @BindView(R.id.temp_expectations_recyclerView)
    RecyclerView tempExpectationsRecyclerView;

    @Optional
    @OnClick(R.id.guidance_add_expectation_button)
    void addExpectation() {

        manager.goAddExpectation(((IntroActivity) getActivity()).habitName);
        Toast.makeText(getContext(), "add expectation", Toast.LENGTH_SHORT).show();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guide_3, container, false);
        ButterKnife.bind(this, view);

        adapter = new TempExpectationsAdapter();
        tempExpectationsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tempExpectationsRecyclerView.setAdapter(adapter);
        tempExpectationsRecyclerView.setHasFixedSize(true);
        manager = new AppManager(getActivity().getApplicationContext(), getActivity());

        TempExpectationViewModel viewModel = ViewModelProviders.of(getActivity()).get(TempExpectationViewModel.class);
        viewModel.getTempExpectationsLive().observe(getActivity(), new Observer<List<TempExpectation>>() {
            @Override
            public void onChanged(@Nullable List<TempExpectation> tempExpectations) {

                adapter.setTempExpectationList(tempExpectations);
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}
