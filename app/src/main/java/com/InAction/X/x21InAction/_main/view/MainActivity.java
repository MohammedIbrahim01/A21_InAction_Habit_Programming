package com.InAction.X.x21InAction._main.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.InAction.X.x21InAction.AppCP;
import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction._main.MainContract;
import com.InAction.X.x21InAction._main.preseter.MainPresenter;
import com.InAction.X.x21InAction.intro_screens.view.IntroScreensActivity;
import com.InAction.X.x21InAction.manager.AppManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View, SharedPreferences.OnSharedPreferenceChangeListener {


    public static final String NAME_SHARED_PREFERENCES = AppCP.NAME_SHARED_PREFERENCES;
    public static final String KEY_FIRST_LAUNCH = AppCP.KEY_FIRST_LAUNCH;
    public static final String KEY_COUNT = AppCP.KEY_COUNT;
    public static final String AD_MOB_App_ID = AppCP.AD_MOB_App_ID;


    private MainPresenter presenter;
    private SharedPreferences sharedPreferences;


    @BindView(R.id.counter_textView)
    TextView counterTextView;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.main_activity_habit_name_textView)
    TextView habitNameTextView;
    @BindView(R.id.adView)
    AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //***Ads
        MobileAds.initialize(this, AD_MOB_App_ID);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        sharedPreferences = getSharedPreferences(NAME_SHARED_PREFERENCES, MODE_PRIVATE);


        if (sharedPreferences.getBoolean(KEY_FIRST_LAUNCH, true)) {

            //start intro screens
            startActivity(new Intent(MainActivity.this, IntroScreensActivity.class));
            finish();
        }

        if (AppManager.getHabitName(this) != null) {

            habitNameTextView.setText(AppManager.getHabitName(this));
        }


        presenter = new MainPresenter(this, this);


        presenter.setupTabLayoutAndViewPager();


        presenter.setCounter();
    }


    @Override
    protected void onResume() {
        super.onResume();

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * update the counter if count value in SharedPreferences was changed
     *
     * @param sharedPreferences
     * @param s
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        if (s == KEY_COUNT) {

            counterTextView.setText(String.valueOf(sharedPreferences.getInt(KEY_COUNT, 0)));
        }
    }


    @Override
    public void setupTabLayoutAndViewPager(AppFragmentPagerAdapter fragmentPagerAdapter) {

        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void displayCounter(String count) {

        counterTextView.setText(count);
    }


    @Override
    public FragmentManager getAppSupportFragmentManager() {
        return getSupportFragmentManager();
    }
}
