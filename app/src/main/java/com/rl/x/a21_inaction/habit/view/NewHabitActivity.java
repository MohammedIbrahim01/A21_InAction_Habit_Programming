package com.rl.x.a21_inaction.habit.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rl.x.a21_inaction.R;
import com.rl.x.a21_inaction.habit.HabitContract;
import com.rl.x.a21_inaction.habit.presenter.HabitPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewHabitActivity extends AppCompatActivity implements HabitContract.NewHabitView, View.OnClickListener {

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

        presenter = new HabitPresenter(getApplicationContext());
        presenter.setNewHabitView(this);

        addTaskButton.setOnClickListener(this);
        addExpectationButton.setOnClickListener(this);
        saveHabitButton.setOnClickListener(this);
    }


    @Override
    public void goAddTask() {

        startActivity(new Intent(NewHabitActivity.this, AddTaskActivity.class));
    }

    @Override
    public void goAddExpectation() {

        startActivity(new Intent(NewHabitActivity.this, AddExpectationActivity.class));
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
                presenter.goAddTask();
                break;
            case R.id.add_expectation_button:
                presenter.goAddExpectation();
                break;
            case R.id.save_habit_button:
                presenter.saveNewHabit();
                break;
        }
    }
}
