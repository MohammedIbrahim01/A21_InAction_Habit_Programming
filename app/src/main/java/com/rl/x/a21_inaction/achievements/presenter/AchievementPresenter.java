package com.rl.x.a21_inaction.achievements.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.rl.x.a21_inaction.achievements.AchievementContract;
import com.rl.x.a21_inaction.achievements.model.Achievement;
import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.utils.AppExecutors;

import java.util.List;

public class AchievementPresenter implements AchievementContract.Presenter {

    private Context applicationContext;
    private AchievementContract.View view;

    public AchievementPresenter(Context applicationContext, AchievementContract.View view) {
        this.applicationContext = applicationContext;
        this.view = view;
    }

    @Override
    public void setupRecyclerViewWithAdapter() {
        view.setupRecyclerViewWithAdapter();
    }

    @Override
    public void retrieveAndDisplayAchievements() {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                //retrieve achievements
                List<Achievement> achievementList = AppDatabase.getInstance(applicationContext).achievementDao().getAllAchievements();

                //display achievements
                view.displayAchievements(achievementList);
            }
        });
    }

    @Override
    public void insertAchievementIntoDatabase(String name) {
        final Achievement newAchievement = new Achievement(name);

        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(applicationContext).achievementDao().insertAchievement(newAchievement);
            }
        });
    }

    @Override
    public void insertMockAchievementsIntoDatabase() {

        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(applicationContext).achievementDao().insertAchievement(new Achievement("achievement #1"));
                AppDatabase.getInstance(applicationContext).achievementDao().insertAchievement(new Achievement("achievement #2"));
                AppDatabase.getInstance(applicationContext).achievementDao().insertAchievement(new Achievement("achievement #3"));
                AppDatabase.getInstance(applicationContext).achievementDao().insertAchievement(new Achievement("achievement #4"));
            }
        });

    }

    @Override
    public void deleteAchievement(final Achievement achievement) {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(applicationContext).achievementDao().deleteAchievement(achievement);
            }
        });
    }


    @Override
    public void start() {
        setupRecyclerViewWithAdapter();
        retrieveAndDisplayAchievements();
    }
}
