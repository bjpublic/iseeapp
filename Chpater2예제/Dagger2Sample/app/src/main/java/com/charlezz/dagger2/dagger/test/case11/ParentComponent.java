package com.charlezz.dagger2.dagger.test.case11;

import java.util.Set;

import dagger.Component;

@Component(modules = ParentModule.class)
public interface ParentComponent {
    Set<String> strings();

    ChildComponent.Builder childCompBuilder();
}