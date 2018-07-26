package com.rl.x.a21_inaction.day_zero;

import android.support.v7.widget.RecyclerView;

import com.rl.x.a21_inaction.BasePresenter;
import com.rl.x.a21_inaction.day_zero.model.Expectation;
import com.rl.x.a21_inaction.add_expectation.model.TempExpectation;
import com.rl.x.a21_inaction.day_zero.view.ExpectationAdapter;

import java.util.List;

public interface ExpectationContract {

    interface Model{

        void insertExpectation(Expectation expectation);

        void insertMockExpectation();

        void deleteExpectation(Expectation expectation);
    }

    interface View {

        void setupRecyclerViewWithAdapter();

        void refreshExpectations(List<Expectation> expectationList);

        ExpectationAdapter getAdapter();

        RecyclerView getRecyclerView();

    }

    interface Presenter extends BasePresenter {

        void setupRecyclerViewWithAdapter();

        void setupExpectationLive();

        void insertExpectation(String name);

        void insertMockExpectations();

        void deleteExpectation(Expectation expectation);
    }
}
