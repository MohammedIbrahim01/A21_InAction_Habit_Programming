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
import android.widget.TextView;

import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction.temp_expectation.model.TempExpectation;
import com.InAction.X.x21InAction.temp_expectation.view.TempExpectationsAdapter;
import com.InAction.X.x21InAction.temp_expectation.view.TempExpectationsViewModel;
import com.InAction.X.x21InAction.temp_task.model.TempTask;
import com.InAction.X.x21InAction.temp_task.view.TempTasksAdapter;
import com.InAction.X.x21InAction.temp_task.view.TempTasksViewModel;
import com.InAction.X.x21InAction.manager.AppManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroHabitOverviewScreen extends Fragment {

    private TempTasksAdapter tempTaskAdapter;
    private TempExpectationsAdapter tempExpectationsAdapter;
    private TempTasksViewModel tempTasksViewModel;
    private TempExpectationsViewModel tempExpectationsViewModel;
    private AppManager manager;

    @BindView(R.id.habit_overview_habit_name_textView)
    TextView habitNameTextView;
    @BindView(R.id.habit_overview_tasks_recyclerView)
    RecyclerView tasksRecyclerView;
    @BindView(R.id.habit_overview_expectations_recyclerView)
    RecyclerView expectationsRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.habit_over_view, container, false);
        ButterKnife.bind(this, view);

        tempTaskAdapter = new TempTasksAdapter();
        tempExpectationsAdapter = new TempExpectationsAdapter();
        manager = new AppManager(getActivity().getApplicationContext());

        tempTasksViewModel = ViewModelProviders.of(getActivity()).get(TempTasksViewModel.class);
        tempExpectationsViewModel = ViewModelProviders.of(getActivity()).get(TempExpectationsViewModel.class);

        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tasksRecyclerView.setAdapter(tempTaskAdapter);
        expectationsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        expectationsRecyclerView.setAdapter(tempExpectationsAdapter);


        tempTasksViewModel.getTempTasksList().observe(getActivity(), new Observer<List<TempTask>>() {
            @Override
            public void onChanged(@Nullable List<TempTask> tempTasks) {

                tempTaskAdapter.setTempTaskList(tempTasks);
                tempTaskAdapter.notifyDataSetChanged();
            }
        });

        tempExpectationsViewModel.getTempExpectationsLive().observe(getActivity(), new Observer<List<TempExpectation>>() {
            @Override
            public void onChanged(@Nullable List<TempExpectation> tempExpectations) {

                tempExpectationsAdapter.setTempExpectationList(tempExpectations);
                tempExpectationsAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}
