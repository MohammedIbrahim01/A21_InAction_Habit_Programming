package com.InAction.X.x21InAction.temp_task.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction.temp_task.model.TempTask;
import com.InAction.X.x21InAction.utils.StringFormats;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TempTasksAdapter extends RecyclerView.Adapter<TempTasksAdapter.ViewHolder> {


    private List<TempTask> tempTaskList = new ArrayList<>();


    public void setTempTaskList(List<TempTask> tempTaskList) {

        this.tempTaskList = tempTaskList;
    }


    @NonNull
    @Override
    public TempTasksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TempTasksAdapter.ViewHolder holder, int position) {

        holder.nameTextView.setText(tempTaskList.get(position).getName());
        holder.timeTextView.setText(StringFormats.formattedTime(tempTaskList.get(position).getCalendar()));
    }


    @Override
    public int getItemCount() {

        return tempTaskList.size();
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
