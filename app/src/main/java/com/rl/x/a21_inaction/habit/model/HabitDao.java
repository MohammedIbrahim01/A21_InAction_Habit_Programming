package com.rl.x.a21_inaction.habit.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface HabitDao {

    @Query("SELECT * FROM habits WHERE name = :name")
    Habit getHabit(String name);

    @Query("SELECT * FROM habits")
    Habit getHabit();

    @Query("SELECT * FROM habits")
    List<Habit> getAllHabits();

    @Insert
    void insertHabit(Habit habit);
}
