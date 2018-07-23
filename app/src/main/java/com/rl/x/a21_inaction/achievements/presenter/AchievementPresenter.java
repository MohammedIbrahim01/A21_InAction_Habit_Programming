package com.rl.x.a21_inaction.achievements.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.rl.x.a21_inaction.achievements.AchievementContract;
import com.rl.x.a21_inaction.achievements.model.Achievement;
import com.rl.x.a21_inaction.achievements.model.AchievementDao;
import com.rl.x.a21_inaction.achievements.model.AchievementModel;
import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.day_zero.model.ExpectationDao;
import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.utils.AppExecutors;

import java.util.List;
import java.util.concurrent.Executor;

public class AchievementPresenter implements AchievementContract.Presenter {

    private AchievementContract.View view;

    private AchievementModel model;


    public AchievementPresenter(Context applicationContext, AchievementContract.View view) {

        this.view = view;
        model = new AchievementModel(applicationContext);
    }


    /**
     * setup recyclerView with adapter
     */
    @Override
    public void setupRecyclerViewWithAdapter() {

        view.setupRecyclerViewWithAdapter();
    }

    /**
     * retrieve Achievements from database
     */
    @Override
    public void retrieveAndDisplayAchievements() {

        view.displayAchievements(model.retrieveAchievements());
    }


    /**
     * insert Achievement into database
     *
     * @param name
     */
    @Override
    public void insertAchievementIntoDatabase(String name) {

        model.insertAchievement(new Achievement(name));
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
        retrieveAndDisplayAchievements();
    }
}
