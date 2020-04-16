package com.charlezz.dagger2;

import dagger.BindsInstance;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = MainFragmentModule.class)
public interface MainFragmentComponent {

    void inject(MainFragment fragment);

    @Subcomponent.Factory
    interface Factory {
        MainFragmentComponent create(@BindsInstance MainFragment fragment, MainFragmentModule module);
    }
}
