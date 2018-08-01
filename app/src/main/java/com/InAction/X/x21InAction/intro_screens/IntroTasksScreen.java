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
import com.InAction.X.x21InAction.add_task.model.TempTask;
import com.InAction.X.x21InAction.add_task.view.TempTasksAdapter;
import com.InAction.X.x21InAction.add_task.view.TempTasksViewModel;
import com.InAction.X.x21InAction.intro_screens.view.IntroScreensActivity;
import com.InAction.X.x21InAction.manager.AppManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class IntroTasksScreen extends Fragment {

    private TempTasksAdapter adapter;
    private AppManager manager;

    @BindView(R.id.temp_tasks_recyclerView)
    RecyclerView tempTasksRecyclerView;

    @Optional
    @OnClick(R.id.guidance_add_task_button)
    void addTask() {

        manager.goAddTask(((IntroScreensActivity) getActivity()).habitName);
        Toast.makeText(getContext(), "add task", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guide_5, container, false);
        ButterKnife.bind(this, view);

        adapter = new TempTasksAdapter();
        tempTasksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tempTasksRecyclerView.setAdapter(adapter);
        tempTasksRecyclerView.setHasFixedSize(true);
        manager = new AppManager(getActivity().getApplicationContext(), getActivity());

        TempTasksViewModel viewModel = ViewModelProviders.of(getActivity()).get(TempTasksViewModel.class);
        viewModel.getTempTasksList().observe(getActivity(), new Observer<List<TempTask>>() {
            @Override
            public void onChanged(@Nullable List<TempTask> tempTasks) {

                adapter.setTempTaskList(tempTasks);
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }
}
