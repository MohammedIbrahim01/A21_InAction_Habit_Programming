package com.rl.x.a21_inaction.add_task.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TempTaskDao {

    @Query("SELECT * FROM tempTasks")
    List<TempTask> getAllTempTasks();

    @Insert
    void insertTempTask(TempTask tempTask);

    @Query("DELETE FROM tempTasks")
    void deleteAllTempTasks();
}
