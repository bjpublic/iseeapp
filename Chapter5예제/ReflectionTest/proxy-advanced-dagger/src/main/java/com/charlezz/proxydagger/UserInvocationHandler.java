package com.charlezz.proxydagger;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserInvocationHandler implements InvocationHandler {
    public static final String TAG = UserInvocationHandler.class.getSimpleName();

    private Object target;
    public UserInvocationHandler(Object object){
        target = object;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        sendPointToEntryToServer(objects);
        Object result = method.invoke(target, objects);
        return result;
    }
    private void sendPointToEntryToServer(Object[] objs){
        if(objs.length>0 && objs[0] instanceof User){
            User user = (User) objs[0];
            Log.e(TAG, "해당 유져 정보를 서버로 전송 : "+ user.getName());
        }

    }
}
