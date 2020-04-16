package com.charlezz.dagger2;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {

    MainActivityComponent.Factory mainActivityComponentFactory();

    void inject(App app);

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance App app, AppModule appModule);

    }

}