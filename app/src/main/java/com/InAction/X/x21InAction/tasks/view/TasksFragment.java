package com.InAction.X.x21InAction.tasks.view;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction.tasks.Presenter.TasksPresenter;
import com.InAction.X.x21InAction.tasks.TasksContract;
import com.InAction.X.x21InAction.tasks.model.Task;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TasksFragment extends Fragment implements TasksContract.View {

    TasksAdapter adapter = new TasksAdapter();
    TasksPresenter presenter;

    @BindView(R.id.tasks_recyclerView)
    RecyclerView tasksRecyclerView;
    private TasksViewModel viewModel;


    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ButterKnife.bind(this, view);


        presenter = new TasksPresenter(getContext().getApplicationContext(), this);
        viewModel = ViewModelProviders.of(getActivity()).get(TasksViewModel.class);


        presenter.setUpRecyclerViewWithAdapter();
        presenter.setupTasksLive();
        presenter.setupSwipeTaskFunctionality();


        return view;
    }


    @Override
    public void setupSwipeTaskFunctionality(ItemTouchHelper itemTouchHelper) {

        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);
    }


    @Override
    public TasksAdapter getAdapter() {

        return adapter;
    }

    @Override
    public RecyclerView getRecyclerView() {

        return tasksRecyclerView;
    }


    @Override
    public void setTasks(final List<Task> taskList) {

        adapter.setTaskList(taskList);
        adapter.notifyDataSetChanged();
    }


    @Override
    public TasksViewModel getViewModel() {

        return viewModel;
    }

    @Override
    public LifecycleOwner getLifeCycleOwner() {

        return getActivity();
    }

}
