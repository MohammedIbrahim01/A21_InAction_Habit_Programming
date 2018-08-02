package com.InAction.X.x21InAction.tasks.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction.tasks.model.Task;
import com.InAction.X.x21InAction.utils.StringFormats;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {


    private List<Task> taskList;


    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }


    public List<Task> getTaskList() {
        return taskList;
    }


    @NonNull
    @Override
    public TasksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TasksAdapter.ViewHolder holder, int position) {

        Task currentTask = taskList.get(position);


        holder.nameTextView.setText(currentTask.getName());
        holder.timeTextView.setText(StringFormats.formattedTime(currentTask.getCalendar()));
    }


    @Override
    public int getItemCount() {

        if (taskList == null)
            return 0;
        return taskList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.task_row_name_textView)
        TextView nameTextView;
        @BindView(R.id.task_row_time_textView)
        TextView timeTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
