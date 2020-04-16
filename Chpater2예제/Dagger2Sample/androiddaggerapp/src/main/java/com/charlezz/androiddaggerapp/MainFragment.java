package com.charlezz.androiddaggerapp;

import javax.inject.Inject;
import javax.inject.Named;

import android.content.Context;
import android.util.Log;

import dagger.android.support.DaggerFragment;

public class MainFragment extends DaggerFragment {
    @Inject
    @Named("app")
    String appString;
    @Inject
    @Named("activity")
    String activityString;
    @Inject
    @Named("fragment")
    String fragmentString;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("MainFragment", appString);
        Log.e("MainFragment", activityString);
        Log.e("MainFragment", fragmentString);
    }

}