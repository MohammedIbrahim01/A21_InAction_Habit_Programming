package com.rl.x.a21_inaction.achievements;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.rl.x.a21_inaction.BasePresenter;
import com.rl.x.a21_inaction.achievements.model.Achievement;
import com.rl.x.a21_inaction.achievements.view.AchievementsAdapter;
import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.tasks.view.TasksAdapter;

import java.util.List;

public interface AchievementContract {

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

        void insertAchievementIntoDatabase(String name);

        void insertMockAchievementsIntoDatabase();

        void deleteAchievement(Achievement achievement);

    }

}
