package com.charlezz.dagger2.dagger.test.case06;

import dagger.BindsOptionalOf;
import dagger.Module;

@Module
public abstract class CommonModule {
    @BindsOptionalOf
    abstract String bindsOptionalOfString();
}