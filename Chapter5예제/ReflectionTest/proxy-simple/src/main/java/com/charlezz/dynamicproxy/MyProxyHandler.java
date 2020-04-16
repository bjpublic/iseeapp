package com.charlezz.dynamicproxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxyHandler implements InvocationHandler {
    public static final String TAG = MyProxyHandler.class.getSimpleName();
    private Object target;
    public MyProxyHandler(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Log.e(TAG,"메소드 invoke 하기 전");
        int result = (int) method.invoke(target, objects);
        Log.e(TAG,"메소드 invoke 한 후");
        return result;
    }
}
