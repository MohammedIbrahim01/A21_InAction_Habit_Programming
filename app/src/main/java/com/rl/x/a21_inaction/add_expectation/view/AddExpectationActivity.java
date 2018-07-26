package com.rl.x.a21_inaction.add_expectation.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rl.x.a21_inaction.R;
import com.rl.x.a21_inaction.add_expectation.AddExpectationContract;
import com.rl.x.a21_inaction.add_expectation.presenter.AddExpectationPresenter;
import com.rl.x.a21_inaction.habit.HabitContract;
import com.rl.x.a21_inaction.habit.presenter.HabitPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddExpectationActivity extends AppCompatActivity implements AddExpectationContract.View, View.OnClickListener {

    private AddExpectationPresenter presenter;

    @BindView(R.id.expectation_name_editText)
    EditText expectationNameEditText;
    @BindView(R.id.save_expectation_button)
    Button saveExpectationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expectation);
        ButterKnife.bind(this);

        presenter = new AddExpectationPresenter(getApplicationContext(), this);

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

        presenter.insertTempExpectation();
    }
}
