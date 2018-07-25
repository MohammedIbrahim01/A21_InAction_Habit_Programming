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

        void insertAchievement(Achievement achievement);

        void insertMockAchievements();

        void deleteAchievement(Achievement achievement);
    }

    interface View {

        AchievementsAdapter getAdapter();

        RecyclerView getRecyclerView();

        void refreshAchievements(List<Achievement> achievementList);

        void setupRecyclerViewWithAdapter();
    }

    interface Presenter extends BasePresenter {

        void setupRecyclerViewWithAdapter();

        void setupAchievementsLive();

        void insertMockAchievementsIntoDatabase();

        void deleteAchievement(Achievement achievement);

    }

}
