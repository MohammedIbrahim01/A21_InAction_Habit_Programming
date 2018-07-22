package com.rl.x.a21_inaction.achievements.view;

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

public class AchievementsFragment extends Fragment {

    @BindView(R.id.achievements_recyclerView)
    RecyclerView achievementsRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achievements, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
