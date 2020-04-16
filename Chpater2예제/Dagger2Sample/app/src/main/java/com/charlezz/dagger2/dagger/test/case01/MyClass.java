package com.charlezz.dagger2.dagger.test.case01;

import javax.inject.Inject;
import javax.inject.Named;

public class MyClass {

    @Inject
    String str;

    @Inject
    @Named("hello")
    String strHello;

    @Inject
    @Named("world")
    String strWorld;

    @Inject
    @Hello
    String strHelloWithQualifier;

    public String getStr() {
        return str;
    }

    public String getStrHello() {
        return strHello;
    }

    public String getStrWorld() {
        return strWorld;
    }

    public String getStrHelloWithQualifier() {
        return strHelloWithQualifier;
    }
}