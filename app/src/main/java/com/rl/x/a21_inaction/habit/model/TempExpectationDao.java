package com.rl.x.a21_inaction.habit.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.rl.x.a21_inaction.day_zero.model.Expectation;
import com.rl.x.a21_inaction.tasks.model.Task;

import java.util.List;

@Dao
public interface TempExpectationDao {

    @Query("SELECT * FROM tempExpectations")
    List<TempExpectation> getAllTempExpectations();

    @Insert
    void insertTempExpectation(TempExpectation tempExpectation);

    @Query("DELETE FROM tempExpectations")
    void deleteAllTempExpectations();
}
