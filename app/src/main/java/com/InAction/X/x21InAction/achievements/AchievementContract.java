package com.InAction.X.x21InAction.achievements;

import android.arch.lifecycle.LiveData;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.InAction.X.x21InAction.BasePresenter;
import com.InAction.X.x21InAction.achievements.model.Achievement;
import com.InAction.X.x21InAction.achievements.view.AchievementsAdapter;
import com.InAction.X.x21InAction.tasks.model.Task;
import com.InAction.X.x21InAction.tasks.view.TasksAdapter;

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
