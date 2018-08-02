package com.InAction.X.x21InAction.achievements.communication;

import android.content.Context;

import com.InAction.X.x21InAction.achievements.AchievementContract;
import com.InAction.X.x21InAction.achievements.model.Achievement;
import com.InAction.X.x21InAction.achievements.presenter.AchievementPresenter;


public class Communication implements AchievementContract.Communication {


    private AchievementPresenter presenter;


    public Communication(Context applicationContext) {

        presenter = new AchievementPresenter(applicationContext);
    }


    @Override
    public void deleteAchievement(Achievement achievement) {

        presenter.deleteAchievement(achievement);
    }


    @Override
    public void insertAchievement(Achievement achievement) {

        presenter.insertAchievement(achievement);
    }
}
