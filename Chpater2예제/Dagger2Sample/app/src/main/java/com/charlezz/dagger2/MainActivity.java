package com.charlezz.dagger2;

import javax.inject.Inject;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    String activityName;

    MainActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        component = ((App) getApplication()).getAppComponent().mainActivityComponentFactory()
                .create(this, new MainActivityModule());
        component.inject(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MainFragment())
                .commit();

    }

    public MainActivityComponent getComponent() {
        return component;
    }
}
