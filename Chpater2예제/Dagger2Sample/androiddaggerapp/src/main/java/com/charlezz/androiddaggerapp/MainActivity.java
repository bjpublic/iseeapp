package com.charlezz.androiddaggerapp;

import javax.inject.Inject;

import android.content.SharedPreferences;
import android.os.Bundle;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    String activityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MainFragment())
                .commit();

    }
}
