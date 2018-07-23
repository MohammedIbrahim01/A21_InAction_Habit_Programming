package com.rl.x.a21_inaction.achievements.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.rl.x.a21_inaction.achievements.AchievementContract;
import com.rl.x.a21_inaction.achievements.model.Achievement;
import com.rl.x.a21_inaction.achievements.model.AchievementDao;
import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.day_zero.model.ExpectationDao;
import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.utils.AppExecutors;

import java.util.List;
import java.util.concurrent.Executor;

public class AchievementPresenter implements AchievementContract.Presenter {

    private Context applicationContext;
    private AchievementContract.View view;

    private Executor diskIO;
    private AchievementDao achievementDao;

    public AchievementPresenter(Context applicationContext, AchievementContract.View view) {
        this.applicationContext = applicationContext;
        this.view = view;

        diskIO = AppExecutors.getInstance().getDiskIO();
        achievementDao = AppDatabase.getInstance(applicationContext).achievementDao();
    }

    @Override
    public void setupRecyclerViewWithAdapter() {
        view.setupRecyclerViewWithAdapter();
    }

    @Override
    public void retrieveAndDisplayAchievements() {
        diskIO.execute(new Runnable() {
            @Override
            public void run() {
                //retrieve achievements
                List<Achievement> achievementList = achievementDao.getAllAchievements();

                //display achievements
                view.displayAchievements(achievementList);
            }
        });
    }

    @Override
    public void insertAchievementIntoDatabase(String name) {
        final Achievement newAchievement = new Achievement(name);

        diskIO.execute(new Runnable() {
            @Override
            public void run() {
                achievementDao.insertAchievement(newAchievement);
            }
        });
    }

    @Override
    public void insertMockAchievementsIntoDatabase() {

        diskIO.execute(new Runnable() {
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
        diskIO.execute(new Runnable() {
            @Override
            public void run() {
                achievementDao.deleteAchievement(achievement);
            }
        });
    }


    @Override
    public void start() {
        setupRecyclerViewWithAdapter();
        retrieveAndDisplayAchievements();
    }
}
