package com.rl.x.a21_inaction.add_task.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.rl.x.a21_inaction.R;
import com.rl.x.a21_inaction.add_task.AddTaskContract;
import com.rl.x.a21_inaction.add_task.presenter.AddTaskPresenter;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddTaskActivity extends AppCompatActivity  implements AddTaskContract.View, View.OnClickListener {

    public static final String NAME_HABIT = "name-habit";

    private AddTaskPresenter presenter;

    @BindView(R.id.task_name_editText)
    EditText taskNameEditText;
    @BindView(R.id.save_task_button)
    Button saveTaskButton;
    @BindView(R.id.timePicker)
    TimePicker timePicker;
    private String habitName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ButterKnife.bind(this);

        presenter = new AddTaskPresenter(getApplicationContext(), this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(NAME_HABIT)){

            habitName = intent.getStringExtra(NAME_HABIT);
        }

        saveTaskButton.setOnClickListener(this);
    }

    @Override
    public String getNewTaskName() {
        return taskNameEditText.getText().toString();
    }

    @Override
    public String getHabitName() {
        return habitName;
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public Calendar getNewTaskCalendar() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
        calendar.set(Calendar.SECOND, 0);

        return calendar;
    }

    @Override
    public void onClick(View view) {

        presenter.insertTempTask();
    }
}
