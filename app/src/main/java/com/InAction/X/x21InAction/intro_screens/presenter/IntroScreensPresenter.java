package com.InAction.X.x21InAction.intro_screens.presenter;

import com.InAction.X.x21InAction.intro_screens.IntroScreensContract;
import com.InAction.X.x21InAction.intro_screens.view.IntoScreensAdapter;
import com.InAction.X.x21InAction.intro_screens.view.IntroScreens;
import com.InAction.X.x21InAction.utils.CustomViewPager;

public class IntroScreensPresenter implements IntroScreensContract.Presenter {


    private IntroScreensContract.View view;


    public IntroScreensPresenter(IntroScreensContract.View view) {

        this.view = view;
    }


    @Override
    public void setupAdapterWithViewPager() {


        IntoScreensAdapter adapter = view.getAdapter();
        CustomViewPager viewPager = view.getViewPager();


        adapter.addFragment(IntroScreens.getScreen(0));
        adapter.addFragment(IntroScreens.getScreen(1));
        adapter.addFragment(IntroScreens.getScreen(2));
        adapter.addFragment(IntroScreens.getScreen(3));

        viewPager.setAdapter(adapter);
    }


    @Override
    public void nextPage() {

        int page = view.getViewPagerCurrentItem();

        view.getViewPager().setCurrentItem(++page);
        view.setButtonsVisibilities(page);
    }

    @Override
    public void previousPage() {

        int page = view.getViewPagerCurrentItem();

        view.getViewPager().setCurrentItem(--page);
        view.setButtonsVisibilities(page);
    }
}
