package com.charlezz.dagger2;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = MainFragmentComponent.class)
public class MainActivityModule {


    @Provides
    @ActivityScope
    @Named("activity")
    String provideActivityString(){
        return "String from MainActivityModule";
    }

    @Provides
    @ActivityScope
    String provideActivityName() {
        return MainActivity.class.getSimpleName();
    }

}