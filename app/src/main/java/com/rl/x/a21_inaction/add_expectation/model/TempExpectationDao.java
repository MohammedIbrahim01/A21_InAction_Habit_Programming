package com.rl.x.a21_inaction.add_expectation.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.rl.x.a21_inaction.add_expectation.model.TempExpectation;

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
