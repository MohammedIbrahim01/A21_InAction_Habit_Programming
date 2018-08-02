package com.InAction.X.x21InAction.counter;

import com.InAction.X.x21InAction.manager.AppManager;

public interface CounterContract {

    interface Model {

        int getCount();

        void increaseCount();

        void resetCounter();
    }

    interface Presenter {

        void startCountingIfMidnight(AppManager manager);

        void notifyCountingStart(AppManager manager);

        void increaseCount();

        int getCount();

        void resetCounter();
    }

    interface Communication {

        int getCount();

        void resetCounter();

        void startCountingIfMidnight(AppManager manager);
    }
}
