package com.InAction.X.x21InAction.counter;

public interface CounterContract {

    interface Model {

        int getCount();

        void increaseCount();

        void resetCounter();
    }

    interface Presenter {

        void startCountingIfMidnight();

        void notifyCountingStart();

        void increaseCount();

        int getCount();

        void resetCounter();
    }

    interface Communication {

        int getCount();

        void resetCounter();
    }
}
