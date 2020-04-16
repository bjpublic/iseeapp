package com.charlezz.dagger2.dagger.test.case12;

import java.util.Set;

import dagger.Component;

@Component(modules = MultibindsModules.class)
public interface MultibindsComponent {
    Set<String> getStrings();
}