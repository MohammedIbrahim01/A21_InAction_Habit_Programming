package com.rl.x.a21_inaction.expectation;

import android.support.v7.widget.RecyclerView;

import com.rl.x.a21_inaction.BasePresenter;
import com.rl.x.a21_inaction.expectation.model.Expectation;
import com.rl.x.a21_inaction.expectation.view.ExpectationAdapter;

import java.util.List;

public interface ExpectationContract {

    interface Model{

        void insertExpectation(Expectation expectation);

        void deleteExpectation(Expectation expectation);

        void insertExpectationList(List<Expectation> expectationListFromHabit);
    }

    interface View {

        void setupRecyclerViewWithAdapter();

        void setExpectations(List<Expectation> expectationList);
    }

    interface Presenter extends BasePresenter {

        void setupRecyclerViewWithAdapter();

        void setupExpectationLive();

        void deleteExpectation(Expectation expectation);
    }
}
