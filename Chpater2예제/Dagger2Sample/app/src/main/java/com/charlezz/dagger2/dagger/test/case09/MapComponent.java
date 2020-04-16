package com.charlezz.dagger2.dagger.test.case09;

import java.util.Map;

import dagger.Component;

@Component(modules = MapModule.class)
public interface MapComponent {
    Map<String, Long> getLongsByString();

    Map<Class<?>, String> getStringsByClass();

}