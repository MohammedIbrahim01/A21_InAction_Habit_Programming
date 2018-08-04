package com.InAction.X.x21InAction.intro_screens.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.InAction.X.x21InAction.AppCP;
import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction.intro_screens.IntroScreensContract;
import com.InAction.X.x21InAction.intro_screens.presenter.IntroScreensPresenter;
import com.InAction.X.x21InAction.manager.AppManager;
import com.InAction.X.x21InAction.utils.CustomViewPager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroScreensActivity extends AppCompatActivity implements View.OnClickListener, IntroScreensContract.View {

    public static final String AD_MOB_App_ID = AppCP.AD_MOB_App_ID;


    private IntroScreensPresenter presenter;
    private IntoScreensAdapter adapter;


    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.start)
    TextView start;
    @BindView(R.id.intro_screens_viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.into_adView)
    AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);

        //***Ads
        MobileAds.initialize(this, AD_MOB_App_ID);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        adapter = new IntoScreensAdapter(getSupportFragmentManager());
        presenter = new IntroScreensPresenter(this);

        presenter.setupAdapterWithViewPager();
    }


    @Override
    protected void onResume() {
        super.onResume();

        back.setOnClickListener(this);
        next.setOnClickListener(this);
        start.setOnClickListener(this);

        setButtonsVisibilities(viewPager.getCurrentItem());
    }


    @Override
    public void onClick(View view) {

        int page = viewPager.getCurrentItem();

        switch (view.getId()) {

            case R.id.next:
                presenter.nextPage();
                break;

            case R.id.back:
                presenter.previousPage();
                viewPager.setCurrentItem((--page), true);
                setButtonsVisibilities(page);
                break;

            case R.id.start:
                AppManager.goCreateHabit(this);
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
                break;

            case 4:
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
                start.setVisibility(View.VISIBLE);
                break;

            default:
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                start.setVisibility(View.GONE);
        }
    }


    @Override
    public IntoScreensAdapter getAdapter() {

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
}