package com.charlezz.dagger2.dagger.test.cafe;

import dagger.Module;
import dagger.Provides;

@Module
public class MachineModule {

    @Provides
    CoffeeBean provideCoffeeBean() {
        return new CoffeeBean();
    }

}