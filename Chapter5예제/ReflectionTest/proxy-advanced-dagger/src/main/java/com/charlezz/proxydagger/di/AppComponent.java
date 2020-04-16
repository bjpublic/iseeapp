package com.charlezz.proxydagger.di;

import com.charlezz.proxydagger.App;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ActivityModules.class})
public interface AppComponent extends AndroidInjector<App>{
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App>{}
}
