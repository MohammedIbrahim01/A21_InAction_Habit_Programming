package com.InAction.X.x21InAction.achievements.model;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.InAction.X.x21InAction.achievements.AchievementContract;
import com.InAction.X.x21InAction.database.AppDatabase;
import com.InAction.X.x21InAction.utils.AppExecutors;

import java.util.List;
import java.util.concurrent.Executor;

public class AchievementModel implements AchievementContract.Model {

    private Executor diskIOExecutor;
    private AchievementDao achievementDao;

    public AchievementModel(Context applicationContext) {

        diskIOExecutor = AppExecutors.getInstance().getDiskIO();
        achievementDao = AppDatabase.getInstance(applicationContext).achievementDao();
    }


    @Override
    public void insertAchievement(final Achievement achievement) {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {
                achievementDao.insertAchievement(achievement);
            }
        });
    }


    @Override
    public void deleteAchievement(final Achievement achievement) {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {
                achievementDao.deleteAchievement(achievement);
            }
        });
    }
}
