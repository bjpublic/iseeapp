package com.charlezz.dagger2.dagger.test.case13;

import dagger.Component;

@Component(modules = ModuleA.class)
public interface ComponentA {
    String provideString();//프로비전 메서드

}