package com.InAction.X.x21InAction.achievements.view;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction.achievements.AchievementContract;
import com.InAction.X.x21InAction.achievements.model.Achievement;
import com.InAction.X.x21InAction.achievements.presenter.AchievementPresenter;
import com.InAction.X.x21InAction.expectation.view.ExpectationViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AchievementsFragment extends Fragment implements AchievementContract.View {


    private AchievementsAdapter adapter = new AchievementsAdapter();
    private AchievementPresenter presenter;
    private AchievementViewModel viewModel;


    @BindView(R.id.achievements_recyclerView)
    RecyclerView achievementsRecyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achievements, container, false);
        ButterKnife.bind(this, view);

        presenter = new AchievementPresenter(getContext(), this);
        viewModel = ViewModelProviders.of(getActivity()).get(AchievementViewModel.class);


        presenter.setupRecyclerViewWithAdapter();
        presenter.setupAchievementsLive();


        return view;
    }


    @Override
    public AchievementsAdapter getAdapter() {

        return adapter;
    }


    @Override
    public RecyclerView getRecyclerView() {

        return achievementsRecyclerView;
    }


    @Override
    public LifecycleOwner getLifeCycleOwner() {

        return getActivity();
    }


    @Override
    public AchievementViewModel getViewModel() {

        return viewModel;
    }


    @Override
    public void setAchievementsLive(List<Achievement> achievementList) {

        adapter.setAchievementList(achievementList);
        adapter.notifyDataSetChanged();
    }
}
