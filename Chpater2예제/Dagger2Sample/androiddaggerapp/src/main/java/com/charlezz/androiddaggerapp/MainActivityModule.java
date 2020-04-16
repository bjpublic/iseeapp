package com.charlezz.androiddaggerapp;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @Provides
    @ActivityScope
    @Named("activity")
    static String provideActivityString(){
        return "String from MainActivityModule";
    }

    @Provides
    @ActivityScope
    static String provideActivityName() {
        return MainActivity.class.getSimpleName();
    }

    @ContributesAndroidInjector(modules = MainFragmentModule.class)
    @FragmentScope
    abstract MainFragment bindsMainFragment();


}