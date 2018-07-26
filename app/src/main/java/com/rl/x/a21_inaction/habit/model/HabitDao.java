package com.rl.x.a21_inaction.habit.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.rl.x.a21_inaction.day_zero.model.Expectation;
import com.rl.x.a21_inaction.tasks.model.Task;

import java.util.List;

@Dao
public interface HabitDao {

    @Query("SELECT * FROM habits WHERE name = :name")
    Habit getHabit(String name);

    @Insert
    void insertHabit(Habit habit);
}
