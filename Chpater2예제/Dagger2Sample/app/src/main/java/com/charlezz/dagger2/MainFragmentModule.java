package com.charlezz.dagger2;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module

public class MainFragmentModule {

    @Named("fragment")
    @Provides
    @FragmentScope
    String provideString() {
        return "String from fragment";
    }

}