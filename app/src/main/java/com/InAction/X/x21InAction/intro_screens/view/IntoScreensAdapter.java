package com.InAction.X.x21InAction.intro_screens.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class IntoScreensAdapter extends FragmentPagerAdapter {


    List<Fragment> fragmentList = new ArrayList<>();


    public IntoScreensAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }


    @Override
    public int getCount() {
        return fragmentList.size();
    }


    public void addFragment(Fragment fragment) {

        fragmentList.add(fragment);
    }
}
