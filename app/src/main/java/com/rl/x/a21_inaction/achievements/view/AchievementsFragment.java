package com.rl.x.a21_inaction.achievements.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rl.x.a21_inaction.R;
import com.rl.x.a21_inaction.achievements.AchievementContract;
import com.rl.x.a21_inaction.achievements.model.Achievement;
import com.rl.x.a21_inaction.achievements.presenter.AchievementPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AchievementsFragment extends Fragment implements AchievementContract.View {

    private AchievementsAdapter adapter = new AchievementsAdapter();
    private AchievementPresenter presenter;

    @BindView(R.id.achievements_recyclerView)
    RecyclerView achievementsRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achievements, container, false);
        ButterKnife.bind(this, view);

        presenter = new AchievementPresenter(getActivity().getApplicationContext(), this);

        presenter.start();

        return view;
    }

    @Override
    public void displayAchievements(final List<Achievement> achievementList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setAchievementList(achievementList);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public AchievementsAdapter getAdapter() {
        return adapter;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return achievementsRecyclerView;
    }

    @Override
    public void refreshAchievementRecyclerView(List<Achievement> achievementList) {
        adapter.setAchievementList(achievementList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setupRecyclerViewWithAdapter() {
        achievementsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        achievementsRecyclerView.setAdapter(adapter);
    }
}
