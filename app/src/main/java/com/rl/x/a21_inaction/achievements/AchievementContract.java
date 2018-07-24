package com.rl.x.a21_inaction.achievements;

import android.arch.lifecycle.LiveData;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.rl.x.a21_inaction.BasePresenter;
import com.rl.x.a21_inaction.achievements.model.Achievement;
import com.rl.x.a21_inaction.achievements.view.AchievementsAdapter;
import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.tasks.view.TasksAdapter;

import java.util.List;

public interface AchievementContract {

    interface Model {

        List<Achievement> retrieveAchievements();

        LiveData<List<Achievement>> retrieveAchievementsLive();

        void insertAchievement(Achievement achievement);

        void insertMockAchievements();

        void deleteAchievement(Achievement achievement);
    }

    interface View {

        void displayAchievements(List<Achievement> achievementList);

        AchievementsAdapter getAdapter();

        RecyclerView getRecyclerView();

        void refreshAchievementRecyclerView(List<Achievement> achievementList);

        void setupRecyclerViewWithAdapter();
    }

    interface Presenter extends BasePresenter {

        void setupRecyclerViewWithAdapter();

        void retrieveAndDisplayAchievements();

        void retrieveAchievementsLive();

        void insertAchievementIntoDatabase(String name, int day);

        void insertMockAchievementsIntoDatabase();

        void deleteAchievement(Achievement achievement);

    }

}
