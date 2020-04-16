package com.charlezz.androiddaggerapp;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Component(modules = {AppModule.class, AndroidInjectionModule.class})
@Singleton
public interface AppComponent extends AndroidInjector<App> {
    @Component.Factory
    interface Factory extends AndroidInjector.Factory<App>{ }

}