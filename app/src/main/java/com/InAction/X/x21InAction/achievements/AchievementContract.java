package com.InAction.X.x21InAction.achievements;

import android.arch.lifecycle.LifecycleOwner;
import android.support.v7.widget.RecyclerView;

import com.InAction.X.x21InAction.achievements.model.Achievement;
import com.InAction.X.x21InAction.achievements.view.AchievementViewModel;
import com.InAction.X.x21InAction.achievements.view.AchievementsAdapter;

import java.util.List;

public interface AchievementContract {

    interface Model {

        void insertAchievement(Achievement achievement);

        void deleteAchievement(Achievement achievement);
    }

    interface View {

        AchievementsAdapter getAdapter();

        RecyclerView getRecyclerView();

        LifecycleOwner getLifeCycleOwner();

        AchievementViewModel getViewModel();

        void setAchievementsLive(List<Achievement> achievementList);
    }

    interface Presenter {

        void setupRecyclerViewWithAdapter();

        void setupAchievementsLive();

        void deleteAchievement(Achievement achievement);

        void insertAchievement(Achievement achievement);
    }

    interface Communication {

        void deleteAchievement(Achievement achievement);

        void insertAchievement(Achievement achievement);
    }
}
