package com.rl.x.a21_inaction.tasks.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rl.x.a21_inaction.R;
import com.rl.x.a21_inaction.tasks.model.Task;

import java.util.Calendar;
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
        holder.dayTextView.setText(formattedTaskTime(currentTask.getCalendar()));
    }

    /**
     * format taskTime from calendar
     *
     * @param calendar
     * @return
     */
    private String formattedTaskTime(Calendar calendar) {

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        String formattedTime = "";
        String AM_PM = "";

        if (hour < 12) {

            AM_PM = "am";
        } else {
            hour -= 12;
            AM_PM = "pm";
        }

        formattedTime = hour + " : " + minute + " " + AM_PM;

        return formattedTime;
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
        @BindView(R.id.task_row_day_textView)
        TextView dayTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
