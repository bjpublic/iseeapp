package com.charlezz.proxydagger.di;

import com.charlezz.proxydagger.UserActivity;
import com.charlezz.proxydagger.UserModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModules {
    @ActivityScope
    @ContributesAndroidInjector(modules = UserModule.class)
    abstract UserActivity mainActivity();
}
