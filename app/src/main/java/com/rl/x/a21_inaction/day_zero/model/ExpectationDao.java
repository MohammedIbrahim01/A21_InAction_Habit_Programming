package com.rl.x.a21_inaction.day_zero.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ExpectationDao {

    @Query("SELECT * FROM expectations")
    List<Expectation> getAllExpectations();

    @Insert
    void insertExpectation(Expectation expectation);

    @Delete
    void deleteExpectation(Expectation expectation);

}
