package com.rl.x.a21_inaction.add_task.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rl.x.a21_inaction.R;
import com.rl.x.a21_inaction.add_task.AddTaskContract;
import com.rl.x.a21_inaction.add_task.presenter.AddTaskPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddTaskActivity extends AppCompatActivity  implements AddTaskContract.View, View.OnClickListener {

    private AddTaskPresenter presenter;

    @BindView(R.id.task_name_editText)
    EditText taskNameEditText;
    @BindView(R.id.save_task_button)
    Button saveTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ButterKnife.bind(this);

        presenter = new AddTaskPresenter(getApplicationContext(), this);

        saveTaskButton.setOnClickListener(this);
    }

    @Override
    public String getNewTaskName() {
        return taskNameEditText.getText().toString();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void onClick(View view) {

        presenter.insertTempTask();
    }
}