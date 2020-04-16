package com.charlezz.androiddaggerapp;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class App extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.factory().create(this);
    }

}