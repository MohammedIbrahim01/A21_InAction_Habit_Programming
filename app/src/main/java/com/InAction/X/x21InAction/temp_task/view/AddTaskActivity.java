package com.InAction.X.x21InAction.temp_task.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction.temp_task.TempTaskContract;
import com.InAction.X.x21InAction.temp_task.presenter.TempTaskPresenter;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddTaskActivity extends AppCompatActivity implements TempTaskContract.View.AddTaskView, View.OnClickListener {


    public static final String KEY_NAME_HABIT = "key-name-habit";


    private TempTaskPresenter presenter;
    private String habitName = "";


    @BindView(R.id.add_task_name_editText)
    EditText nameEditText;
    @BindView(R.id.add_task_save_button)
    Button saveButton;
    @BindView(R.id.add_task_timePicker)
    TimePicker timePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ButterKnife.bind(this);


        // display as popUp
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.7));


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(KEY_NAME_HABIT)) {

            habitName = intent.getStringExtra(KEY_NAME_HABIT);
        }


        presenter = new TempTaskPresenter(getApplicationContext(), this);


        saveButton.setOnClickListener(this);
    }


    @Override
    public String getTaskName() {

        return nameEditText.getText().toString();
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
    public Calendar getCalendar() {

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
