package com.InAction.X.x21InAction._main.preseter;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.InAction.X.x21InAction._main.MainContract;
import com.InAction.X.x21InAction._main.model.MainModel;
import com.InAction.X.x21InAction._main.view.AppFragmentPagerAdapter;
import com.InAction.X.x21InAction.manager.AppManager;

public class MainPresenter implements MainContract.Presenter {

    private FragmentActivity fragmentActivity;
    private MainModel model;
    private MainContract.View view;


    public MainPresenter(FragmentActivity fragmentActivity, MainContract.View view) {

        this.fragmentActivity = fragmentActivity;
        this.model = new MainModel();
        this.view = view;
    }


    /**
     * getScreen fragmentPagerAdapter from model then pass it to view to setupTabLayoutAndViewPager
     */
    @Override
    public void setupTabLayoutAndViewPager() {

        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        AppFragmentPagerAdapter fragmentPagerAdapter = model.getFragmentPagerAdapter(supportFragmentManager);
        view.setupTabLayoutAndViewPager(fragmentPagerAdapter);
    }


    /**
     * manager responsibility
     */
    @Override
    public void clearDatabase() {

        AppManager.clearDatabase(fragmentActivity.getApplicationContext());
    }


    /**
     * getScreen count from manager then display it
     */
    @Override
    public void setCounter() {

        view.displayCounter(AppManager.getCount(fragmentActivity.getApplicationContext()));
    }
}
