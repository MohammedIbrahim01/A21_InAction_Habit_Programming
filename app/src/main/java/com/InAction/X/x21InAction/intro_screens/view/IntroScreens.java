package com.InAction.X.x21InAction.intro_screens.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.InAction.X.x21InAction.R;

import butterknife.ButterKnife;

public class IntroScreens extends Fragment {


    public static final String KEY_PAGE = "key-page";


    private int page;


    public IntroScreens() {
    }

    public static IntroScreens getScreen(int page) {

        Bundle args = new Bundle();
        args.putInt(KEY_PAGE, page);

        IntroScreens fragment = new IntroScreens();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(KEY_PAGE)) {

            page = getArguments().getInt(KEY_PAGE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int layout;

        switch (page) {

            case 0:
                layout = R.layout.intro_0;
                break;
            case 1:
                layout = R.layout.intro_1;
                break;
            case 2:
                layout = R.layout.intro_2;
                break;
            case 3:
                layout = R.layout.intro_3;
                break;

            default:
                layout = -1;
        }


        View view = inflater.inflate(layout, container, false);
        ButterKnife.bind(this, view);


        return view;
    }
}
