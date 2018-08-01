package com.InAction.X.x21InAction.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor mainThreadExecutor;

    private static final Object LOCK = new Object();
    private static AppExecutors sInstance;

    public AppExecutors(Executor diskIO, Executor networkIO, Executor mainThreadExecutor) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThreadExecutor = mainThreadExecutor;
    }

    public static AppExecutors getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new AppExecutors(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MainThreadExecutor());
            }
        }
        return sInstance;
    }

    public Executor getDiskIO() {
        return diskIO;
    }

    public Executor getNetworkIO() {
        return networkIO;
    }

    public Executor getMainThreadExecutor() {
        return mainThreadExecutor;
    }

    private static class MainThreadExecutor implements Executor {
        Handler mainHandler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(@NonNull Runnable runnable) {
            mainHandler.post(runnable);
        }
    }
}
