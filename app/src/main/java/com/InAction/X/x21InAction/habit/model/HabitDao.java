package com.InAction.X.x21InAction.habit.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface HabitDao {

    @Query("SELECT * FROM habits")
    Habit getHabit();

    @Insert
    void insertHabit(Habit habit);
}
