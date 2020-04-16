package com.charlezz.dagger2;

import javax.inject.Named;
import javax.inject.Singleton;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = MainActivityComponent.class)
public class AppModule {

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(App app) {
        return app.getSharedPreferences("default", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    @Named("app")
    String provideAppString(){
        return "String from AppModule";
    }

}