package com.InAction.X.x21InAction.temp_expectation.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction.temp_expectation.TempExpectationContract;
import com.InAction.X.x21InAction.temp_expectation.presenter.TempExpectationPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddExpectationActivity extends AppCompatActivity implements TempExpectationContract.View.AddExpectationView, View.OnClickListener {


    public static final String Key_NAME_HABIT = "key-name-habit";


    private TempExpectationPresenter presenter;
    private String habitName = "";


    @BindView(R.id.add_expectation_name_editText)
    EditText nameEditText;
    @BindView(R.id.add_expectation_save_button)
    Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expectation);
        ButterKnife.bind(this);


        // display as popUp
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.7));


        Intent intent = getIntent();
        if (intent.hasExtra(Key_NAME_HABIT)) {

            habitName = intent.getStringExtra(Key_NAME_HABIT);
        }


        presenter = new TempExpectationPresenter(getApplicationContext(), this);


        saveButton.setOnClickListener(this);
    }


    @Override
    public String getExpectationName() {

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
    public void onClick(View view) {

        presenter.insertTempExpectation();
    }
}
