package com.InAction.X.x21InAction.habit.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction._main.view.MainActivity;
import com.InAction.X.x21InAction.habit.HabitContract;
import com.InAction.X.x21InAction.habit.model.Habit;
import com.InAction.X.x21InAction.habit.presenter.HabitPresenter;
import com.InAction.X.x21InAction.manager.AppManager;
import com.InAction.X.x21InAction.utils.CustomViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateHabitActivity extends AppCompatActivity implements View.OnClickListener, HabitContract.View {


    private HabitPresenter presenter;
    private CreateHabitAdapter adapter;
    public String habitName;


    @BindView(R.id.create_habit_back)
    TextView back;
    @BindView(R.id.create_habit_next)
    TextView next;
    @BindView(R.id.create_habit_start)
    TextView start;
    @BindView(R.id.create_habit_finish)
    TextView finish;
    @BindView(R.id.create_habit_viewPager)
    CustomViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_habit);
        ButterKnife.bind(this);


        presenter = new HabitPresenter(getApplicationContext(), this);
        adapter = new CreateHabitAdapter(getSupportFragmentManager());


        presenter.setupAdapterWithViewPager();
    }


    @Override
    protected void onResume() {
        super.onResume();

        back.setOnClickListener(this);
        next.setOnClickListener(this);
        start.setOnClickListener(this);
        finish.setOnClickListener(this);

        setButtonsVisibilities(viewPager.getCurrentItem());
    }


    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.create_habit_next:
                // if page 1 get habit name before next
                if (viewPager.getCurrentItem() == 1)
                    habitName = ((HabitNameFragment) adapter.getItem(1)).habitNameEditText.getText().toString();
                presenter.nextPage();
                break;

            case R.id.create_habit_back:
                presenter.previousPage();
                break;

            case R.id.create_habit_start:
                AppManager.setFirstLaunch(getApplicationContext(), false);
                AppManager.startHabitPrograming(getApplicationContext(), habitName);
                presenter.nextPage();
                break;

            case R.id.create_habit_finish:
                startActivity(new Intent(CreateHabitActivity.this, MainActivity.class));
                Toast.makeText(CreateHabitActivity.this, "Tasks Will Start in next midnight", Toast.LENGTH_LONG).show();
                finish();
                break;
        }
    }


    @Override
    public void setButtonsVisibilities(int page) {


        switch (page) {

            case 0:
                back.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);
                start.setVisibility(View.GONE);
                finish.setVisibility(View.GONE);
                break;

            case 6:
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
                start.setVisibility(View.VISIBLE);
                finish.setVisibility(View.GONE);
                break;

            case 10:
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
                start.setVisibility(View.GONE);
                finish.setVisibility(View.VISIBLE);
                break;

            default:
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                start.setVisibility(View.GONE);
                finish.setVisibility(View.GONE);

        }
    }


    @Override
    public CreateHabitAdapter getAdapter() {

        return adapter;
    }


    @Override
    public CustomViewPager getViewPager() {

        return viewPager;
    }

    @Override
    public int getViewPagerCurrentItem() {

        return viewPager.getCurrentItem();
    }

    @Override
    public void setViewPagerCurrentItem(int page) {

        viewPager.setCurrentItem(page);
    }
}
