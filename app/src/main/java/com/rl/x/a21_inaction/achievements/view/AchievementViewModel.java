package com.rl.x.a21_inaction.achievements.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.rl.x.a21_inaction.achievements.model.Achievement;
import com.rl.x.a21_inaction.database.AppDatabase;

import java.util.List;

public class AchievementViewModel extends AndroidViewModel {

    private LiveData<List<Achievement>> achievementList;


    public AchievementViewModel(@NonNull final Application application) {
        super(application);

        achievementList = AppDatabase.getInstance(application.getApplicationContext()).achievementDao().getAllAchievementsLive();
    }


    public LiveData<List<Achievement>> getAchievementList() {
        return achievementList;
    }
}
