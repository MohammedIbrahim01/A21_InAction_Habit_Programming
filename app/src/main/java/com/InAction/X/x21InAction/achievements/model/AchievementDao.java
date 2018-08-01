package com.InAction.X.x21InAction.achievements.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AchievementDao {

    @Query("SELECT * FROM achievements")
    LiveData<List<Achievement>> getAllAchievementsLive();

    @Insert
    void insertAchievement(Achievement achievement);

    @Delete
    void deleteAchievement(Achievement achievement);

}
