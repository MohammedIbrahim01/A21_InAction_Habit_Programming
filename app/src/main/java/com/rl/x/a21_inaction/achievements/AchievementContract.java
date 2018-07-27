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

        void deleteAchievement(Achievement achievement);
    }

    interface View {

        void setAchievements(List<Achievement> achievementList);

        void attachRecyclerViewWithAdapter();
    }

    interface Presenter extends BasePresenter {

        void attachRecyclerViewWithAdapter();

        void setupAchievementsLive();

        void deleteAchievement(Achievement achievement);
    }

}
