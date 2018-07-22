package com.rl.x.a21_inaction.day_zero.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rl.x.a21_inaction.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DayZeroFragment extends Fragment {

    @BindView(R.id.expectations_recyclerView)
    RecyclerView expectationsRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_zero, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
