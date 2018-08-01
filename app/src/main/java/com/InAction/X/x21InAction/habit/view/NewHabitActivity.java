package com.InAction.X.x21InAction.habit.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction.habit.HabitContract;
import com.InAction.X.x21InAction.habit.presenter.HabitPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewHabitActivity extends AppCompatActivity implements HabitContract.View, View.OnClickListener {

    private HabitPresenter presenter;

    @BindView(R.id.habit_name_editText)
    EditText habitNameEditText;
    @BindView(R.id.add_task_button)
    Button addTaskButton;
    @BindView(R.id.add_expectation_button)
    Button addExpectationButton;
    @BindView(R.id.save_habit_button)
    Button saveHabitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);
        ButterKnife.bind(this);

        presenter = new HabitPresenter(this, this);

        addTaskButton.setOnClickListener(this);
        addExpectationButton.setOnClickListener(this);
        saveHabitButton.setOnClickListener(this);
    }


    @Override
    public void finishActivity() {

        finish();
    }


    @Override
    public String getHabitName() {

        return habitNameEditText.getText().toString();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.add_task_button:
                presenter.goAddTask(getHabitName());
                break;

            case R.id.add_expectation_button:
                presenter.goAddExpectation(getHabitName());
                break;

            case R.id.save_habit_button:
                presenter.saveNewHabit();
                break;
        }
    }
}