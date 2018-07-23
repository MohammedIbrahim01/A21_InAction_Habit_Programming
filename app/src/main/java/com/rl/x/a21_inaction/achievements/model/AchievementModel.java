package com.rl.x.a21_inaction.achievements.model;

import android.content.Context;

import com.rl.x.a21_inaction.achievements.AchievementContract;
import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.utils.AppExecutors;

import java.util.List;
import java.util.concurrent.Executor;

public class AchievementModel implements AchievementContract.Model {

    private Executor diskIOExecutor;
    private AchievementDao achievementDao;

    public AchievementModel(Context applicationContext) {
        diskIOExecutor = AppExecutors.getInstance().getDiskIO();
        achievementDao = AppDatabase.getInstance(applicationContext).achievementDao();
    }


    private List<Achievement> achievementList;
    private boolean isFinish;

    @Override
    public List<Achievement> retrieveAchievements() {

        isFinish = false;
        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {
                achievementList = achievementDao.getAllAchievements();
                isFinish = true;
            }
        });

        while (!isFinish) ;

        return achievementList;
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
    public void insertMockAchievements() {
        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                    achievementDao.insertAchievement(new Achievement("achievement #" + i));
                }
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