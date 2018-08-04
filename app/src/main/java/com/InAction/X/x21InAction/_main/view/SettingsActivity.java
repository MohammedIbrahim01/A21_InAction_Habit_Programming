package com.InAction.X.x21InAction._main.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.InAction.X.x21InAction.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
