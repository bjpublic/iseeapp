package com.example.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DebugProxy implements InvocationHandler {

    private final Object target;

    public DebugProxy(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable{
        Object result;
        System.out.println(m.getName() + " 메서드 호출 전");
        result = m.invoke(target, args);
        System.out.println(m.getName() + " 메서드 호출 후");
        return result;
    }
}