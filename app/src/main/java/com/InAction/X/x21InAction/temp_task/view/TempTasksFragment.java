package com.InAction.X.x21InAction.temp_task.view;

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
import com.InAction.X.x21InAction.habit.view.CreateHabitActivity;
import com.InAction.X.x21InAction.temp_task.TempTaskContract;
import com.InAction.X.x21InAction.manager.AppManager;
import com.InAction.X.x21InAction.temp_task.presenter.TempTaskPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class TempTasksFragment extends Fragment implements TempTaskContract.View.TempTasksView{


    private TempTaskPresenter presenter;
    private TempTasksAdapter adapter;
    private TempTasksViewModel viewModel;


    @BindView(R.id.temp_tasks_recyclerView)
    RecyclerView tempTasksRecyclerView;


    @Optional
    @OnClick(R.id.guidance_add_task_button)
    void addTask() {

        AppManager.goAddTask(getActivity(), ((CreateHabitActivity) getActivity()).habitName);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.guide_6, container, false);
        ButterKnife.bind(this, view);

        presenter = new TempTaskPresenter(getContext().getApplicationContext(), this);
        adapter = new TempTasksAdapter();
        viewModel = ViewModelProviders.of(getActivity()).get(TempTasksViewModel.class);


        presenter.setupRecyclerViewAndAdapter();
        presenter.setupDisplayTempTasksLive();



        return view;
    }


    @Override
    public TempTasksAdapter getAdapter() {

        return adapter;
    }


    @Override
    public RecyclerView getRecyclerView() {

        return tempTasksRecyclerView;
    }


    @Override
    public TempTasksViewModel getViewModel() {

        return viewModel;
    }


    @Override
    public LifecycleOwner getLifecycleOwner() {

        return getActivity();
    }
}
