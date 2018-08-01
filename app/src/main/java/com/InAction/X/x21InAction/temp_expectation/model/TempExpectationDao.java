package com.InAction.X.x21InAction.temp_expectation.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TempExpectationDao {

    @Query("SELECT * FROM tempExpectations")
    List<TempExpectation> getAllTempExpectations();

    @Query("SELECT * FROM tempExpectations")
    LiveData<List<TempExpectation>> getAllTempExpectationsLive();

    @Insert
    void insertTempExpectation(TempExpectation tempExpectation);

    @Query("DELETE FROM tempExpectations")
    void deleteAllTempExpectations();
}
