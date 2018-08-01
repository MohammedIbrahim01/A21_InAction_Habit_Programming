package com.InAction.X.x21InAction.tasks.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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


    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ButterKnife.bind(this, view);

        presenter = new TasksPresenter(this, this);

        presenter.start();

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
    public void setTasks(final List<Task> taskList) {

        adapter.setTaskList(taskList);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void attachRecyclerViewWithAdapter() {

        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tasksRecyclerView.setAdapter(adapter);
    }

}
