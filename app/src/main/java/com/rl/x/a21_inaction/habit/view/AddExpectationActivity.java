package com.rl.x.a21_inaction.habit.view;

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

public class AddExpectationActivity extends AppCompatActivity implements HabitContract.AddExpectationView, View.OnClickListener {

    private HabitPresenter presenter;

    @BindView(R.id.expectation_name_editText)
    EditText expectationNameEditText;
    @BindView(R.id.save_expectation_button)
    Button saveExpectationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expectation);
        ButterKnife.bind(this);

        presenter = new HabitPresenter(getApplicationContext());
        presenter.setAddExpectationView(this);

        saveExpectationButton.setOnClickListener(this);
    }

    @Override
    public String getNewExpectationName() {

        return expectationNameEditText.getText().toString();
    }

    @Override
    public void finishActivity() {

        finish();
    }

    @Override
    public void onClick(View view) {

        presenter.saveNewTempExpectation();
    }
}
