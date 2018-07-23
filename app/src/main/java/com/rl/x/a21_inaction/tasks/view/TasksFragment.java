package com.rl.x.a21_inaction.tasks.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rl.x.a21_inaction.R;
import com.rl.x.a21_inaction.tasks.Presenter.TasksPresenter;
import com.rl.x.a21_inaction.tasks.TasksContract;
import com.rl.x.a21_inaction.tasks.model.Task;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TasksFragment extends Fragment implements TasksContract.View {

    TasksAdapter adapter;
    TasksPresenter presenter;

    @BindView(R.id.tasks_recyclerView)
    RecyclerView tasksRecyclerView;

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ButterKnife.bind(this, view);

        adapter = new TasksAdapter();

        presenter = new TasksPresenter(this, getActivity().getApplicationContext());
        presenter.start();

        return view;
    }


    @Override
    public void displayTasks(final List<Task> taskList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setTaskList(taskList);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void setupSwipeTaskFun(ItemTouchHelper itemTouchHelper) {
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);
    }

    @Override
    public TasksAdapter getAdapter() {
        return adapter;
    }

    @Override
    public RecyclerView getTasksRecyclerView() {
        return tasksRecyclerView;
    }

    @Override
    public void refreshTasksRecyclerView(final List<Task> taskList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setTaskList(taskList);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void setupRecyclerViewWithAdapter() {
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tasksRecyclerView.setAdapter(adapter);
    }

}
