package com.rl.x.a21_inaction.achievements.presenter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.rl.x.a21_inaction.achievements.AchievementContract;
import com.rl.x.a21_inaction.achievements.model.Achievement;
import com.rl.x.a21_inaction.achievements.model.AchievementModel;
import com.rl.x.a21_inaction.achievements.view.AchievementViewModel;

import java.util.List;

public class AchievementPresenter implements AchievementContract.Presenter {

    private AchievementContract.View view;
    private AchievementModel model;
    private Fragment fragment;
    private AchievementViewModel viewModel;


    public AchievementPresenter(Fragment fragment, AchievementContract.View view) {

        this.view = view;
        model = new AchievementModel(fragment.getContext().getApplicationContext());
        this.fragment = fragment;
        viewModel = ViewModelProviders.of(fragment).get(AchievementViewModel.class);
    }


    /**
     * setup recyclerView with adapter
     *
     */
    @Override
    public void setupRecyclerViewWithAdapter() {

        view.setupRecyclerViewWithAdapter();
    }


    @Override
    public void setupAchievementsLive(){

        viewModel.getAchievementList().observe(fragment.getActivity(), new Observer<List<Achievement>>() {
            @Override
            public void onChanged(@Nullable List<Achievement> achievementList) {
                view.displayAchievements(achievementList);
            }
        });
    }


    /**
     * insert Achievements into database for testing
     *
     */
    @Override
    public void insertMockAchievementsIntoDatabase() {

        model.insertMockAchievements();
    }


    /**
     * delete achievement from database then refresh achievements
     *
     * @param achievement
     */
    @Override
    public void deleteAchievement(Achievement achievement) {

        model.deleteAchievement(achievement);

        view.refreshAchievementRecyclerView(model.retrieveAchievements());
    }


    @Override
    public void start() {

        setupRecyclerViewWithAdapter();
        setupAchievementsLive();
    }
}
