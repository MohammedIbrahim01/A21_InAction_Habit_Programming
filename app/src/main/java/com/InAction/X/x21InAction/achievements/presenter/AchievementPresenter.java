package com.InAction.X.x21InAction.achievements.presenter;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.InAction.X.x21InAction.achievements.AchievementContract;
import com.InAction.X.x21InAction.achievements.model.Achievement;
import com.InAction.X.x21InAction.achievements.model.AchievementModel;
import com.InAction.X.x21InAction.achievements.view.AchievementViewModel;
import com.InAction.X.x21InAction.achievements.view.AchievementsAdapter;

import java.util.List;

public class AchievementPresenter implements AchievementContract.Presenter {


    private AchievementModel model;
    private AchievementContract.View view;
    private AchievementViewModel viewModel;
    private Context context;


    public AchievementPresenter(Context applicationContext) {

        model = new AchievementModel(applicationContext);
    }


    public AchievementPresenter(Context context, AchievementContract.View view) {

        this.context = context;
        model = new AchievementModel(context.getApplicationContext());
        this.view = view;
        viewModel = view.getViewModel();
    }


    /**
     * attach recyclerView with adapter
     */
    @Override
    public void setupRecyclerViewWithAdapter() {

        AchievementsAdapter adapter = view.getAdapter();
        RecyclerView recyclerView = view.getRecyclerView();

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }


    /**
     * set observer to LiveData AchievementList and set AchievementList on change
     */
    @Override
    public void setupAchievementsLive() {

        viewModel.getAchievementList().observe(view.getLifeCycleOwner(), new Observer<List<Achievement>>() {
            @Override
            public void onChanged(@Nullable List<Achievement> achievementList) {

                view.setAchievementsLive(achievementList);
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


    /**
     * insert Achievement (Model)
     *
     * @param achievement
     */
    @Override
    public void insertAchievement(Achievement achievement) {

        model.insertAchievement(achievement);
    }
}
