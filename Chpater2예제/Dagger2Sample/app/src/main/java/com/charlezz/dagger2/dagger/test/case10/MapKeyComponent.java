package com.charlezz.dagger2.dagger.test.case10;

import java.util.Map;

import dagger.Component;

@Component(modules = MapModule.class)
public interface MapKeyComponent {
    Map<Animal, String> getStringsByAnimal();
    Map<Class<? extends Number>, String> getStringsByNumber();
}