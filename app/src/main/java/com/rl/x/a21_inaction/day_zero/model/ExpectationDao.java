package com.rl.x.a21_inaction.day_zero.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ExpectationDao {

    @Query("SELECT * FROM expectations")
    List<Expectation> getAllExpectations();

    @Query("SELECT * FROM expectations")
    LiveData<List<Expectation>> getAllExpectationsLive();

    @Insert
    void insertExpectation(Expectation expectation);

    @Insert
    void insertAllExpectation(List<Expectation> expectationList);

    @Delete
    void deleteExpectation(Expectation expectation);

}
