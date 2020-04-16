package com.charlezz.dagger2;

import dagger.BindsInstance;
import dagger.Subcomponent;

@Subcomponent(modules = MainActivityModule.class)
@ActivityScope
public interface MainActivityComponent {

    MainFragmentComponent.Factory mainFragmentComponentFactory();

    void inject(MainActivity activity);

    @Subcomponent.Factory
    interface Factory {
        MainActivityComponent create(@BindsInstance MainActivity activity, MainActivityModule module);
    }

}