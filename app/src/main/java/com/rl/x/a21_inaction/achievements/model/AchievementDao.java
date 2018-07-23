package com.rl.x.a21_inaction.achievements.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AchievementDao {

    @Query("SELECT * FROM achievements")
    List<Achievement> getAllAchievements();

    @Insert
    void insertAchievement(Achievement achievement);

    @Delete
    void deleteAchievement(Achievement achievement);

}
