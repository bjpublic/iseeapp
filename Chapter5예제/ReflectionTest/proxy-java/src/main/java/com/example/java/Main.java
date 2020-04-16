package com.example.java;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        Foo foo = (Foo) Proxy.newProxyInstance(
                Foo.class.getClassLoader(),
                new Class[]{Foo.class},
                new DebugProxy(new FooImpl())
        );
        foo.bar();

    }
}
