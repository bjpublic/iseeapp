package com.charlezz.dagger2.dagger.test.case12;

import java.util.Set;

import dagger.Module;
import dagger.multibindings.Multibinds;

@Module
public abstract class MultibindsModules {

    @Multibinds
    abstract Set<String> strings();

}