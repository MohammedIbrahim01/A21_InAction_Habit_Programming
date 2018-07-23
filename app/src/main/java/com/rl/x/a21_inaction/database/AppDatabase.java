package com.rl.x.a21_inaction.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.tasks.model.TaskDao;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    public static final String NAME_DATABASE = "name-database";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context applicationContext) {
        if (sInstance == null) {
            if (sInstance == null) {
                sInstance = Room.databaseBuilder(applicationContext, AppDatabase.class, NAME_DATABASE)
                        .build();
            }
        }
        return sInstance;
    }

    public abstract TaskDao taskDao();
}
