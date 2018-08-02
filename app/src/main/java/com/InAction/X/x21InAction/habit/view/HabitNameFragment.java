package com.InAction.X.x21InAction.habit.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.InAction.X.x21InAction.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HabitNameFragment extends Fragment {


    @BindView(R.id.guide_habitName)
    public EditText habitNameEditText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.guide_1, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
