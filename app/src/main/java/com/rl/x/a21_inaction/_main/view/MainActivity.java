package com.rl.x.a21_inaction._main.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.rl.x.a21_inaction.R;
import com.rl.x.a21_inaction._main.MainContract;
import com.rl.x.a21_inaction._main.preseter.MainPresenter;
import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.habit.view.NewHabitActivity;
import com.rl.x.a21_inaction.utils.AppExecutors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View, SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String NAME_SHARED_PREFERENCES = "name-sharedPreferences";
    public static final String KEY_COUNT = "key-count";

    private MainPresenter presenter;
    private SharedPreferences sharedPreferences;

    @BindView(R.id.counter_textView)
    TextView counterTextView;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(NAME_SHARED_PREFERENCES, MODE_PRIVATE);

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

        switch (item.getItemId()) {

            case R.id.action_add_habit:
                presenter.clearDatabase();
                presenter.goAddHabit();
                break;
            case R.id.action_stop_Time:
                presenter.stopTime();
                break;
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
