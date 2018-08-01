package com.InAction.X.x21InAction.temp_task.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TempTaskDao {


    @Query("SELECT * FROM tempTasks")
    List<TempTask> getAllTempTasks();

    @Query("SELECT * FROM tempTasks")
    LiveData<List<TempTask>> getAllTempTasksLive();

    @Insert
    void insertTempTask(TempTask tempTask);

    @Query("DELETE FROM tempTasks")
    void deleteAllTempTasks();
}
