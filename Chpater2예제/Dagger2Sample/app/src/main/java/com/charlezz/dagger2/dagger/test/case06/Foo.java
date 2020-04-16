package com.charlezz.dagger2.dagger.test.case06;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Lazy;

public class Foo {

    @Inject
    public Optional<String> str; // @Nullable 바인딩은 허용하지 않음

    @Inject
    public Optional<Provider<String>> str2;

    @Inject
    public Optional<Lazy<String>> str3;

}