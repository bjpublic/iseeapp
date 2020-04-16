package com.charlezz.androiddaggerapp;

import javax.inject.Named;
import javax.inject.Singleton;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppModule {

    @Provides
    @Singleton
    static SharedPreferences provideSharedPreferences(App app) {
        return app.getSharedPreferences("default", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    @Named("app")
    static String provideAppString(){
        return "String from AppModule";
    }

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    @ActivityScope
    abstract MainActivity bindsMainActivity();

}