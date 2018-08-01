package com.InAction.X.x21InAction.achievements.presenter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.InAction.X.x21InAction.achievements.AchievementContract;
import com.InAction.X.x21InAction.achievements.model.Achievement;
import com.InAction.X.x21InAction.achievements.model.AchievementModel;
import com.InAction.X.x21InAction.achievements.view.AchievementViewModel;

import java.util.List;

public class AchievementPresenter implements AchievementContract.Presenter {

    private Fragment fragment;
    private AchievementModel model;
    private AchievementContract.View view;
    private AchievementViewModel viewModel;


    public AchievementPresenter(Fragment fragment, AchievementContract.View view) {

        this.fragment = fragment;
        model = new AchievementModel(fragment.getContext().getApplicationContext());
        this.view = view;
        viewModel = ViewModelProviders.of(fragment.getActivity()).get(AchievementViewModel.class);
    }


    /**
     * attach recyclerView with adapter
     */
    @Override
    public void attachRecyclerViewWithAdapter() {

        view.attachRecyclerViewWithAdapter();
    }


    /**
     * set observer to LiveData AchievementList and set AchievementList on change
     */
    @Override
    public void setupAchievementsLive() {

        viewModel.getAchievementList().observe(fragment.getActivity(), new Observer<List<Achievement>>() {
            @Override
            public void onChanged(@Nullable List<Achievement> achievementList) {

                view.setAchievements(achievementList);
            }
        });
    }


    /**
     * delete achievement from database then refresh achievements
     *
     * @param achievement
     */
    @Override
    public void deleteAchievement(Achievement achievement) {

        model.deleteAchievement(achievement);
    }


    @Override
    public void start() {

        attachRecyclerViewWithAdapter();
        setupAchievementsLive();
    }
}
