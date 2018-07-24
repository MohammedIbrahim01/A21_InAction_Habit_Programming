package com.rl.x.a21_inaction.manager;

import android.content.Context;

import com.rl.x.a21_inaction.achievements.model.Achievement;
import com.rl.x.a21_inaction.achievements.model.AchievementModel;
import com.rl.x.a21_inaction.day_zero.model.ExpectationModel;
import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.tasks.model.TaskModel;

public class AppManager {

    private TaskModel taskModel;
    private AchievementModel achievementModel;
    private ExpectationModel expectationModel;


    public AppManager(Context applicationContext) {

        taskModel = new TaskModel(applicationContext);
        achievementModel = new AchievementModel(applicationContext);
        expectationModel = new ExpectationModel(applicationContext);
    }

    public void addAchievement(Task swipedTask) {
        achievementModel.insertAchievement(new Achievement(swipedTask.getName(), swipedTask.getDay()));
    }
}
