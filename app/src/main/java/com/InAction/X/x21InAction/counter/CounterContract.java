package com.InAction.X.x21InAction.counter;

import com.InAction.X.x21InAction.manager.AppManager;

public interface CounterContract {

    interface Model {

        int getCount();

        void increaseCount();

        void resetCounter();
    }

    interface Presenter {

        void startCountingIfMidnight();

        void notifyCountingStart();
    }

    interface Communication {

        int getCount();

        void resetCounter();

        void startCountingIfMidnight();

        void stopCounter();
    }
}
